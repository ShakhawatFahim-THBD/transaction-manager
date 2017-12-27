import domain.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import service.TransactionService;
import util.TransactionDataContainer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
public abstract class TransactionTest {

    @Autowired
    private TransactionService transactionService;

    @Before
    public void setUp() {
        Transaction transaction1 = new Transaction(1, "shopping", 100);
        Transaction transaction2 = new Transaction(2, "car", 2000);
        Transaction transaction3 = new Transaction(3, "shopping", 300, 1L);
        Transaction transaction4 = new Transaction(4, "car", 4000, 3L);
        Transaction transaction5 = new Transaction(5, "car", 5000, 3L);
        Transaction transaction6 = new Transaction(6, "shopping", 600, 1L);
        Transaction transaction7 = new Transaction(7, "discount", -200.50, 6L);

        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);
        transactionService.addTransaction(transaction4);
        transactionService.addTransaction(transaction5);
        transactionService.addTransaction(transaction6);
        transactionService.addTransaction(transaction7);
    }

    @After
    public void reset() {
        TransactionDataContainer.transactionMap = new HashMap<>();
        TransactionDataContainer.transactionTree = new ArrayList<>();
    }
}
