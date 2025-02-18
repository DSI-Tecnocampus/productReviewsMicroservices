package cat.tecnocampus.review.application.ports.in;

import cat.tecnocampus.review.application.services.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewCRUD {

    List<Review> getReviewsFromProduct(long productId, int delay, int faultPercent);
    Optional<Review> getReview(long id);
    Review createReview(Review review);
}
