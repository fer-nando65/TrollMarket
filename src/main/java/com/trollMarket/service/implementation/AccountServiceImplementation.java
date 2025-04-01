package com.trollMarket.service.implementation;

import com.trollMarket.dto.Account.AccountAdminRegisterDTO;
import com.trollMarket.dto.Account.AccountLoginDTO;
import com.trollMarket.dto.Account.AccountRegisterDTO;
import com.trollMarket.dto.Account.AccountTokenDTO;
import com.trollMarket.entity.Account;
import com.trollMarket.entity.Buyer;
import com.trollMarket.entity.Profile;
import com.trollMarket.entity.Seller;
import com.trollMarket.repository.AccountRepository;
import com.trollMarket.service.AccountService;
import com.trollMarket.service.ProfileService;
import com.trollMarket.utility.Implementation.UserDetailsImplementation;
import com.trollMarket.utility.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImplementation implements AccountService, UserDetailsService {
    private final AccountRepository repository;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AccountServiceImplementation(AccountRepository repository, ProfileService profileService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void register(AccountRegisterDTO dto){
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(hashPassword);
        account.setRole(dto.getRole());

        Profile profile = new Profile();
        profile.setUsername(dto.getUsername());
        profile.setName(dto.getName());
        profile.setAddress(dto.getAddress());
        profile.setAccount(account);

        if(dto.getRole().equals("Buyer")){
            Buyer buyer = new Buyer();
            buyer.setUsername(dto.getUsername());
            buyer.setBalance(BigDecimal.ZERO);
            buyer.setProfile(profile);
            profile.setBuyer(buyer);
        } else if(dto.getRole().equals("Seller")){
            Seller seller = new Seller();
            seller.setUsername(dto.getUsername());
            seller.setBalance(BigDecimal.ZERO);
            seller.setProfile(profile);
            profile.setSeller(seller);
        }

        account.setProfile(profile);
        this.repository.save(account);
    }

    @Override
    public void registerAdmin(String username, String password){
        var hashPassword = passwordEncoder.encode(password);
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(hashPassword);
        account.setRole("Administrator");

        this.repository.save(account);
    }

    @Override
    public void registerAdmin(AccountAdminRegisterDTO dto){
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(hashPassword);
        account.setRole("Administrator");

        this.repository.save(account);
    }

    @Override
    public AccountTokenDTO createToken(AccountLoginDTO dto) {
        var account = this.repository.findById(dto.getUsername()).orElseThrow();

        if(!passwordEncoder.matches(dto.getPassword(), account.getPassword())){
            throw new IllegalArgumentException("Wrong username and password");
        }

        if(!(dto.getRole().equals(account.getRole()))){
            throw new IllegalArgumentException("Wrong username and password");
        }

        String token = jwtService.generateToken(account);
        AccountTokenDTO accountTokenDto = new AccountTokenDTO();
        accountTokenDto.setToken(token);
        return accountTokenDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = this.repository.findById(username).orElseThrow();
        return new UserDetailsImplementation(account);
    }
}
