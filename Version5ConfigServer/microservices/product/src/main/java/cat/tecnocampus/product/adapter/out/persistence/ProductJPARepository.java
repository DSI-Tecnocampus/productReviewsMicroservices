package cat.tecnocampus.product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
