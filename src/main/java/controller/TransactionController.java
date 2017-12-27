package controller;

import domain.Response;
import domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TransactionService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
@RestController
@RequestMapping(TransactionController.BASE_URL)
public class TransactionController {

    public static final String BASE_URL = "/transactionservice";

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public Transaction getTransaction(@PathVariable long transactionId) {

        return transactionService.getTransaction(transactionId);
    }

    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    public List<Long> getTransactionIds(@PathVariable String type) {

        return transactionService.getTransactionIds(type);
    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.PUT)
    public Response addTransaction(@PathVariable long transactionId,
                                   @RequestBody Transaction transaction) {

        transaction.setId(transactionId);

        transactionService.addTransaction(transaction);

        return new Response(Response.STATUS.OK);
    }

    @RequestMapping(value = "/sum/{transactionId}", method = RequestMethod.GET)
    public Map<String, Double> getTransactionAmount(@PathVariable long transactionId) {
        double sum = transactionService.getTotalTransactionAmount(transactionId);

        return Collections.singletonMap("sum", sum);
    }
}
