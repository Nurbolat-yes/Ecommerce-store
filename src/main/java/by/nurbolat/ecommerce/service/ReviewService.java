package by.nurbolat.ecommerce.service;

import by.nurbolat.ecommerce.db.entity.Review;
import by.nurbolat.ecommerce.exception.ReviewNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<Review> getReview(Long id);

    List<Review> getReviews();

    Review addReview(Review review);

    Review updateReview(Review review) throws ReviewNotFoundException;

    void deleteReview(Long id) throws ReviewNotFoundException;

}
