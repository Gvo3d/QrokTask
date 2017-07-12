import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import qroktask.services.RewardService;

@ContextConfiguration(classes = {TestDaoConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class RewardDbTest extends AbstractTest{

    @Autowired
    RewardService rewardService;

    @Test
    public void testFindByname() {
        System.out.println(rewardService.getAllRewards());
    }


}
