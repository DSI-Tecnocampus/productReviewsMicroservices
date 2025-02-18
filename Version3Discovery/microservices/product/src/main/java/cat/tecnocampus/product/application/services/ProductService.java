package cat.tecnocampus.product.application.services;

import java.util.List;
import java.util.Optional;

import cat.tecnocampus.product.application.ports.in.ProductCRUD;
import cat.tecnocampus.product.application.ports.out.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductService implements ProductCRUD {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        logger.info("Getting all products");
        return productRepository.getProducts();
    }

    @Override
    public Optional<Product> getProduct(long id) {
        logger.info("Getting product with id: " + id);
        return productRepository.getProduct(id);
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating product with name: " + product.getName());
        return productRepository.save(product);
    }
}
