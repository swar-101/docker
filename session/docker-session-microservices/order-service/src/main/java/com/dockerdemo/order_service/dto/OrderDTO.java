package com.dockerdemo.order_service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;

    // Default constructor
    public OrderDTO() {}

    // Parameterized constructor
    public OrderDTO(Long id, LocalDateTime orderDate, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
