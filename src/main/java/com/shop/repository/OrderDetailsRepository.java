package com.shop.repository;

import com.shop.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    Optional<Set<OrderDetails>> findByUserId(Long user_id);



}
