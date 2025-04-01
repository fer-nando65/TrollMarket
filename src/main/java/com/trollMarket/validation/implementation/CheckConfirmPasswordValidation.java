package com.trollMarket.validation.implementation;

import com.trollMarket.validation.CheckConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class CheckConfirmPasswordValidation implements ConstraintValidator<CheckConfirmPassword, Object> {
    private String first;
    private String second;

    @Override
    public void initialize(CheckConfirmPassword constraintAnnotation) {
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        String password = (String) new BeanWrapperImpl(object).getPropertyValue(this.first);
        String confirmPassword = (String) new BeanWrapperImpl(object).getPropertyValue(this.second);
        if (password.equals(confirmPassword)){
            return true;
        }else {
            return false;
        }

    }
}
