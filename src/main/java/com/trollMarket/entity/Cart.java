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
@Table(name = "Carts")
public class Cart {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Username", length = 50)
    private String username;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Buyer buyer;

    @Column(name = "MerchandiseId")
    private Integer merchandiseId;

    @ManyToOne
    @JoinColumn(name = "MerchandiseId", insertable = false, updatable = false)
    private Merchandise merchandise;

    @Column(name = "ShipperId")
    private Integer shipperId;

    @ManyToOne
    @JoinColumn(name = "ShipperId", insertable = false, updatable = false)
    private Shipper shipper;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice;
}
