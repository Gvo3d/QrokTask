import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import qroktask.models.Author;
import qroktask.models.Book;
import qroktask.models.Reward;
import qroktask.models.enums.Genre;
import qroktask.models.enums.Sex;
import qroktask.services.AuthorsService;
import qroktask.services.BooksService;
import qroktask.services.RewardService;

import java.util.Date;

@ContextConfiguration(classes = {TestDaoConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class DatabaseTests extends AbstractTest {
    private static final String SEPARATOR = "********************************************";

    @Autowired
    RewardService rewardService;

    @Autowired
    AuthorsService authorsService;

    @Autowired
    BooksService booksService;

    @Test
    public void testFindAllRewards() {
        Iterable<Reward> iterable = rewardService.getAllRewards();
        for (Reward reward : iterable) {
            System.out.println(reward);
        }
        System.out.println(SEPARATOR);
        Assert.assertNotNull(iterable);
    }

    @Test
    public void testFindReward() {
        Integer rewardId = 1;
        Reward reward = rewardService.getOneReward(rewardId);
        System.out.println(reward);
        System.out.println(SEPARATOR);
    }

    @Test
    public void testFindAllAuthors() {
        Iterable<Author> iterable = authorsService.getAllAuthors();
        for (Author author : iterable) {
            System.out.println(author);
        }
        System.out.println(SEPARATOR);
        Assert.assertNotNull(iterable);
    }

    @Test
    public void testFindAllAuthorsFetchAll() {
        Iterable<Author> iterable = authorsService.getAllAuthorsFetchAll();
        for (Author author : iterable) {
            System.out.println(author.toStringWithAll());
        }
        System.out.println(SEPARATOR);
        Assert.assertNotNull(iterable);
    }

    @Test
    public void testFindAuthorFetchAll() {
        int authorId = 1;
        Author author = authorsService.getOneAuthorFetchAll(authorId);
        System.out.println(author.toStringWithAll());
    }

    @Test
    public void testFindAllBooks() {
        Iterable<Book> iterable = booksService.getAllBooks();
        for (Book book : iterable) {
            System.out.println(book);
        }
        System.out.println(SEPARATOR);
        Assert.assertNotNull(iterable);
    }

    @Test
    public void testFindAllBooksFetchAll() {
        Iterable<Book> iterable = booksService.getAllBooksFetchAll();
        for (Book book : iterable) {
            System.out.println(book.toStringWithAll());
        }
        System.out.println(SEPARATOR);
        Assert.assertNotNull(iterable);
    }

    @Test
    public void testFindBookFetchAll() {
        int bookId = 1;
        Book book = booksService.getOneBookFetchAll(bookId);
        System.out.println(book.toStringWithAll());
        System.out.println(SEPARATOR);
    }

    @Test
    @Transactional
    public void rewardFullCycle() {
        Reward reward = new Reward();
        reward.setRewardYear(2017);
        reward.setRewardTitle("TemporaryReward");
        Reward fromDatabase = rewardService.createOrUpdateReward(reward);
        System.out.println(fromDatabase.toString());
        System.out.println(SEPARATOR);
        Assert.assertNotNull(fromDatabase);
        Assert.assertEquals(reward.getRewardTitle(), fromDatabase.getRewardTitle());
        Assert.assertEquals(reward.getRewardYear(), fromDatabase.getRewardYear());
        Assert.assertEquals(true, rewardService.exists(fromDatabase.getId()));
        Assert.assertEquals(true, rewardService.delete(fromDatabase.getId()));
        Assert.assertEquals(false, rewardService.exists(fromDatabase.getId()));
    }

    @Test
    public void authorFullCycle() {
        Author author = new Author();
        author.setBirthDate(new Date(System.currentTimeMillis()));
        author.setFirstName("Temporary firstname");
        author.setLastName("Temporary lastname");
        author.setSex(Sex.MALE);
        Book book = booksService.getOneBookFetchAll(3);
        Reward reward = rewardService.getOneReward(3);
        Author fromDatabase = authorsService.create(author);
        fromDatabase = authorsService.getOneAuthorFetchAll(fromDatabase.getId());
        fromDatabase.addBook(book);
        fromDatabase.addReward(reward);
        authorsService.update(fromDatabase);
        fromDatabase = authorsService.getOneAuthorFetchAll(fromDatabase.getId());
        System.out.println(fromDatabase.toString());
        System.out.println(SEPARATOR);
        boolean hasThatBook = false;
        for (Book bookFromAuthor : fromDatabase.getBooks()) {
            if (bookFromAuthor.equals(book)) {
                hasThatBook = true;
            }
        }
        Assert.assertEquals(true, hasThatBook);
        Assert.assertEquals(fromDatabase.getRewards(), authorsService.getOneAuthorFetchAll(author.getId()).getRewards());
        Assert.assertEquals(author.getBirthDate(), authorsService.getOneAuthorFetchAll(author.getId()).getBirthDate());
        Assert.assertEquals(author.getFirstName(), authorsService.getOneAuthorFetchAll(author.getId()).getFirstName());
        Assert.assertEquals(author.getLastName(), authorsService.getOneAuthorFetchAll(author.getId()).getLastName());
        Assert.assertEquals(author.getSex(), authorsService.getOneAuthorFetchAll(author.getId()).getSex());
        Assert.assertEquals(true, authorsService.exists(fromDatabase.getId()));
        Assert.assertEquals(true, authorsService.delete(fromDatabase.getId()));
        Assert.assertEquals(false, authorsService.exists(fromDatabase.getId()));
    }

    @Test
    public void bookFullCycle() {
        Book book = new Book();
        book.setGenre(Genre.TRAGEDY);
        book.setIsbn("fgjknsdkljcvnsdl");
        book.setTitle("Title");
        Author author = authorsService.getOneAuthor(2);
        book.addAuthor(author);
        Book fromDatabase = booksService.createOrUpdateBook(book);
        Assert.assertNotNull(fromDatabase);
        Assert.assertEquals(book.getTitle(), fromDatabase.getTitle());
        Assert.assertEquals(book.getIsbn(), fromDatabase.getIsbn());
        Assert.assertEquals(book.getGenre(), fromDatabase.getGenre());
        Assert.assertEquals(book.getAuthors(), fromDatabase.getAuthors());
        Assert.assertEquals(true, booksService.exists(fromDatabase.getId()));
        Author fromDatabaseAuthor = authorsService.getOneAuthorFetchAll(author.getId());
        boolean authorHasThatBook = false;
        for (Book bookFromAuthor : fromDatabaseAuthor.getBooks()) {
            if (bookFromAuthor.equals(fromDatabase)) {
                authorHasThatBook = true;
            }
        }
        Assert.assertEquals(true, authorHasThatBook);
        Assert.assertEquals(true, booksService.delete(fromDatabase.getId()));
        Assert.assertEquals(false, booksService.exists(fromDatabase.getId()));
    }
}
