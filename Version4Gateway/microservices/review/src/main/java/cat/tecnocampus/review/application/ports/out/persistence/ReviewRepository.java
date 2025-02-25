package cat.tecnocampus.review.application.ports.out.persistence;

import cat.tecnocampus.review.application.services.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    List<Review> getReviewFromProduct(long productId);
    Optional<Review> getReview(long id);
    Review save(Review review);
}
