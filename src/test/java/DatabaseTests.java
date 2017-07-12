import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
    public void testFindAllAuthors() {
        Iterable<Author> iterable = authorsService.getAllAuthors();
        for (Author author: iterable){
            System.out.println(author);
        }
    }

    @Test
    public void testFindAllBooks() {
        Iterable<Book> iterable = booksService.getAllBooks();
        for (Book book: iterable){
            System.out.println(book);
        }
    }
}
