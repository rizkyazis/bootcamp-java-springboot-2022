package com.manusiaikan.bootcamp.controller.api;


import com.manusiaikan.bootcamp.model.DataTableRequest;
import com.manusiaikan.bootcamp.model.DataTableResponse;
import com.manusiaikan.bootcamp.model.Product;
import com.manusiaikan.bootcamp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @PostMapping("")
    public ResponseEntity< DataTableResponse<Product>> list(@RequestBody DataTableRequest request) {

        DataTableResponse<Product> response = new DataTableResponse<>();
        response.setData(productRepo.list(request));
        response.setDraw(request.getDraw());
        Long total = productRepo.countProduct(request);
        response.setRecordsFiltered(total);
        response.setRecordsTotal(total);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) throws EmptyResultDataAccessException {
        Product product = productRepo.findById(id);
        return ResponseEntity.ok(product);
    }

}
