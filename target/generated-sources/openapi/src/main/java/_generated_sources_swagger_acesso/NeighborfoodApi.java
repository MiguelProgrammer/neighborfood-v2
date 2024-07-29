/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package _generated_sources_swagger_acesso;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AdminRequestDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.ClienteRequestDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-28T22:08:58.257789100-03:00[America/Sao_Paulo]")
@Validated
@Tag(name = "login", description = "Realizar login, com cpf")
public interface NeighborfoodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /neighborfood/login : Se cadastrar, logar
     * Identificação do cliente
     *
     * @param clienteRequestDTO Identifica um cliente logado (optional)
     * @return Usuário logado (status code 200)
     *         or request inválida (status code 400)
     */
    @Operation(
        operationId = "login",
        summary = "Se cadastrar, logar",
        description = "Identificação do cliente",
        tags = { "login" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário logado", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "400", description = "request inválida")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Object> login(
        @Parameter(name = "ClienteRequestDTO", description = "Identifica um cliente logado") @Valid @RequestBody(required = false) ClienteRequestDTO clienteRequestDTO
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


    /**
     * POST /neighborfood/painel/login : Cadastrar adm, logar adm
     * Identificação do adm
     *
     * @param adminRequestDTO Identifica um adminsitrador logado (optional)
     * @return Usuário logado (status code 200)
     *         or request inválida (status code 400)
     */
    @Operation(
        operationId = "loginAdm",
        summary = "Cadastrar adm, logar adm",
        description = "Identificação do adm",
        tags = { "loginAdm" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário logado", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "400", description = "request inválida")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/painel/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Object> loginAdm(
        @Parameter(name = "AdminRequestDTO", description = "Identifica um adminsitrador logado") @Valid @RequestBody(required = false) AdminRequestDTO adminRequestDTO
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


    /**
     * POST /neighborfood/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param clienteRequestDTO Cria um novo cliente (optional)
     * @return Usuário logado (status code 200)
     *         or request inválida (status code 400)
     */
    @Operation(
        operationId = "register",
        summary = "Se cadastrar, logar",
        description = "Cria cliente",
        tags = { "register" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário logado", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "400", description = "request inválida")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/cadastro",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Object> register(
        @Parameter(name = "ClienteRequestDTO", description = "Cria um novo cliente") @Valid @RequestBody(required = false) ClienteRequestDTO clienteRequestDTO
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /neighborfood/painel/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param adminRequestDTO Cria um novo administrador (optional)
     * @return Usuário cadastrao (status code 200)
     *         or request inválida (status code 400)
     */
    @Operation(
        operationId = "registerAdm",
        summary = "Se cadastrar, logar",
        description = "Cria cliente",
        tags = { "registerAdm" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrao", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "400", description = "request inválida")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/neighborfood/painel/cadastro",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Object> registerAdm(
        @Parameter(name = "AdminRequestDTO", description = "Cria um novo administrador") @Valid @RequestBody(required = false) AdminRequestDTO adminRequestDTO
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
