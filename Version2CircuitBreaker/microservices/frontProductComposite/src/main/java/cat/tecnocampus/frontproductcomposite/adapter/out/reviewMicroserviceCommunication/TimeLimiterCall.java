package cat.tecnocampus.frontproductcomposite.adapter.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.services.Review;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TimeLimiterCall {
    private final RestClient restClient;

    public TimeLimiterCall(@Qualifier("reviewRestClient") RestClient restClient) {
        this.restClient = restClient;
    }


    @TimeLimiter(name = "review")
    public CompletableFuture<List<Review>> getReviewsFromProductTimeLimiter (long productId, int delay, int faultPercent) {
        return CompletableFuture.supplyAsync(
                () -> restClient.get()
                        .uri("/product/" + productId + "?delay=" + delay + "&faultPercent=" + faultPercent)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<Review>>() {
                        })
        );
    }
}
