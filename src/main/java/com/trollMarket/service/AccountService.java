package com.trollMarket.service;

import com.trollMarket.dto.Account.AccountAdminRegisterDTO;
import com.trollMarket.dto.Account.AccountLoginDTO;
import com.trollMarket.dto.Account.AccountRegisterDTO;
import com.trollMarket.dto.Account.AccountTokenDTO;

public interface AccountService {
    void register(AccountRegisterDTO dto);
    public AccountTokenDTO createToken(AccountLoginDTO dto);
    void registerAdmin(String username, String password);
    void registerAdmin(AccountAdminRegisterDTO dto);
}
