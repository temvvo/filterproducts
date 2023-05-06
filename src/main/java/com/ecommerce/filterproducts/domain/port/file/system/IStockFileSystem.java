package com.ecommerce.filterproducts.domain.port.file.system;

import com.ecommerce.filterproducts.domain.model.Stock;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface IStockFileSystem {
    Set<Stock> getAll() throws IOException;
    Map<String,String> getAllMapped() throws IOException;

}
