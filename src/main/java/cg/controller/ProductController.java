package cg.controller;

import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAllProducts() {
        Iterable<Product> products = productService.findAll();
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Iterable<Product>> findAllProductsByCategory(@PathVariable Long id) {
        Iterable<Product> products = productService.findAllByCategory(id);
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProductsByName(String name){
        Iterable<Product> products = productService.findProductByNameContaining(name);
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
