package br.com.techchallenge.fiap.neighborfood.domain.dto;

import java.net.URI;
import java.util.Objects;
import br.com.techchallenge.fiap.neighborfood.domain.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ItemPedido
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-22T23:22:19.385138400-03:00[GMT-03:00]")
public class ItemPedido {

  private Long id;

  private Long idPedido;

  private ProdutoDTO produto;

  public ItemPedido id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ItemPedido idPedido(Long idPedido) {
    this.idPedido = idPedido;
    return this;
  }

  /**
   * Get idPedido
   * @return idPedido
  */
  
  @Schema(name = "idPedido", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idPedido")
  public Long getIdPedido() {
    return idPedido;
  }

  public void setIdPedido(Long idPedido) {
    this.idPedido = idPedido;
  }

  public ItemPedido produto(ProdutoDTO produto) {
    this.produto = produto;
    return this;
  }

  /**
   * Get produto
   * @return produto
  */
  @Valid 
  @Schema(name = "produto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("produto")
  public ProdutoDTO getProduto() {
    return produto;
  }

  public void setProduto(ProdutoDTO produto) {
    this.produto = produto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPedido itemPedido = (ItemPedido) o;
    return Objects.equals(this.id, itemPedido.id) &&
        Objects.equals(this.idPedido, itemPedido.idPedido) &&
        Objects.equals(this.produto, itemPedido.produto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idPedido, produto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemPedido {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idPedido: ").append(toIndentedString(idPedido)).append("\n");
    sb.append("    produto: ").append(toIndentedString(produto)).append("\n");
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
}

