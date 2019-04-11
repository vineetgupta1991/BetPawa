package com.betpawa.wallet.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;

import com.betpawa.wallet.server.DbConnection;
import com.betpawa.wallet.server.dao.AccountDAO;
import com.betpawa.wallet.server.exception.AccountLockException;
import com.betpawa.wallet.server.exception.AccountNotFoundException;
import com.betpawa.wallet.server.exception.InsufficientFundsException;
import com.betpawa.wallet.server.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vingupta3
 */
public class AccountDAOImpl implements AccountDAO {

    private final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    /**
     * Find all User's accounts
     *
     * @param userId User ID
     * @return List of accounts
     */
    @Override
    public List<Account> findAllByUserId(Long userId) {
        return DbConnection.getEntityManager()
                .createNamedQuery("Account.findAllByUserId")
                .setParameter("userId", userId)
                .getResultList();
    }

    /**
     * Find all User's accounts by Currency
     *
     * @param userId User ID
     * @param currencyCode Currency 3-char CODE
     * @return existing Account if found, else NULL
     */
    @Override
    public Optional<Account> findByUserIdAndCurrency(Long userId, String currencyCode) {
        return Optional.ofNullable((Account) DbConnection.getEntityManager()
                .createNamedQuery("Account.findAllByUserIdAndCurrency")
                .setParameter("userId", userId)
                .setParameter("currencyCode", currencyCode)
                .setMaxResults(1).getSingleResult());
    }

    /**
     * Get account by id.
     *
     * @param id Account ID
     * @return existing Account if found, else NULL
     */
    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(DbConnection.getEntityManager().find(Account.class, id));
    }

    /**
     * Create new account.
     *
     * @param entity Account data
     * @return created/updated Account
     */
    @Override
    public Account saveOrUpdate(Account entity) {
        try {
            DbConnection.beginTransaction();

            Account result = DbConnection.getEntityManager().merge(entity);

            DbConnection.commit();

            return result;
        } catch (RuntimeException ex) {
            if (DbConnection.getEntityManager() != null && DbConnection.getEntityManager().isOpen()) {
                DbConnection.rollback();
            }
            logger.error(ex.getMessage());
            throw ex;
        } finally {
            DbConnection.closeEntityManager();
        }
    }

    /**
     * Delete account by id.
     *
     * @param id Account ID
     * @throws AccountNotFoundException if there's no Account with the given id
     */
    @Override
    public void delete(Long id) {
        try {
            DbConnection.beginTransaction();

            Account targetAccount = findById(id).orElseThrow(
                    () -> new AccountNotFoundException("Account with ID " + id + " doesn't exist!"));

            DbConnection.getEntityManager().remove(targetAccount);

            DbConnection.commit();
        } catch (RuntimeException ex) {
            if (DbConnection.getEntityManager() != null && DbConnection.getEntityManager().isOpen()) {
                DbConnection.rollback();
            }
            logger.error(ex.getMessage());
            throw ex;
        } finally {
            DbConnection.closeEntityManager();
        }
    }

    /**
     * Delete all User's accounts.
     *
     * @param userId User ID
     * @return the number Accounts that have been deleted by the query
     */
   
    @Override
    public int deleteByUserId(Long userId) {
        try {
            DbConnection.beginTransaction();

            int deletedCount = DbConnection.getEntityManager()
                    .createNamedQuery("Account.deleteByUserId")
                    .setParameter("userId", userId)
                    .executeUpdate();

            DbConnection.commit();

            return deletedCount;
        } catch (RuntimeException ex) {
            if (DbConnection.getEntityManager() != null && DbConnection.getEntityManager().isOpen()) {
                DbConnection.rollback();
            }
            logger.error(ex.getMessage());
            throw ex;
        } finally {
            DbConnection.closeEntityManager();
        }
    }

    /**
     * Update account balance.
     *
     * @param accountId Account ID
     * @param deltaAmount Delta Amount
     * @throws AccountLockException if Account lock fails
     * @throws InsufficientFundsException if not enough funds on Account
     */
    @Override
    public void updateAccountBalance(Long accountId, BigDecimal deltaAmount) {
        try {
            DbConnection.beginTransaction();

            Account targetAccount = DbConnection.getEntityManager().find(
                    Account.class, accountId, LockModeType.READ);

            if (targetAccount == null) {
                throw new AccountLockException("Failed to lock Account with ID " + accountId);
            }

            // update account upon success locking
            BigDecimal balance = targetAccount.getBalance().add(deltaAmount);
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                throw new InsufficientFundsException("Not enough funds on " + targetAccount);
            }

            // proceed with update
            targetAccount.setBalance(balance);

            DbConnection.getEntityManager().merge(targetAccount);

            DbConnection.commit();
        } catch (RuntimeException ex) {
            if (DbConnection.getEntityManager() != null && DbConnection.getEntityManager().isOpen()) {
                DbConnection.rollback();
            }
            logger.error(ex.getMessage());
            throw ex;
        } finally {
            DbConnection.closeEntityManager();
        }
    }
}
