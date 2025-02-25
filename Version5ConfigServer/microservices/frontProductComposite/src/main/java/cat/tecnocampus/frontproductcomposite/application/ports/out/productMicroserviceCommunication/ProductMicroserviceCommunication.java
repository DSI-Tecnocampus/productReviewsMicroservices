package cat.tecnocampus.frontproductcomposite.application.ports.out.productMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.services.ProductComposite;

import java.util.List;
import java.util.Optional;

public interface ProductMicroserviceCommunication {

    List<ProductComposite> getProducts();
    Optional<ProductComposite> getProduct(long id);
    ProductComposite createProduct(ProductComposite product);
}
