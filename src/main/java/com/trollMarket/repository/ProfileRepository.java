package com.trollMarket.repository;

import com.trollMarket.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    @Query("""
            SELECT pro
            FROM Profile pro
            WHERE pro.username =:username
            """)
    Profile getById(String username);
}
