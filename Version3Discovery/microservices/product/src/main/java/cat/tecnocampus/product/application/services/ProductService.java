package cat.tecnocampus.product.application.services;

import java.util.List;
import java.util.Optional;

import cat.tecnocampus.product.application.ports.in.ProductCRUD;
import cat.tecnocampus.product.application.ports.out.persistence.ProductRepository;

public class ProductService implements ProductCRUD {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return productRepository.getProduct(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
