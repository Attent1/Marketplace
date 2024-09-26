package br.com.fiap.Marketplace.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByProductByName(String name, Pageable pageable) {
        return productRepository.findByProductByName(name, pageable);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product product) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.setId(id);
        return productRepository.save(product);
    }

    public Product findById(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.deleteById(id);
    }
}
