package com.trollMarket.dto.Account;

import com.trollMarket.validation.CheckConfirmPassword;
import com.trollMarket.validation.UniqueUsernameAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@CheckConfirmPassword(first = "password", second = "confirmPassword", message = "Confirm Password is not matched!")
public class AccountAdminRegisterDTO {
    @UniqueUsernameAccount(message = "Username is already exists!")
    @NotBlank(message = "Username should not be empty!")
    private String username;
    @NotBlank(message = "Password should not be empty!")
    private String password;
    private String confirmPassword;
}
