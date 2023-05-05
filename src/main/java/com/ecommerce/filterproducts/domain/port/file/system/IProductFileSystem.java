package com.ecommerce.filterproducts.domain.port.file.system;

import com.ecommerce.filterproducts.domain.model.Product;

import java.io.IOException;
import java.util.Set;

public interface IProductFileSystem {
    Set<Product> getAll() throws IOException;

}
