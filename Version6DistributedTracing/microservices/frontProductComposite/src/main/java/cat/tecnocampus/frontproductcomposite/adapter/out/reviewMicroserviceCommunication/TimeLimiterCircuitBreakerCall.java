package cat.tecnocampus.frontproductcomposite.adapter.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.services.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.Observation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TimeLimiterCircuitBreakerCall {
    private final RestClient restClient;
    private final ObservationRegistry observationRegistry;

    public TimeLimiterCircuitBreakerCall(
            @Qualifier("myRestClientBuilder") RestClient.Builder restClientBuilder,
            ObservationRegistry observationRegistry) {
        this.restClient = restClientBuilder.baseUrl("http://review/reviews").build();
        this.observationRegistry = observationRegistry;
    }

    @TimeLimiter(name = "review")
    @CircuitBreaker(name = "review", fallbackMethod = "getReviewsFallbackValueCircuitBreaker")
    public CompletableFuture<List<Review>> getReviewsFromProduct(long productId, int delay, int faultPercent) {
        Observation observation = Observation.createNotStarted("review.request", observationRegistry);
        return CompletableFuture.supplyAsync(() -> {
            try (Observation.Scope scope = observation.start().openScope()) {
                return executeRequest(productId, delay, faultPercent);
            } catch (Exception e) {
                observation.error(e);
                throw e;
            } finally {
                observation.stop();
            }
        });
    }

    private List<Review> executeRequest(long productId, int delay, int faultPercent) {
        return restClient.get()
                .uri("/product/" + productId + "?delay=" + delay + "&faultPercent=" + faultPercent)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Review>>() {});
    }

    private CompletableFuture<List<Review>> getReviewsFallbackValueCircuitBreaker(long productId, int delay, int faultPercent, Throwable e) {
        return CompletableFuture.completedFuture(List.of(new Review(0, "Circuit breaker fallback", "CB fallback", 5)));
    }
}