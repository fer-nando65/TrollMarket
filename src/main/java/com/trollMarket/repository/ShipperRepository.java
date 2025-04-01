package com.trollMarket.repository;

import com.trollMarket.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
    @Query("""
            SELECT shi
            FROM Shipper shi
            """)
    List<Shipper> get();

    @Query("""
            SELECT shi
            FROM Shipper shi
            WHERE shi.service = false
            """)
    List<Shipper> getOptionShipper();

    @Query("""
            SELECT shi
            FROM Shipper shi
            WHERE shi.id =:id
            """)
    Shipper getById(Integer id);

    @Query("""
            SELECT COUNT(shi)
            FROM Shipper shi
            WHERE LOWER(shi.companyName) = LOWER(:companyName)
            """)
    Integer countByCompanyName(String companyName);
}
