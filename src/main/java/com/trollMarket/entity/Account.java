package com.trollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @Column(name = "Password", length = 200, nullable = false)
    private String password;

    @Column(name = "Role", length = 20, nullable = false)
    private String role;

    @OneToOne(mappedBy = "account",  cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;
}
