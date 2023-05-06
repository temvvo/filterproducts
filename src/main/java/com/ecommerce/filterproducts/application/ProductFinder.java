package com.ecommerce.filterproducts.application;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.domain.model.Size;
import com.ecommerce.filterproducts.domain.port.file.system.IProductFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.ISizeFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.IStockFileSystem;
import com.ecommerce.filterproducts.domain.port.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductFinder implements ProductService {
    private final IProductFileSystem IProductFileSystem;
    private final IStockFileSystem IStockFileSystem;
    private final ISizeFileSystem ISizeFileSystem;

    public ProductFinder(IProductFileSystem iProductFileSystem, IStockFileSystem iStockFileSystem, ISizeFileSystem iSizeFileSystem) {
        IProductFileSystem = iProductFileSystem;
        IStockFileSystem = iStockFileSystem;
        ISizeFileSystem = iSizeFileSystem;
    }

    @Override
    public Set<Product> getAll() throws IOException {
        return IProductFileSystem.getAll();
    }

    @Override
    public List<Long> getVisibleProducts() throws IOException {
        Set<Product> products = IProductFileSystem.getAll();
        Map<String, String> stocks = IStockFileSystem.getAllMapped();
        Set<Size> sizes = ISizeFileSystem.getAllAndJoin(stocks);

        List<Long> visibleProductIds = products.stream()
                .filter(product -> isProductVisible(product.getId(), sizes))
                .sorted(Comparator.comparingLong(Product::getSequence))
                .map(Product::getId)
                .collect(Collectors.toList());
        log.info("Visible products: {}",
                String.join(",",visibleProductIds.stream().map(Object::toString).toArray(String[]::new)));
        return visibleProductIds;
    }

    private boolean isProductVisible(Long productId, Set<Size> sizes) {
       Set<Size> productSizes = sizes.stream().filter(size -> size.getProductId().equals(productId)).collect(Collectors.toSet());
        boolean hasSpecialSize = false;
        boolean hasNonSpecialSize = false;
        for (Size size : productSizes) {
           // First rule: if size has stock or size is marked as backSoon, product will be visible
           if ((size.getQuantity() > 0) || size.getBackSoon()) {
               if (size.getSpecial()) {
                   hasSpecialSize = true;
               } else {
                   hasNonSpecialSize = true;
               }
           }
       }
        // Second rule: if product has special size product will be visible
        // if at least one special size have stock and at least one not special size has stock
       if ((hasSpecialSize && hasNonSpecialSize) || (!hasSpecialSize && hasNonSpecialSize)) {
            return true;
       }

       return false;
    }
}
