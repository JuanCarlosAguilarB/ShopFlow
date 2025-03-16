package auth_service.order.service;

import auth_service.order.domain.Order;

import java.util.List;

public record OrderResponse(List<Order> order){}
