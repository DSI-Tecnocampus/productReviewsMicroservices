package cat.tecnocampus.frontproductcomposite.application.services;

import cat.tecnocampus.frontproductcomposite.application.ports.out.productMicroserviceCommunication.ProductMicroserviceCommunication;
import cat.tecnocampus.frontproductcomposite.application.ports.out.reviewMicroserviceCommunication.ReviewMicroserviceCommunication;

public class AddReviewToProductService implements cat.tecnocampus.frontproductcomposite.application.ports.in.AddReviewToProduct {
    private final ProductMicroserviceCommunication productMicroserviceCommunication;
    private final ReviewMicroserviceCommunication reviewMicroserviceCommunication;

    public AddReviewToProductService(ProductMicroserviceCommunication productMicroserviceCommunication, ReviewMicroserviceCommunication reviewMicroserviceCommunication) {
        this.productMicroserviceCommunication = productMicroserviceCommunication;
        this.reviewMicroserviceCommunication = reviewMicroserviceCommunication;
    }

    @Override
    public void addReviewToProduct(Review review) {
        productMicroserviceCommunication.getProduct(review.getProductId())
                .ifPresentOrElse(product -> {
                    reviewMicroserviceCommunication.createReview(review);
                }, () -> {
                    throw new ProductDoesNotExistException(review.getProductId());
                });
    }
}
