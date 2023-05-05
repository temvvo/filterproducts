package com.ecommerce.filterproducts.application;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.domain.model.Size;

import com.ecommerce.filterproducts.domain.model.Stock;
import com.ecommerce.filterproducts.domain.port.file.system.IProductFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.ISizeFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.IStockFileSystem;
import com.ecommerce.filterproducts.domain.port.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<Long> getProductsId() throws IOException {
        Set<Product> products = IProductFileSystem.getAll();
        Set<Stock> stocks = IStockFileSystem.getAll();
        Set<Size> sizes = ISizeFileSystem.getAll();

        List<Long> visibleProductIds = products.stream()
                .filter(product -> isProductVisible(product, sizes, stocks))
                .sorted(Comparator.comparingLong(Product::getSequence))
                .map(Product::getId)
                .collect(Collectors.toList());

        System.out.println(String.join(",", visibleProductIds.stream().map(Object::toString).toArray(String[]::new)));

        return visibleProductIds;
    }

    private static boolean isProductVisible(Product product, Set<Size> sizes, Set<Stock> stocks) {
        List<Size> productSizes = sizes.stream().filter(size -> size.getProductId().equals(product.getId())).toList();
        boolean hasSpecialSize = false;
        boolean hasNonSpecialSize = false;

        for (Size size : productSizes) {
            Stock stock = stocks.stream().filter(s -> s.getSizeId().equals(size.getId())).findFirst().orElse(null);
            if ((stock != null && stock.getQuantity() > 0) || size.getBackSoon()) {
                if (size.getSpecial()) {
                    hasSpecialSize = true;
                } else {
                    hasNonSpecialSize = true;
                }
            }
        }
        if(hasSpecialSize && !hasNonSpecialSize) {
            return false;
        }
        if (!hasSpecialSize && hasNonSpecialSize) {
            return true;
        }
        return hasSpecialSize && hasNonSpecialSize;
    }
}
