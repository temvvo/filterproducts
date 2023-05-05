package com.ecommerce.filterproducts.domain.port.file.system;

import com.ecommerce.filterproducts.domain.model.Size;

import java.io.IOException;
import java.util.Set;

public interface ISizeFileSystem {
    Set<Size> getAll() throws IOException;
}
