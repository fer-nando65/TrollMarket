package com.trollMarket.repository;


import com.trollMarket.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("""
            SELECT car
            FROM Cart car
            JOIN car.buyer buy
            WHERE buy.username =:username
            """)
    List<Cart> get(String username);

    @Query("""
            SELECT car
            FROM Cart car
            WHERE car.id =:id
            """)
    Cart getById(Integer id);

    @Query("""
            SELECT COUNT(car)
            FROM Cart car
            WHERE car.merchandiseId = :id
            """)
    Integer countByMerchandise(Integer id);

    @Query("""
            SELECT COUNT(car)
            FROM Cart car
            WHERE car.shipperId = :id
            """)
    Integer countByShipper(Integer id);
}
