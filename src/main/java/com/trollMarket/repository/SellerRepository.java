package com.trollMarket.repository;

import com.trollMarket.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
    @Query("""
            SELECT sel
            FROM Seller sel
            WHERE sel.username =:username
            """)
    Seller getById(String username);
}
