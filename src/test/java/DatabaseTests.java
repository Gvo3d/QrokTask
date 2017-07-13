import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import qroktask.models.Author;
import qroktask.models.Book;
import qroktask.models.Reward;
import qroktask.services.AuthorsService;
import qroktask.services.BooksService;
import qroktask.services.RewardService;

@ContextConfiguration(classes = {TestDaoConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class DatabaseTests extends AbstractTest{

    @Autowired
    RewardService rewardService;

    @Autowired
    AuthorsService authorsService;

    @Autowired
    BooksService booksService;

    @Test
    public void testFindAllRewards() {
        Iterable<Reward> iterable = rewardService.getAllRewards();
        for (Reward reward: iterable){
            System.out.println(reward);
        }
    }

    @Test
    public void testFindReward() {
        Integer rewardId = 1;
        Reward reward = rewardService.getOneReward(rewardId);
        System.out.println(reward);
    }

    @Test
    public void testFindAllAuthors() {
        Iterable<Author> iterable = authorsService.getAllAuthors();
        for (Author author: iterable){
            System.out.println(author);
        }
    }

    @Test
    public void testFindAllAuthorsFetchAll() {
        Iterable<Author> iterable = authorsService.getAllAuthorsFetchAll();
        for (Author author: iterable){
            System.out.println(author.toStringWithAll());
        }
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
        for (Book book: iterable){
            System.out.println(book);
        }
    }

    @Test
    public void testFindAllBooksFetchAll() {
        Iterable<Book> iterable = booksService.getAllBooksFetchAll();
        for (Book book: iterable){
            System.out.println(book.toStringWithAll());
        }
    }

    @Test
    public void testFindBookFetchAll() {
        int bookId = 1;
        Book book = booksService.getOneBookFetchAll(bookId);
        System.out.println(book.toStringWithAll());
    }

    @Test
    public void rewardFullCycle(){
        Reward reward = new Reward();
        reward.setRewardYear(2017);
        reward.setRewardTitle("TemporaryReward");
        Reward fromDatabase = rewardService.createOrUpdateReward(reward);
        Assert.assertNotNull(fromDatabase);
        Assert.assertEquals(reward.getRewardTitle(), fromDatabase.getRewardTitle());
        Assert.assertEquals(reward.getRewardYear(), fromDatabase.getRewardYear());
        Assert.assertEquals(true, rewardService.exists(fromDatabase.getId()));
        Assert.assertEquals(true, rewardService.delete(fromDatabase.getId()));
        Assert.assertEquals(false, rewardService.exists(fromDatabase.getId()));

    }
}
