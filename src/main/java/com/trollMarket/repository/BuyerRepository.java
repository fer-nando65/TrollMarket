package com.trollMarket.repository;

import com.trollMarket.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, String> {
    @Query("""
            SELECT buy
            FROM Buyer buy
            WHERE buy.username =:username
            """)
    Buyer getById(String username);
}
