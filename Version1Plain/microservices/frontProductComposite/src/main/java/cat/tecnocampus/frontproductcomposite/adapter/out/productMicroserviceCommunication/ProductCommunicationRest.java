package cat.tecnocampus.frontproductcomposite.adapter.out.productMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.ports.out.productMicroserviceCommunication.ProductMicroserviceCommunication;
import cat.tecnocampus.frontproductcomposite.application.services.ProductComposite;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
public class ProductCommunicationRest implements ProductMicroserviceCommunication {
    private final RestClient restClient;
    private final String productServiceHost;
    private final String productServicePort;

    public ProductCommunicationRest(RestClient.Builder restClientBuilder, @Value("${app.product-service.host}") String productServiceHost,
                                    @Value("${app.product-service.port}") String productServicePort) {
        this.productServiceHost = productServiceHost;
        this.productServicePort = productServicePort;
        this.restClient = restClientBuilder.baseUrl("http://" + productServiceHost + ":" + productServicePort + "/products").build();
    }

    @Override
    public List<ProductComposite> getProducts() {
        return restClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductComposite>>() {
                });
    }

    @Override
    public Optional<ProductComposite> getProduct(long id) {
        try {
            return Optional.ofNullable(
                    restClient.get()
                            .uri("/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .body(ProductComposite.class)
            );
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                return Optional.empty();
            }
            throw e;
        }
    }

    @Override
    public ProductComposite createProduct(ProductComposite product) {
        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON) // Set the content type to JSON
                .body(product) // Set the request body
                .retrieve()
                .body(ProductComposite.class); // Deserialize the response to ProductComposite
    }
}
