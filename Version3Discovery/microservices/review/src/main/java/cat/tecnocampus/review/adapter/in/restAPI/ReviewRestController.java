package cat.tecnocampus.review.adapter.in.restAPI;

import cat.tecnocampus.review.application.ports.in.ReviewCRUD;
import cat.tecnocampus.review.application.services.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReviewRestController {
    private final ReviewCRUD reviewService;

    public ReviewRestController(ReviewCRUD reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/product/{productId}")
    public List<ReviewListWeb> getReviewsFromProduct(@PathVariable long productId,
                                                     @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
                                                     @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    ) {
        System.out.println("delay: " + delay + " faultPercent: " + faultPercent);
        return reviewService.getReviewsFromProduct(productId, delay, faultPercent).stream()
                .map(r -> new ReviewListWeb(r.getId(),r.getProductId(), r.getAuthor(), r.getContent(), r.getRating())).collect(Collectors.toList());
    }

    @GetMapping("/reviews/{id}")
    public ReviewListWeb getReview(@PathVariable long id) {
        return reviewService.getReview(id)
                .map(r -> new ReviewListWeb(r.getId(),r.getProductId(), r.getAuthor(), r.getContent(), r.getRating()))
                .orElseThrow(() -> new ReviewDoesNotExistException(id));
    }

    @PostMapping("/reviews")
    public void createReview(@RequestBody ReviewListWeb review) {
        reviewService.createReview(new Review(review.getProductId(), review.getAuthor(), review.getContent(), review.getRating()));
    }
}
