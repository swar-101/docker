package com.dockerdemo.order_service.service;

import com.dockerdemo.order_service.dto.OrderDTO;
import com.dockerdemo.order_service.dto.OrderItemDTO;
import com.dockerdemo.order_service.entity.Order;
import com.dockerdemo.order_service.entity.OrderItem;
import com.dockerdemo.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        // Map OrderItemDTOs to OrderItems and add them to the order
        if (orderDTO.getOrderItems() != null) {
            orderDTO.getOrderItems().forEach(itemDTO -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(itemDTO.getProductId());
                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setPrice(itemDTO.getPrice());
                order.addOrderItem(orderItem);
            });
        }
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());
        dto.setOrderItems(itemDTOs);
        return dto;
    }
}