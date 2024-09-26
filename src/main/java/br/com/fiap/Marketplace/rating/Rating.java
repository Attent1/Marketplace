package br.com.fiap.Marketplace.rating;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.fiap.Marketplace.product.Product;
import br.com.fiap.Marketplace.user.User;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.EntityModel;

import java.util.UUID;

@Data
@Entity(name = "RATING")
public class Rating {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @Min(1) @Max(5)
    private int score;

    @Size(max = 500)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public EntityModel<Rating> toEntityModel() {
        EntityModel<Rating> entityModel = EntityModel.of(this);
        entityModel.add(linkTo(methodOn(RatingController.class).getRatings(null)).withRel("ratings"));
        entityModel.add(linkTo(methodOn(RatingController.class).getRatingById(id)).withRel("getRatingById"));
        entityModel.add(linkTo(methodOn(RatingController.class).deleteRating(id)).withRel("deleteRating"));
        return entityModel;
    }
    
}
