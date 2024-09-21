/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package _generated_sources_swagger_estoque;

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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-21T18:16:20.108502100-03:00[America/Sao_Paulo]")
@Validated
@Tag(name = "orders", description = "Lista os pedidos")
public interface NeighborfoodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /neighborfood/painel/pedido/lista/{idAdmin} : Lista de pedidos efetuados contendo seus clientes, itens, status, valores e data de pedido e data de entrega.
     * Lista os pedidos recebidos
     *
     * @param idAdmin id identificador do administrador (required)
     * @return Lista de pedidos (status code 200)
     *         or Id inválido (status code 400)
     *         or Mimo não disponível (status code 404)
     */
    @Operation(
        operationId = "listOrders",
        summary = "Lista de pedidos efetuados contendo seus clientes, itens, status, valores e data de pedido e data de entrega.",
        description = "Lista os pedidos recebidos",
        tags = { "orders" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AcompanhamentoResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Mimo não disponível")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/neighborfood/painel/pedido/lista/{idAdmin}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<AcompanhamentoResponseDTO>> listOrders(
        @Parameter(name = "idAdmin", description = "id identificador do administrador", required = true, in = ParameterIn.PATH) @PathVariable("idAdmin") Long idAdmin
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"total\" : 7.061401241503109, \"pedido\" : { \"itensPedido\" : [ { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 }, { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 } ], \"idCliente\" : 6, \"id\" : 0 } }, { \"total\" : 7.061401241503109, \"pedido\" : { \"itensPedido\" : [ { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 }, { \"produto\" : { \"preco\" : 2.3021358869347655, \"img\" : \"img\", \"nome\" : \"nome\", \"id\" : 5, \"descricao\" : \"descricao\" }, \"id\" : 1, \"idPedido\" : 5 } ], \"idCliente\" : 6, \"id\" : 0 } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /neighborfood/painel/produto/cadastro/{idAdmin} : Cadastra produtos
     * Cadastra produtos em lote
     *
     * @param idAdmin id identificador do administrador (required)
     * @return Produtos cadastrados (status code 200)
     *         or Id inválido (status code 400)
     *         or Produto inválido (status code 404)
     */
    @Operation(
        operationId = "registerProduct",
        summary = "Cadastra produtos",
        description = "Cadastra produtos em lote",
        tags = { "products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Produtos cadastrados", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "400", description = "Id inválido"),
            @ApiResponse(responseCode = "404", description = "Produto inválido")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/neighborfood/painel/produto/cadastro/{idAdmin}",
        produces = { "application/json" }
    )
    default ResponseEntity<Object> registerProduct(
        @Parameter(name = "idAdmin", description = "id identificador do administrador", required = true, in = ParameterIn.PATH) @PathVariable("idAdmin") Long idAdmin
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(""))) {
                    String exampleString = "";
                    ApiUtil.setExampleResponse(request, "", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
