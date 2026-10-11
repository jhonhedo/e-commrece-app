package com.ecommerce.order.service;

import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.*;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(
            OrderRepository orderRepository,
            ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.items()) {

            if (itemRequest.quantity() == null || itemRequest.quantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            ProductResponse product = productClient.getProductById(itemRequest.productId());

            BigDecimal price = product.price();
            BigDecimal subtotal = price.multiply( BigDecimal.valueOf(itemRequest.quantity()));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(product.id());
            item.setQuantity(itemRequest.quantity());
            item.setPriceAtPurchase(price);

            order.getItems().add(item);
            totalAmount = totalAmount.add(subtotal);
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemResponse> itemResponses =
                savedOrder.getItems().stream()
                        .map(item -> new OrderItemResponse(
                                item.getProductId(),
                                item.getQuantity(),
                                item.getPriceAtPurchase(),
                                item.getPriceAtPurchase().
                                        multiply(BigDecimal.valueOf(
                                                item.getQuantity())))).toList();


        return new OrderResponse(savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(), itemResponses);
    }


}
