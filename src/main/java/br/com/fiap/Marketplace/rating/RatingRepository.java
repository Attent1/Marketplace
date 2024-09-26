package br.com.fiap.Marketplace.rating;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    @Query("SELECT rt FROM RATING rt WHERE rt.score >= :score")
    Page<Rating> findByRatingScore(@Param("score") int score, Pageable pageable);
}
