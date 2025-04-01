package com.trollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Buyers")
public class Buyer {
    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Username")
    private Profile profile;

    @Column(name = "Balance", nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "buyer")
    private List<Cart> carts;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orders;
}
