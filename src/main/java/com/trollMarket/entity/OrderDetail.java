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
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailId")
    private Integer Id;

    @Column(name = "OrderId")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "OrderId", insertable = false, updatable = false)
    private Order order;

    @Column(name = "MerchandiseId")
    private Integer merchandiseId;

    @ManyToOne
    @JoinColumn(name = "MerchandiseId", insertable = false, updatable = false)
    private Merchandise merchandise;

    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;
}
