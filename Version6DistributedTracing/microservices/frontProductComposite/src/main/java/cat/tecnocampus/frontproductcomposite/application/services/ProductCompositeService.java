package cat.tecnocampus.frontproductcomposite.application.services;

import cat.tecnocampus.frontproductcomposite.application.ports.in.ProductCompositeCRUD;
import cat.tecnocampus.frontproductcomposite.application.ports.out.productMicroserviceCommunication.ProductMicroserviceCommunication;
import cat.tecnocampus.frontproductcomposite.application.ports.out.reviewMicroserviceCommunication.ReviewMicroserviceCommunication;

import java.util.List;
import java.util.Optional;

public class ProductCompositeService implements ProductCompositeCRUD {
    private final ProductMicroserviceCommunication productMicroserviceCommunication;
    private final ReviewMicroserviceCommunication reviewMicroserviceCommunication;

    public ProductCompositeService(ProductMicroserviceCommunication productMicroserviceCommunication, ReviewMicroserviceCommunication reviewMicroserviceCommunication) {
        this.productMicroserviceCommunication = productMicroserviceCommunication;
        this.reviewMicroserviceCommunication = reviewMicroserviceCommunication;
    }

    @Override
    public List<ProductComposite> getProducts() {
        var products = productMicroserviceCommunication.getProducts();
        products.forEach(product -> product.setReviews(reviewMicroserviceCommunication.getReviewsFromProduct(product.getId(), 0, 0)));
        return products;
    }

    @Override
    public Optional<ProductComposite> getProduct(long id, int delay, int faultPercent) {
        var product = productMicroserviceCommunication.getProduct(id);
        return product.map(p -> {
            p.setReviews(reviewMicroserviceCommunication.getReviewsFromProduct(p.getId(), delay, faultPercent));
            return p;
        });
    }

    @Override
    public ProductComposite createProduct(ProductComposite product) {
        var newProduct = productMicroserviceCommunication.createProduct(product);
        product.getReviews()
                .forEach(review -> {review.setProductId(newProduct.getId());
                    reviewMicroserviceCommunication.createReview(review);});
        return newProduct;
    }
}
