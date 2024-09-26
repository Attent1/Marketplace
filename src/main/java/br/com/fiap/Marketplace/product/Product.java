package br.com.fiap.Marketplace.product;

import br.com.fiap.Marketplace.orderItem.OrderItem;
import br.com.fiap.Marketplace.rating.Rating;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private BigDecimal price;

    @Size(max = 255)
    private String description;

    @NotNull
    private int stock;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;
    
}
