package com.utils;

import com.Account;

import java.util.List;

public interface DAO {
    Account save(Account account);

    Account getByPhone(String phone);

    List<Account> getByAddress(String address);
}
