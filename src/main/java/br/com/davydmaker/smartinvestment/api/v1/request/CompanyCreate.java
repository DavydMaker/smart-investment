package br.com.davydmaker.smartinvestment.api.v1.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyCreate {

    @NotBlank(message = "Nome não pode ser nulo ou vazio.")
    private String name;

}
