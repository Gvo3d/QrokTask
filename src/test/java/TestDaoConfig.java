import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import qroktask.Application;
import qroktask.services.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Gvozd on 12.07.2017.
 */
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Application.class)
public class TestDaoConfig {

    private String driver;
    private String url;
    private String username;
    private String dialect;
    private String hbm2ddlAuto;
    private String packageToScan;

    public TestDaoConfig() {
        Locale.setDefault(Locale.ENGLISH);
        Properties props = new Properties();
        FileInputStream fis = null;

        String fileName = "application.properties";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = props.getProperty("dataSource.driverClassName");
        url = props.getProperty("dataSource.url");
        username = props.getProperty("dataSource.username");
        dialect = props.getProperty("dataSource.dialect");
        hbm2ddlAuto = props.getProperty("dataSource.hbm2ddlAuto");
        packageToScan = props.getProperty("dataSource.package");
    }

    @Bean
    public DataSource dataSource() {
        Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(realDataSource());
        Log4JdbcCustomFormatter log4JdbcCustomFormatter = new Log4JdbcCustomFormatter();
        log4JdbcCustomFormatter.setLoggingType(LoggingType.SINGLE_LINE);
        log4JdbcCustomFormatter.setSqlPrefix("SQL:::");
        dataSource.setLogFormatter(log4JdbcCustomFormatter);
        return dataSource;
    }

    @Bean
    public DataSource realDataSource() {
        try {
            System.out.println("Loading " + driver);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
                .addScript("sql/schemecreation.sql")
                .addScript("sql/datainsertion.sql")
                .build();
        return db;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(packageToScan);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setDataSource(dataSource());
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

    @PostConstruct
    public void startDBManager() {
        DatabaseManagerSwing.main(new String[] { "--url", url, "--user", username, "--password", "" });
    }

    @Bean
    RewardService rewardService() { return new RewardServiceImpl(); }

    @Bean
    AuthorsService authorsService() { return new AuthorsServiceImpl(); }

    @Bean
    BooksService booksService() { return new BooksServiceImpl(); }

//    @Bean
//    SecurityService securityService(){
//        return new SecurityServiceImpl();
//    }
//
//    @Bean
//    AuthenticationProvider authenticationProvider(){
//        CustomAuthProvider provider = new CustomAuthProvider();
//        return provider;
//    }
}
