package com.trollMarket.repository;

import com.trollMarket.dto.Admin.AllHistoryRawDTO;
import com.trollMarket.dto.Profile.ProfileHistoryRawDTO;
import com.trollMarket.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("""
            SELECT new com.trollMarket.dto.Profile.ProfileHistoryRawDTO(
            ord.orderDate, mer.name, ordd.quantity, shi.companyName, ordd.unitPrice, shi.price)
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.merchandise mer
            JOIN ord.shipper shi
            WHERE ord.username =:username
            """)
    List<ProfileHistoryRawDTO> getHistoryBuyer(String username);

    @Query("""
            SELECT new com.trollMarket.dto.Profile.ProfileHistoryRawDTO(
            ord.orderDate, mer.name, ordd.quantity, shi.companyName, ordd.unitPrice, shi.price)
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.merchandise mer
            JOIN ord.shipper shi
            WHERE mer.usernameSeller =:username
            """)
    List<ProfileHistoryRawDTO> getHistorySeller(String username);

    @Query("""
            SELECT new com.trollMarket.dto.Admin.AllHistoryRawDTO(
            ord.orderDate, mer.usernameSeller, ord.username, mer.name, ordd.quantity,
            shi.companyName, ordd.unitPrice, shi.price)
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.merchandise mer
            JOIN ord.shipper shi
            WHERE mer.usernameSeller IN %:usernameSeller%
            AND ord.username IN %:usernameBuyer%
            """)
    List<AllHistoryRawDTO> getAllHistory(List<String> usernameSeller, List<String> usernameBuyer);

    @Query("""
            SELECT COUNT(ordd)
            FROM OrderDetail ordd
            WHERE ordd.merchandiseId = :id
            """)
    Integer countByMerchandise(Integer id);
}
