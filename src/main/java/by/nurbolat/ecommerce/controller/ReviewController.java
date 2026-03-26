package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.db.entity.Review;
import by.nurbolat.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(value = "/add-review/{id}")
    public String getAddReviewPage(@PathVariable Long id, Model model){

        model.addAttribute("productId",id);

        return "add-review";
    }

    @PostMapping(value = "/add-review/{product_id}")
    public String addReview(Review review){

        reviewService.addReview(review);

        return "redirect:/products/{product_id}";
    }
}
