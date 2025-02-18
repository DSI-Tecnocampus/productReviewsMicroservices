package cat.tecnocampus.product.application.ports.in;

import cat.tecnocampus.product.application.services.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCRUD {

    List<Product> getProducts();
    Optional<Product> getProduct(long id);
    Product createProduct(Product product);
}
