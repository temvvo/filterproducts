package com.ecommerce.filterproducts.domain.port.service;

import com.ecommerce.filterproducts.domain.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

    Set<Product> getAll() throws IOException;
    List<Long> getProductsId() throws IOException;

}
