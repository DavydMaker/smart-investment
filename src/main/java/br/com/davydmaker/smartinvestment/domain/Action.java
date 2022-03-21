package br.com.davydmaker.smartinvestment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class Action {

    @JsonIgnore
    private Long companyId;

    @NotBlank(message = "Ticker não pode ser nulo ou vazio")
    @Length(max = 10, min = 1, message = "O nome da Ação deve ser entre 1 e 10 caracteres")
    private String ticker;

    //TODO: adicionar validação de valor Mínimo
    @NotNull(message = "Preço não pode ser nulo")
    @Min(value = 1, message = "O Valor Mínimo da Ação deve ser 1")
    private BigDecimal price;

}