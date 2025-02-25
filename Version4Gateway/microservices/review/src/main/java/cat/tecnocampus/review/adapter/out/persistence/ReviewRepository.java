package cat.tecnocampus.review.adapter.out.persistence;

import cat.tecnocampus.review.application.services.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository implements cat.tecnocampus.review.application.ports.out.persistence.ReviewRepository {
    private final ReviewJPARepository reviewJPARepository;

    public ReviewRepository(ReviewJPARepository reviewJPARepository) {
        this.reviewJPARepository = reviewJPARepository;
    }

    @Override
    public List<Review> getReviewFromProduct(long productId) {
        return reviewJPARepository.findByProductId(productId).stream()
                .map(ReviewMapper::toModel)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Optional<Review> getReview(long id) {
        return reviewJPARepository.findById(id)
                .map(ReviewMapper::toModel);
    }

    @Override
    public Review save(Review review) {
        return ReviewMapper.toModel(reviewJPARepository.save(ReviewMapper.toEntity(review)));
    }
}
