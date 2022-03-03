package cg.service;

import cg.model.Product;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findProductByNameContaining(String name);
    Iterable<Product> findAllByCategory(Long id);
}
