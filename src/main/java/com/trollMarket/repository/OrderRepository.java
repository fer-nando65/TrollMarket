package com.trollMarket.repository;

import com.trollMarket.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("""
            SELECT MAX(ord.id)
            FROM Order ord
            """)
    Integer latestOrderId();

    @Query("""
            SELECT COUNT(ord)
            FROM Order ord
            WHERE ord.shipperId = :id
            """)
    Integer countByShipper(Integer id);
}
