package cat.tecnocampus.frontproductcomposite.application.ports.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.services.Review;

import java.util.List;

public interface ReviewMicroserviceCommunication {

    List<Review> getReviewsFromProduct(long productId, int delay, int faultPercent);
    Review createReview(Review product);
}
