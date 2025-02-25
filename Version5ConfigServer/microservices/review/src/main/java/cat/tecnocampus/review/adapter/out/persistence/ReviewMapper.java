package cat.tecnocampus.review.adapter.out.persistence;

import cat.tecnocampus.review.application.services.Review;

public class ReviewMapper {
    public static ReviewEntity toEntity(Review review) {
        if (review == null) {
            return null;
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setId(review.getId());
        entity.setProductId(review.getProductId());
        entity.setAuthor(review.getAuthor());
        entity.setContent(review.getContent());
        entity.setRating(review.getRating());
        return entity;
    }

    public static Review toModel(ReviewEntity entity) {
        if (entity == null) {
            return null;
        }
        Review review = new Review();
        review.setId(entity.getId());
        review.setProductId(entity.getProductId());
        review.setAuthor(entity.getAuthor());
        review.setContent(entity.getContent());
        review.setRating(entity.getRating());
        return review;
    }
}