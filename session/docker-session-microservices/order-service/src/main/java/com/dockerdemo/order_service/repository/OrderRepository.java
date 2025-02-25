package com.dockerdemo.order_service.repository;

import com.dockerdemo.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
