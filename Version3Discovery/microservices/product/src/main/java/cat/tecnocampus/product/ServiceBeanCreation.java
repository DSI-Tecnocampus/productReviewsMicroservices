package cat.tecnocampus.product;

import cat.tecnocampus.product.adapter.out.persistence.ProductRepository;
import cat.tecnocampus.product.application.ports.in.ProductCRUD;
import cat.tecnocampus.product.application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanCreation {

    @Autowired
    private ProductRepository productRepository;
    //create a bean of type ProductCRUD
    @Bean
    public ProductCRUD productCRUD() {
        return new ProductService(productRepository);
    }

}
