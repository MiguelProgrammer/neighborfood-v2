package br.com.techchallenge.fiap.neighborfood.domain.dto;

import java.net.URI;
import java.util.Objects;
import br.com.techchallenge.fiap.neighborfood.domain.dto.PedidoRequestDTO;
import br.com.techchallenge.fiap.neighborfood.domain.dto.StatusPedidoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AcompanhamentoResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-09T12:28:22.169518900-03:00[GMT-03:00]")
public class AcompanhamentoResponseDTO {

  private PedidoRequestDTO pedido;

  private StatusPedidoDTO status;

  private java.math.BigDecimal total;

  public AcompanhamentoResponseDTO pedido(PedidoRequestDTO pedido) {
    this.pedido = pedido;
    return this;
  }

  /**
   * Get pedido
   * @return pedido
  */
  @Valid 
  @Schema(name = "pedido", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pedido")
  public PedidoRequestDTO getPedido() {
    return pedido;
  }

  public void setPedido(PedidoRequestDTO pedido) {
    this.pedido = pedido;
  }

  public AcompanhamentoResponseDTO status(StatusPedidoDTO status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusPedidoDTO getStatus() {
    return status;
  }

  public void setStatus(StatusPedidoDTO status) {
    this.status = status;
  }

  public AcompanhamentoResponseDTO total(java.math.BigDecimal total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total")
  public java.math.BigDecimal getTotal() {
    return total;
  }

  public void setTotal(java.math.BigDecimal total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AcompanhamentoResponseDTO acompanhamentoResponseDTO = (AcompanhamentoResponseDTO) o;
    return Objects.equals(this.pedido, acompanhamentoResponseDTO.pedido) &&
        Objects.equals(this.status, acompanhamentoResponseDTO.status) &&
        Objects.equals(this.total, acompanhamentoResponseDTO.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pedido, status, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AcompanhamentoResponseDTO {\n");
    sb.append("    pedido: ").append(toIndentedString(pedido)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

