package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.order.Order;
import br.com.fiap.Marketplace.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Entity;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal price;

}
