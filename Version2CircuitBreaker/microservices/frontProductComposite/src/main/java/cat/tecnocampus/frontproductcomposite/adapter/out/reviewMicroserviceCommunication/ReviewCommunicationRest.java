package cat.tecnocampus.frontproductcomposite.adapter.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.ports.out.reviewMicroserviceCommunication.ReviewMicroserviceCommunication;
import cat.tecnocampus.frontproductcomposite.application.services.Review;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class ReviewCommunicationRest implements ReviewMicroserviceCommunication {
    private final RestClient restClient;

    public ReviewCommunicationRest(@Qualifier("reviewRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    @TimeLimiter(name = "review")
    @CircuitBreaker(name = "review", fallbackMethod = "getReviewsFallbackValue")
    public List<Review> getReviewsFromProduct(long productId, int delay, int faultPercent) {
        return CompletableFuture.supplyAsync(
                () -> restClient.get()
                        .uri("/product/" + productId + "?delay=" + delay + "&faultPercent=" + faultPercent)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<Review>>() {
                        })
        ).get();
    }

    private CompletionStage<List<Review>> getReviewsFromProductTimeLimiter (long productId, int delay, int faultPercent) {
        return CompletableFuture.supplyAsync(
                () -> restClient.get()
                        .uri("/product/" + productId + "?delay=" + delay + "&faultPercent=" + faultPercent)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<Review>>() {
                        })
        );
    }

    @Override
    public Review createReview(Review review) {
        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON) // Set the content type to JSON
                .body(review) // Set the request body
                .retrieve()
                .body(Review.class); // Deserialize the response to ProductCompositereturn null;
    }

    private List<Review> getReviewsFallbackValue(long productId, int delay, int faultPercent, CallNotPermittedException e) {
        return List.of(new Review(0, "fallback", "fallback", 5));
    }
}
