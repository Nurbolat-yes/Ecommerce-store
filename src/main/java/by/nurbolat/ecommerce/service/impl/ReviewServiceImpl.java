package by.nurbolat.ecommerce.service.impl;

import by.nurbolat.ecommerce.db.entity.Review;
import by.nurbolat.ecommerce.db.repository.ReviewRepository;
import by.nurbolat.ecommerce.exception.ReviewNotFoundException;
import by.nurbolat.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> getReview(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    @Override
    public Review addReview(Review review) {
        review.setCreatedAt(LocalDate.now());
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) throws ReviewNotFoundException {

        var maybeReview = reviewRepository.findById(review.getId());

        if (maybeReview.isEmpty()){
            throw new ReviewNotFoundException();
        }

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) throws ReviewNotFoundException {

        if(reviewRepository.findById(id).isPresent())
            reviewRepository.deleteById(id);
        else
            throw new ReviewNotFoundException();
    }

}
