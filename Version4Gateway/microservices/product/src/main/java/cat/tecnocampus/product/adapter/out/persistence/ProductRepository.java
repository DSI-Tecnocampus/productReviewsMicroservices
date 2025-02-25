package cat.tecnocampus.product.adapter.out.persistence;

import cat.tecnocampus.product.application.services.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements cat.tecnocampus.product.application.ports.out.persistence.ProductRepository {
    private final ProductJPARepository productJPARepository;

    public ProductRepository(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<Product> getProducts() {
        return productJPARepository.findAll().stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return productJPARepository.findById(id)
                .map(ProductMapper::toModel);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        return ProductMapper.toModel(productJPARepository.save(entity));
    }
}
