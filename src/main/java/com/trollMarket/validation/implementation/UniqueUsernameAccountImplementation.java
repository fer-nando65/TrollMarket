package com.trollMarket.validation.implementation;

import com.trollMarket.repository.AccountRepository;
import com.trollMarket.validation.UniqueUsernameAccount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameAccountImplementation implements ConstraintValidator<UniqueUsernameAccount,String> {
    private final AccountRepository repository;

    @Autowired
    public UniqueUsernameAccountImplementation(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueUsernameAccount constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsById(username);
    }
}
