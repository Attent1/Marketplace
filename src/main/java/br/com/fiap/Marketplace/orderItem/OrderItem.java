package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.order.Orders;
import br.com.fiap.Marketplace.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal price;

    public EntityModel<OrderItem> toEntityModel() {
        EntityModel<OrderItem> entityModel = EntityModel.of(this);

        entityModel.add(linkTo(methodOn(OrderItemController.class).getOrderItems(null)).withRel("orderItems"));
        entityModel.add(linkTo(methodOn(OrderItemController.class).getOrderItemById(id)).withRel("getOrderItemById"));
        entityModel.add(linkTo(methodOn(OrderItemController.class).deleteOrderItem(id)).withRel("deleteOrderItem"));

        return entityModel;
    }
}
