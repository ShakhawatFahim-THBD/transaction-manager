package service;

import domain.Transaction;
import domain.TransactionNode;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import static util.TransactionDataContainer.transactionMap;
import static util.TransactionDataContainer.transactionTree;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
@Service
public class TransactionService {

    public Transaction getTransaction(long transactionId) {
        return transactionMap.get(transactionId);
    }

    public List<Long> getTransactionIds(String type) {

        return transactionMap.entrySet().stream()
                .filter(entry -> entry.getValue().getType().equals(type))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public void addTransaction(Transaction transaction) {
        transactionMap.put(transaction.getId(), transaction);

        TransactionNode transactionNode = new TransactionNode(transaction.getId());

        if (transaction.getParentId() == null) {
            transactionTree.add(transactionNode);
            return;
        }

        TransactionNode parentNode = findTransactionNode(transaction.getParentId());

        if (parentNode == null) {
            throw new IllegalArgumentException("No Transaction Found with id = " + transaction.getParentId());
        }

        parentNode.getChildNodes().add(transactionNode);
    }

    public double getTotalTransactionAmount(long transactionId) {
        Transaction transaction = transactionMap.get(transactionId);

        TransactionNode transactionNode = findTransactionNode(transactionId);

        Queue<TransactionNode> transactionNodeQueue = new LinkedList<>();
        transactionNodeQueue.addAll(transactionNode.getChildNodes());

        double amount = transaction.getAmount();

        while (!transactionNodeQueue.isEmpty()) {
            TransactionNode cTransactionNode = transactionNodeQueue.poll();
            transactionNodeQueue.addAll(cTransactionNode.getChildNodes());

            Transaction cTransaction = transactionMap.get(cTransactionNode.getId());

            amount += cTransaction.getAmount();
        }

        return amount;
    }

    private static TransactionNode findTransactionNode(long transactionId) {
        Queue<TransactionNode> transactionQueue = new LinkedList<>();

        transactionQueue.addAll(transactionTree);

        while (!transactionQueue.isEmpty()) {
            TransactionNode transactionNode = transactionQueue.poll();

            if (transactionNode.getId() == transactionId) {
                return transactionNode;
            }

            transactionQueue.addAll(transactionNode.getChildNodes());
        }

        return null;
    }
}
