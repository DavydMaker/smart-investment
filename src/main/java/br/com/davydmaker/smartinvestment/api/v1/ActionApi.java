package br.com.davydmaker.smartinvestment.api.v1;

import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/v1/action")
@Tag(name = "Ações", description = "Requisições referentes a Ações")
@Validated
public interface ActionApi {

    @Operation(summary = "Cadastro de Ação", description = "Cadastrar Ação para Empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Internal Error - Erro interno servidor."),
    })
    @PostMapping(path = "/{companyId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseRequest> create(
            @Parameter(required = true, description = "Identificador da Empresa")
            @PathVariable(required = true, name = "companyId") final Long companyId,
            @Valid @RequestBody Action request
    );

}
