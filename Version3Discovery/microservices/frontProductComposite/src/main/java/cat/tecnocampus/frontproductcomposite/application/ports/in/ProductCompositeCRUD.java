package cat.tecnocampus.frontproductcomposite.application.ports.in;

import cat.tecnocampus.frontproductcomposite.application.services.ProductComposite;

import java.util.List;
import java.util.Optional;

public interface ProductCompositeCRUD {

    List<ProductComposite> getProducts();
    Optional<ProductComposite> getProduct(long id, int delay, int faultPercent);
    ProductComposite createProduct(ProductComposite product);
}
