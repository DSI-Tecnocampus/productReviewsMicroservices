package cat.tecnocampus.frontproductcomposite.adapter.in.restAPI;

import cat.tecnocampus.frontproductcomposite.application.services.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {
    public static List<ReviewListWeb> mapReviewsToWeb(List<Review> reviews) {
        return reviews.stream().map(r -> new ReviewListWeb(r.getId(), r.getAuthor(), r.getContent(), r.getRating())).collect(Collectors.toList());
    }

    public static List<Review> mapWebToReviews(List<ReviewListWeb> reviews) {
        return reviews.stream().map(r -> new Review(r.getAuthor(), r.getContent(), r.getRating())).collect(Collectors.toList());
    }
}
