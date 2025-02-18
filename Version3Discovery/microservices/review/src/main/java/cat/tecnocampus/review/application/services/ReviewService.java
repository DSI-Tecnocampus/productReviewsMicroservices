package cat.tecnocampus.review.application.services;

import cat.tecnocampus.review.application.ports.in.ReviewCRUD;
import cat.tecnocampus.review.application.ports.out.persistence.ReviewRepository;

import java.util.List;
import java.util.Random;
import java.util.Optional;

public class ReviewService implements ReviewCRUD {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewsFromProduct(long productId, int delay, int faultPercent) {
        throwErrorIfBadLuck(faultPercent);
        simulateDelay(delay);
        return reviewRepository.getReviewFromProduct(productId);
    }

    @Override
    public Optional<Review> getReview(long id) {
        return reviewRepository.getReview(id);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    private void throwErrorIfBadLuck(int faultPercent) {
        if (faultPercent == 0) {
            return;
        }
        int randomThreshold = getRandomNumber(1, 100);
        if (faultPercent < randomThreshold) {
            System.out.println("We got lucky, no error occurred, %d < %d".formatted(faultPercent, randomThreshold));

        } else {
            System.out.println("Bad luck, an error occurred, %d >= %d".formatted(faultPercent, randomThreshold));
            throw new RuntimeException("Something went wrong...");
        }
    }

    private final Random randomNumberGenerator = new Random();

    private int getRandomNumber(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return randomNumberGenerator.nextInt((max - min) + 1) + min;
    }

    private static void simulateDelay(int delay) {
        // Simulate a delay in the response
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
