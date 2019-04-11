package com.betpawa.wallet.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author vingupta3
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Account.findAllByUserId",
            query = "SELECT a FROM Account a WHERE a.userId = :userId")
    ,
        @NamedQuery(
            name = "Account.findAllByUserIdAndCurrency",
            query = "SELECT a FROM Account a WHERE a.userId = :userId AND a.currencyCode = :currencyCode")
    ,
        @NamedQuery(
            name = "Account.deleteByUserId",
            query = "DELETE FROM Account a WHERE a.userId = :userId")
})
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;
    
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    
    @Version
    public long version;

    public Account() {
    }

    public Account(Long userId, String currencyCode, BigDecimal balance) {
        this.userId = userId;
        this.currencyCode = currencyCode;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.userId);
        hash = 47 * hash + Objects.hashCode(this.currencyCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.currencyCode, other.currencyCode)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" + "userId=" + userId + ", currencyCode=" + currencyCode + ", balance=" + balance + '}';
    }
}
