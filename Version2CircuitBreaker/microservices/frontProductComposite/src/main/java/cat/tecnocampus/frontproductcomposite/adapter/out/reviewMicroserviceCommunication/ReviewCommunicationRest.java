package cat.tecnocampus.frontproductcomposite.adapter.out.reviewMicroserviceCommunication;

import cat.tecnocampus.frontproductcomposite.application.ports.out.reviewMicroserviceCommunication.ReviewMicroserviceCommunication;
import cat.tecnocampus.frontproductcomposite.application.services.Review;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ReviewCommunicationRest implements ReviewMicroserviceCommunication {
    private final RestClient restClient;
    private final TimeLimiterCircuitBreakerCall timeLimiterCircuitBreakerCall;

    public ReviewCommunicationRest(RestClient.Builder restClientBuilder, TimeLimiterCircuitBreakerCall timeLimiterCircuitBreakerCall,
                                   @Value("${app.review-service.host}") String reviewServiceHost,
                                   @Value("${app.review-service.port}") String reviewServicePort) {
        this.restClient = restClientBuilder.baseUrl("http://" + reviewServiceHost + ":" + reviewServicePort +"/reviews").build();
        this.timeLimiterCircuitBreakerCall = timeLimiterCircuitBreakerCall;
    }

    @Override
    public List<Review> getReviewsFromProduct(long productId, int delay, int faultPercent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Wait for the CompletableFuture to complete and get the result
            CompletableFuture<List<Review>> future = timeLimiterCircuitBreakerCall.getReviewsFromProduct(productId, delay, faultPercent);
            List<Review> reviews = future.get(); // Blocking call to get the result
            return reviews;
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions and return the fallback value
            System.out.println("Exception " +
                    e.getMessage() +
                    " on thread " +
                    Thread.currentThread().getName() +
                    " at " +
                    LocalDateTime.now().format(formatter));
            return getReviewsFallbackValueTimeLimiter(productId, delay, faultPercent, e);
        }
    }


    @Override
    public Review createReview(Review review) {
        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON) // Set the content type to JSON
                .body(review) // Set the request body
                .retrieve()
                .body(Review.class); // Deserialize the response to ProductCompositereturn null;
    }

    private List<Review> getReviewsFallbackValueTimeLimiter(long productId, int delay, int faultPercent, Exception e) {
        return List.of(new Review(0, "Time Limiter fallback", "TL fallback", 5));
    }
}
