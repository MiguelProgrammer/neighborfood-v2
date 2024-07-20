/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package _generated_sources_swagger_clientes;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoRequestDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-20T05:29:51.608518600-03:00[GMT-03:00]")
@Validated
@Tag(name = "customers", description = "Para gerenciamento de campanhas promocionais")
public interface NeighborfoodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /neighborfood/painel/cliente : Envia mimo ao último cliente que realizou um pedido
     * Envia mimo ao cliente
     *
     * @param mimoRequestDTO  (required)
     * @return Mimo enviado (status code 200)
     *         or Id inválido (status code 400)
     *         or Mimo não disponível (status code 404)
     */
    @Operation(
        operationId = "sendBonus",
        summary = "Envia mimo ao último cliente que realizou um pedido",
        description = "Envia mimo ao cliente",
        tags = { "customers" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Mimo enviado", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MimoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Mimo não disponível")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/painel/cliente",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<MimoDTO> sendBonus(
        @Parameter(name = "MimoRequestDTO", description = "", required = true) @Valid @RequestBody MimoRequestDTO mimoRequestDTO
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"codigo\" : \"codigo\", \"idCliente\" : 0, \"acao\" : \"acao\", \"descricao\" : \"descricao\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
