package com.trollMarket.validation.implementation;

import com.trollMarket.repository.ShipperRepository;
import com.trollMarket.validation.UniqueShipperCompanyName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueShipperCompanyNameImplementation implements ConstraintValidator<UniqueShipperCompanyName, String> {
    private final ShipperRepository repository;

    @Autowired
    public UniqueShipperCompanyNameImplementation(ShipperRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueShipperCompanyName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String companyName, ConstraintValidatorContext constraintValidatorContext) {
        int count = this.repository.countByCompanyName(companyName);
        return !(count > 1);
    }
}
