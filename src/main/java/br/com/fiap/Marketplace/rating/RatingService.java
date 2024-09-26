package br.com.fiap.Marketplace.rating;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }
    
}
