package br.com.fiap.Marketplace.product;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    public EntityModel<Product> toEntityModel() {
        EntityModel<Product> productModel = EntityModel.of(this);
        productModel.add(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel());
        productModel.add(linkTo(methodOn(ProductController.class).getProducts(null)).withRel("products"));
        return productModel;
    }

}
