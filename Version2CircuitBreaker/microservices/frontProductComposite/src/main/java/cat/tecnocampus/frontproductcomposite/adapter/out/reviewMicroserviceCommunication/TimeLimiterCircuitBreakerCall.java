package cat.tecnocampus.frontproductcomposite.adapter.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.services.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TimeLimiterCircuitBreakerCall {
    private final RestClient restClient;

    public TimeLimiterCircuitBreakerCall(RestClient.Builder restClientBuilder,
                                   @Value("${app.review-service.host}") String reviewServiceHost,
                                   @Value("${app.review-service.port}") String reviewServicePort) {
        this.restClient = restClientBuilder.baseUrl("http://" + reviewServiceHost + ":" + reviewServicePort +"/reviews").build();
    }

    @TimeLimiter(name = "review")
    @CircuitBreaker(name = "review", fallbackMethod = "getReviewsFallbackValueCircuitBreaker")
    public CompletableFuture<List<Review>> getReviewsFromProduct(long productId, int delay, int faultPercent) {
        return CompletableFuture.supplyAsync(
                () -> restClient.get()
                        .uri("/product/" + productId + "?delay=" + delay + "&faultPercent=" + faultPercent)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<Review>>() {
                        })
        );
    }

    private CompletableFuture<List<Review>> getReviewsFallbackValueCircuitBreaker(long productId, int delay, int faultPercent, Throwable e) {
        return CompletableFuture.completedFuture(List.of(new Review(0, "Circuit breaker fallback", "CB fallback", 5)));
    }


}
