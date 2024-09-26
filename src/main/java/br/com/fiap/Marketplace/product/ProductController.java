package br.com.fiap.Marketplace.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    final PagedResourcesAssembler<Product> pageAssembler;
    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService, PagedResourcesAssembler<Product> pageAssembler) {
        this.productService = productService;
        this.pageAssembler = pageAssembler;
    }

    @GetMapping
    public PagedModel<EntityModel<Product>> getProducts(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> page = productService.findAll(pageable);
        return pageAssembler.toModel(page, Product::toEntityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product.toEntityModel());
    }

    @GetMapping("/filterName")
    public PagedModel<EntityModel<Product>> findByProductByName(@RequestParam String name, @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> page = productService.findByProductByName(name, pageable);
        return pageAssembler.toModel(page, Product::toEntityModel);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
