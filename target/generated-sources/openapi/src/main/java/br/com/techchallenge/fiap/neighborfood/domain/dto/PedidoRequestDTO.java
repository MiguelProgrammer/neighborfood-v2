package br.com.techchallenge.fiap.neighborfood.domain.dto;

import java.net.URI;
import java.util.Objects;
import br.com.techchallenge.fiap.neighborfood.domain.dto.ItemPedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PedidoRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-22T23:22:19.385138400-03:00[GMT-03:00]")
public class PedidoRequestDTO {

  private Long id;

  private Long idCliente;

  @Valid
  private List<@Valid ItemPedido> itensPedido;

  public PedidoRequestDTO id(Long id) {
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

  public PedidoRequestDTO idCliente(Long idCliente) {
    this.idCliente = idCliente;
    return this;
  }

  /**
   * Get idCliente
   * @return idCliente
  */
  
  @Schema(name = "idCliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("idCliente")
  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public PedidoRequestDTO itensPedido(List<@Valid ItemPedido> itensPedido) {
    this.itensPedido = itensPedido;
    return this;
  }

  public PedidoRequestDTO addItensPedidoItem(ItemPedido itensPedidoItem) {
    if (this.itensPedido == null) {
      this.itensPedido = new ArrayList<>();
    }
    this.itensPedido.add(itensPedidoItem);
    return this;
  }

  /**
   * Get itensPedido
   * @return itensPedido
  */
  @Valid 
  @Schema(name = "itensPedido", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("itensPedido")
  public List<@Valid ItemPedido> getItensPedido() {
    return itensPedido;
  }

  public void setItensPedido(List<@Valid ItemPedido> itensPedido) {
    this.itensPedido = itensPedido;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PedidoRequestDTO pedidoRequestDTO = (PedidoRequestDTO) o;
    return Objects.equals(this.id, pedidoRequestDTO.id) &&
        Objects.equals(this.idCliente, pedidoRequestDTO.idCliente) &&
        Objects.equals(this.itensPedido, pedidoRequestDTO.itensPedido);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idCliente, itensPedido);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PedidoRequestDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idCliente: ").append(toIndentedString(idCliente)).append("\n");
    sb.append("    itensPedido: ").append(toIndentedString(itensPedido)).append("\n");
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

