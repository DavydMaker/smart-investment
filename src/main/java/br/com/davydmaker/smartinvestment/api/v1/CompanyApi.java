package br.com.davydmaker.smartinvestment.api.v1;

import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/v1/company")
@Tag(name = "Empresas", description = "Requisições referentes a Empresa")
@Validated
public interface CompanyApi {

    @Operation(summary = "Cadastro de Empresa", description = "Cadastrar nova empresa e suas Ações na Bolsa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Internal Error - Erro interno servidor."),
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseRequest> create(@Valid @RequestBody CompanyCreate request);

}
