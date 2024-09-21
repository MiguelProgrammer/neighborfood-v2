/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package _generated_sources_swagger_acompanhamento;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-21T17:08:53.629791900-03:00[America/Sao_Paulo]")
@Validated
@Tag(name = "follow-up", description = "Acompanhar status do pedido")
public interface NeighborfoodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /neighborfood/acompanhamento/{idPedido} : Procura o status de um pedido
     * Retorna o status de um pedido
     *
     * @param idPedido id do pedido (required)
     * @return successful operation (status code 200)
     *         or Id inválido (status code 400)
     *         or Pedido não encontrado (status code 404)
     */
    @Operation(
        operationId = "findOrderByIdOrder",
        summary = "Procura o status de um pedido",
        description = "Retorna o status de um pedido",
        tags = { "follow-up" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AcompanhamentoResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/neighborfood/acompanhamento/{idPedido}",
        produces = { "application/json" }
    )
    default ResponseEntity<AcompanhamentoResponseDTO> findOrderByIdOrder(
        @Parameter(name = "idPedido", description = "id do pedido", required = true, in = ParameterIn.PATH) @PathVariable("idPedido") Long idPedido
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
