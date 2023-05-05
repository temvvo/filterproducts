package com.ecommerce.filterproducts.configuration;

import com.ecommerce.filterproducts.domain.port.file.system.IProductFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.ISizeFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.IStockFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.ProductFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.SizeFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.StockFileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public IProductFileSystem productFileSystem() {
        return new ProductFileSystem();
    }
    @Bean
    public IStockFileSystem stockFileSystem() {
        return new StockFileSystem();
    }

    @Bean
    public ISizeFileSystem sizeFileSystem() {
        return new SizeFileSystem();
    }
}
