package br.com.fiap.Marketplace.orderItem.dto;

import br.com.fiap.Marketplace.order.Orders;
import br.com.fiap.Marketplace.orderItem.OrderItem;
import br.com.fiap.Marketplace.product.Product;

import java.util.UUID;

public record OrderItemRequest(Orders order, Product product, int quantity ) {

    public OrderItem toModel() {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .build();
    }

}
