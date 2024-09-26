package br.com.fiap.Marketplace.rating;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RatingService {

    final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }

    public Rating findById(UUID id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Page<Rating> findByRatingScore(int score, Pageable pageable) {
        return ratingRepository.findByRatingScore(score, pageable);
    }

    public Rating updateRating(UUID id, Rating rating) {
        ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating Not Found"));
        rating.setId(id);
        return ratingRepository.save(rating);
    }

    public void deleteRating(UUID id) {
        ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating Not Found"));
        ratingRepository.deleteById(id);
    }
}
