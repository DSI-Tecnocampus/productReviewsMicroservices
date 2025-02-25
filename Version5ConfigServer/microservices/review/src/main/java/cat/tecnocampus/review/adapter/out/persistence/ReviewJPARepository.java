package cat.tecnocampus.review.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJPARepository extends JpaRepository<ReviewEntity, Long> {

    //get all reviews from a product given its id
    List<ReviewEntity> findByProductId(long productId);
}
