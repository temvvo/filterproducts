package com.ecommerce.filterproducts.infrastructure.adapter.api.controller;

import com.ecommerce.filterproducts.domain.port.service.ProductService;
import com.ecommerce.filterproducts.infrastructure.adapter.api.ProductApi;
import com.ecommerce.filterproducts.infrastructure.adapter.api.mapper.ProductApiMapper;
import com.ecommerce.filterproducts.infrastructure.adapter.api.model.ProductDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Generated(value = "org.openapitools.codegen.products.SpringCodegen", date = "2023-05-02T00:59:23.088864-03:00[America/Argentina/Buenos_Aires]")
@Controller
@RequestMapping("${openapi.filterproducts.base-path:/filter-products/api/v1}")
public class ProductsApiController implements ProductApi {

    private final NativeWebRequest request;

    private final ProductApiMapper mapper= Mappers.getMapper(ProductApiMapper.class);

    @Autowired
    private final ProductService service;

    @Autowired
    public ProductsApiController(NativeWebRequest request, ProductService service) {
        this.request = request;
        this.service = service;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    @Override
    public ResponseEntity<Set<ProductDto>> getProducts(
            @Parameter(name = "Accept-Version", description = "Header parameter that indicates the API version the client is working with", in = ParameterIn.HEADER) @RequestHeader(value = "Accept-Version", required = false) String acceptVersion,
            @Parameter(name = "Accept", description = "Header parameter that advertises which content types, expressed as MIME types, the client is able to understand", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept
    ) throws IOException {
        return new ResponseEntity<>(service.getAll().stream().map(mapper::convert).collect(Collectors.toSet()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Long>> getProductsId(
            @Parameter(name = "Accept-Version", description = "Header parameter that indicates the API version the client is working with", in = ParameterIn.HEADER) @RequestHeader(value = "Accept-Version", required = false) String acceptVersion,
            @Parameter(name = "Accept", description = "Header parameter that advertises which content types, expressed as MIME types, the client is able to understand", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept
            ) throws IOException {
        return new ResponseEntity<>(service.getProductsId(), HttpStatus.OK);
    }

}
