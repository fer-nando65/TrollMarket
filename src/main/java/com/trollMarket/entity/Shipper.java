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
@Table(name = "Shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CompanyName", length = 50, nullable = false)
    private String companyName;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Service", nullable = false)
    private Boolean service;

    @OneToMany(mappedBy = "shipper")
    private List<Cart> carts;

    @OneToMany(mappedBy = "shipper")
    private List<Order> orders;
}
