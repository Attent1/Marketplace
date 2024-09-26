package br.com.fiap.Marketplace.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("rating")
public class RatingController {

    private final PagedResourcesAssembler<Rating> pageAssembler;
    private final RatingService ratingService;

    @Autowired
    public RatingController(PagedResourcesAssembler<Rating> pageAssembler, RatingService ratingService) {
        this.pageAssembler = pageAssembler;
        this.ratingService = ratingService;
    }

    @GetMapping
    public PagedModel<EntityModel<Rating>> getRatings(@PageableDefault(sort = "score", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Rating> page = ratingService.findAll(pageable);
        return pageAssembler.toModel(page, Rating::toEntityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rating>> getRatingById(@PathVariable UUID id) {
        Rating rating = ratingService.findById(id);
        return ResponseEntity.ok(rating.toEntityModel());
    }

    @GetMapping("score")
    public PagedModel<EntityModel<Rating>> getRatingByScore(@RequestParam int score, @PageableDefault(sort = "score", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Rating> page = ratingService.findByRatingScore(score, pageable);
        return pageAssembler.toModel(page, Rating::toEntityModel);
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable UUID id, @RequestBody Rating rating) {
        Rating updatedRating = ratingService.updateRating(id, rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable UUID id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
