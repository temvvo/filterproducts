package com.ecommerce.filterproducts.domain.port.file.system;

import com.ecommerce.filterproducts.domain.model.Size;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface ISizeFileSystem {
    Set<Size> getAll() throws IOException;

    Set<Size> getAllAndJoin(Map<String, String> stocks) throws IOException;
}
