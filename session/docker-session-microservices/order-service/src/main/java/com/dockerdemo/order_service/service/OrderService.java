package com.dockerdemo.order_service.service;

import com.dockerdemo.order_service.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO createOrder(OrderDTO orderDTO);
}
