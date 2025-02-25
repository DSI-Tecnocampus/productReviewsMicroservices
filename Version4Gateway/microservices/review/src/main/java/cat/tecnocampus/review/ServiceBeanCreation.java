package cat.tecnocampus.review;

import cat.tecnocampus.review.application.ports.in.ReviewCRUD;
import cat.tecnocampus.review.application.ports.out.persistence.ReviewRepository;
import cat.tecnocampus.review.application.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanCreation {

    @Autowired
    private ReviewRepository reviewRepository;

    @Bean
    public ReviewCRUD reviewCRUD() {
        return new ReviewService(reviewRepository);
    }

}
