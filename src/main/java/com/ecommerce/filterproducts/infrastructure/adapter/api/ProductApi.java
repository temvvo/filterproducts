/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.ecommerce.filterproducts.infrastructure.adapter.api;


import com.ecommerce.filterproducts.infrastructure.adapter.api.model.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-02T00:59:23.088864-03:00[America/Argentina/Buenos_Aires]")
@Validated
@Tag(name = "product", description = "Product endpoints")
public interface ProductApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /products/id : Get products id
     * Get products id ordered by sequence.
     *
     * @param acceptVersion Header parameter that indicates the API version the client is working with (optional)
     * @param accept        Header parameter that advertises which content types, expressed as MIME types, the client is able to understand (optional)
     * @return Products id response (status code 200)
     * or Internal Server Error (status code 500)
     */
    @Operation(
            operationId = "getProductsId",
            summary = "Get products id",
            description = "Get products id ordered by sequence.",
            tags = { "product" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products id response", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/products/id",
            produces = { "application/json" }
    )
    default ResponseEntity<List<Long>> getProductsId(
            @Parameter(name = "Accept-Version", description = "Header parameter that indicates the API version the client is working with", in = ParameterIn.HEADER) @RequestHeader(value = "Accept-Version", required = false) String acceptVersion,
            @Parameter(name = "Accept", description = "Header parameter that advertises which content types, expressed as MIME types, the client is able to understand", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept
            ) throws IOException {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /products : Get products details
     * Get products details.
     *
     * @param acceptVersion Header parameter that indicates the API version the client is working with (optional)
     * @param accept Header parameter that advertises which content types, expressed as MIME types, the client is able to understand (optional)
     * @return Products details response (status code 200)
     *         or Internal Server Error (status code 500)
     */
    @Operation(
        operationId = "getProducts",
        summary = "Get products details",
        description = "Get products details.",
        tags = { "product" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Products details response", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/products",
        produces = { "application/json" }
    )
    default ResponseEntity<Set<ProductDto>> getProducts(
        @Parameter(name = "Accept-Version", description = "Header parameter that indicates the API version the client is working with", in = ParameterIn.HEADER) @RequestHeader(value = "Accept-Version", required = false) String acceptVersion,
        @Parameter(name = "Accept", description = "Header parameter that advertises which content types, expressed as MIME types, the client is able to understand", in = ParameterIn.HEADER) @RequestHeader(value = "Accept", required = false) String accept
    ) throws IOException {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
