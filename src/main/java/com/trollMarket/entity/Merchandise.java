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
@Table(name = "Merchandises")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "ProductName")
    private String name;

    @Column(name = "CategoryId")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "CategoryId", insertable = false, updatable = false, nullable = false)
    private Category category;

    @Column(name = "UsernameSeller", length = 50)
    private String usernameSeller;

    @ManyToOne
    @JoinColumn(name = "UsernameSeller", nullable = false, insertable = false, updatable = false)
    private Seller seller;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Discontinue")
    private Boolean discontinue;

    @OneToMany(mappedBy = "merchandise")
    private List<Cart> carts;

    @OneToMany(mappedBy = "merchandise")
    private List<OrderDetail> orderDetails;
}
