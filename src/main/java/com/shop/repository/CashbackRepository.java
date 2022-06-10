package com.shop.repository;

import com.shop.entity.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {
}
