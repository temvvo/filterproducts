package com.ecommerce.filterproducts.infrastructure.adapter.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * Product details
 */
@Schema(name = "Product", description = "Product details")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-02T00:59:23.088864-03:00[America/Argentina/Buenos_Aires]")
public class ProductDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("sequence")
  private Long sequence;

  public ProductDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * id
   * @return id
  */
  
  @Schema(name = "id", description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductDto sequence(Long sequence) {
    this.sequence = sequence;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDto product = (ProductDto) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.sequence, product.sequence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sequence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sequence: ").append(toIndentedString(sequence)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public Long getSequence() {
    return sequence;
  }
}

