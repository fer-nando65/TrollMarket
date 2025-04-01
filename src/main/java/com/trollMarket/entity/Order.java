package com.trollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer id;

    @Column(name = "Username", length = 50)
    private String username;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Buyer buyer;

    @Column(name = "OrderDate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "ShipperId")
    private Integer shipperId;

    @ManyToOne
    @JoinColumn(name = "ShipperId", insertable = false, updatable = false)
    private Shipper shipper;

    @Column(name = "DeliveryCost", nullable = false)
    private BigDecimal deliveryCost;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
