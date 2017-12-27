package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
public class TransactionNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private Set<TransactionNode> childNodes;

    public TransactionNode(long id) {
        this.id = id;
        this.childNodes = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<TransactionNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<TransactionNode> childNodes) {
        this.childNodes = childNodes;
    }
}
