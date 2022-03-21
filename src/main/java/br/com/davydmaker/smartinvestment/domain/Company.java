package br.com.davydmaker.smartinvestment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {

    @JsonIgnore
    private Long id;

    private String name;

    private Integer status;

    @JsonIgnore
    private Integer excluded;

}
