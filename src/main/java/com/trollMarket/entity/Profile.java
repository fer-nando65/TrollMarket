package com.trollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Profiles")
public class Profile {
    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Column(name = "Address", length = 100)
    private String address;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Seller seller;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Buyer buyer;
}
