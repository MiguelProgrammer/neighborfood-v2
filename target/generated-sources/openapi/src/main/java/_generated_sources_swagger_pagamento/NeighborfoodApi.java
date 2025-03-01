/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package _generated_sources_swagger_pagamento;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-29T12:19:47.053094800-03:00[America/Sao_Paulo]")
@Validated
@Tag(name = "payment", description = "Realização do pagamento")
public interface NeighborfoodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /neighborfood/pagamento : Realiza o pagamento do pedido
     * Realiza pagamento
     *
     * @param pagamentoDTO  (required)
     * @return pagamento realizado com sucesso. (status code 200)
     *         or Id inválido (status code 400)
     *         or Pedido não encontrado (status code 404)
     */
    @Operation(
        operationId = "payment",
        summary = "Realiza o pagamento do pedido",
        description = "Realiza pagamento",
        tags = { "payment" },
        responses = {
            @ApiResponse(responseCode = "200", description = "pagamento realizado com sucesso.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AcompanhamentoResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/pagamento",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<AcompanhamentoResponseDTO> payment(
        @Parameter(name = "PagamentoDTO", description = "", required = true) @Valid @RequestBody PagamentoDTO pagamentoDTO
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"total\" : 7.061401241503109, \"pedido\" : { \"itensPedido\" : [ { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 }, { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 } ], \"idCliente\" : 6, \"id\" : 0 } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
