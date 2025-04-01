package com.trollMarket.repository;

import com.trollMarket.dto.Category.CategoryIndexDTO;
import com.trollMarket.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("""
            SELECT new com.trollMarket.dto.Category.CategoryIndexDTO(cat.id, cat.name)
            FROM Category cat
            """)
    List<CategoryIndexDTO> get();

    @Query("""
            SELECT cat.id
            FROM Category cat
            """)
    List<Integer> getOnlyId();
}
