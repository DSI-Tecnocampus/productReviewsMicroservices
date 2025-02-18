package cat.tecnocampus.frontproductcomposite.adapter.in.restAPI;

import cat.tecnocampus.frontproductcomposite.application.ports.in.AddReviewToProduct;
import cat.tecnocampus.frontproductcomposite.application.services.Review;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddReviewToProductRestController {
    private final AddReviewToProduct addReviewToProduct;

    public AddReviewToProductRestController(AddReviewToProduct addReviewToProduct) {
        this.addReviewToProduct = addReviewToProduct;
    }

    @PostMapping("/products/{productId}/reviews")
    public void addReviewToProduct(@RequestBody ReviewListWeb review, @PathVariable long productId) {
        addReviewToProduct.addReviewToProduct(new Review(productId, review.getAuthor(), review.getContent(), review.getRating()));
    }
}
