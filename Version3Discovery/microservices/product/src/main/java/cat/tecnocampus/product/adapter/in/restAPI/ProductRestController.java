package cat.tecnocampus.product.adapter.in.restAPI;

import cat.tecnocampus.product.application.ports.in.ProductCRUD;
import cat.tecnocampus.product.application.services.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductRestController {
    private final ProductCRUD productService;

    public ProductRestController(ProductCRUD productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductListWeb> getProducts() {
        return productService.getProducts().stream()
                .map(p -> new ProductListWeb(p.getId(),p.getName(),p.getDescription())).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductListWeb getProduct(@PathVariable long id) {
        return productService.getProduct(id)
                .map(p -> new ProductListWeb(p.getId(),p.getName(),p.getDescription()))
                .orElseThrow(() -> new ProductDoesNotExistException(id));
    }

    @PostMapping("/products")
    public ProductListWeb createProduct(@RequestBody ProductListWeb product) {
        var newProduct = productService.createProduct(new Product(product.getName(),product.getDescription()));
        return new ProductListWeb(newProduct.getId(),newProduct.getName(),newProduct.getDescription());
    }
}
