package util;

import domain.Transaction;
import domain.TransactionNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
public class TransactionDataContainer {

    public static Map<Long, Transaction> transactionMap = new HashMap<>();
    public static List<TransactionNode> transactionTree = new ArrayList<>();

}
