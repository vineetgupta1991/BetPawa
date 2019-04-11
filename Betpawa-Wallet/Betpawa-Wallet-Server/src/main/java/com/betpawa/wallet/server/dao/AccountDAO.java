package com.betpawa.wallet.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.betpawa.wallet.server.model.Account;

/**
 *
 * @author vingupta3
 */
public interface AccountDAO {

    List<Account> findAllByUserId(Long userId);

    Optional<Account> findByUserIdAndCurrency(Long userId, String currencyCode);

    Optional<Account> findById(Long id);

    Account saveOrUpdate(Account entity);

    void delete(Long id);
    
    int deleteByUserId(Long userId);

    void updateAccountBalance(Long accountId, BigDecimal deltaAmount);
}
