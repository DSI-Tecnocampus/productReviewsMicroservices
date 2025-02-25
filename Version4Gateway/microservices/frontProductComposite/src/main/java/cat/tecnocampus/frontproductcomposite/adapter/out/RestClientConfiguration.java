package cat.tecnocampus.frontproductcomposite.adapter.out;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
    private final String productServiceUrl = "http://product";

    @Bean("productRestClient")
    @LoadBalanced
    public RestClient.Builder restClient() {
        return RestClient.builder();
    }
}
