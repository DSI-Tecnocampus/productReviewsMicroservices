package cat.tecnocampus.product.application.ports.out.persistence;

import cat.tecnocampus.product.application.services.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProducts();
    Optional<Product> getProduct(long id);
    Product save(Product product);
}
