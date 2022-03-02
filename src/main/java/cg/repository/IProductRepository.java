package cg.repository;

import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findProductByNameContaining(String name, Pageable pageable);
    Page<Product> findAllByCategory_Id(Long id, Pageable pageable);
}
