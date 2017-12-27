package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String type;
    private double amount;
    private Long parentId;

    public Transaction() {
    }

    public Transaction(long id, String type, double amount) {
        this(id, type, amount, null);
    }

    public Transaction(long id, String type, double amount, Long parentId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
