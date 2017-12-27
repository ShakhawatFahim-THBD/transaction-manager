import domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.TransactionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionService.class)
public class TransactionServiceTest extends TransactionTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void test_getTransaction() {
        Transaction transaction1 = transactionService.getTransaction(1);
        assertEquals("shopping", transaction1.getType());
        assertEquals(100, transaction1.getAmount(), 0);

        Transaction transaction2 = transactionService.getTransaction(4);
        assertEquals("car", transaction2.getType());
        assertEquals(4000, transaction2.getAmount(), 0);
        assertEquals(3, (long) transaction2.getParentId());

        Transaction transaction3 = transactionService.getTransaction(7);
        assertEquals("discount", transaction3.getType());
        assertEquals(-200.50, transaction3.getAmount(), 0);
        assertNotEquals(5, (long) transaction3.getParentId());
    }

    @Test
    public void test_getTransactionIds() {
        List<Long> transactionIdList = transactionService.getTransactionIds("shopping");
        List<Long> transactionIdList2 = transactionService.getTransactionIds("discount");

        List<Long> expectedIdList = Arrays.asList(1L, 3L, 6L);

        assertEquals(expectedIdList, transactionIdList);
        assertEquals(Collections.singletonList(7L), transactionIdList2);
    }

    @Test
    public void test_getTotalTransactionAmount() {
        assertEquals(-200.50, transactionService.getTotalTransactionAmount(7), 0);
        assertEquals(399.50, transactionService.getTotalTransactionAmount(6), 0);
        assertEquals(5000, transactionService.getTotalTransactionAmount(5), 0);
        assertEquals(9300, transactionService.getTotalTransactionAmount(3), 0);
        assertEquals(9799.5, transactionService.getTotalTransactionAmount(1), 0);
        assertEquals(2000, transactionService.getTotalTransactionAmount(2), 0);

        assertNotEquals(9799, transactionService.getTotalTransactionAmount(1), 0);
    }
}