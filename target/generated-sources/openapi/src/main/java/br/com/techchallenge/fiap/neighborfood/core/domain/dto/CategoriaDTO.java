package br.com.techchallenge.fiap.neighborfood.core.domain.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets CategoriaDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-29T12:19:47.714581600-03:00[America/Sao_Paulo]")
public enum CategoriaDTO {
  
  LANCHE("LANCHE"),
  
  ACOMPANHAMENTO("ACOMPANHAMENTO"),
  
  BEBIDA("BEBIDA"),
  
  SOBREMESA("SOBREMESA");

  private String value;

  CategoriaDTO(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CategoriaDTO fromValue(String value) {
    for (CategoriaDTO b : CategoriaDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

