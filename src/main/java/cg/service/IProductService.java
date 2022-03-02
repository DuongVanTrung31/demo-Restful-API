package cg.service;

import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findProductByNameContaining(String name, Pageable pageable);



    Page<Product> findAllByCategory(Long id, Pageable pageable);
}
