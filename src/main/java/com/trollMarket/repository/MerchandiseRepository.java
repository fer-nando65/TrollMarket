package com.trollMarket.repository;

import com.trollMarket.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    @Query("""
            SELECT mer
            FROM Merchandise mer
            JOIN mer.category cat
            WHERE mer.name LIKE %:merchandiseName%
            AND cat.id IN :categoryId
            AND mer.description LIKE %:description%
            AND mer.discontinue = false
            """)
    List<Merchandise> get(String merchandiseName, List<Integer>categoryId ,String description);

    @Query("""
            SELECT mer
            FROM Merchandise mer
            JOIN mer.seller sel
            WHERE sel.username =:username
            """)
    List<Merchandise> get(String username);

    @Query("""
            SELECT mer
            FROM Merchandise mer
            WHERE mer.id =:id
            """)
    Merchandise getById(Integer id);
}
