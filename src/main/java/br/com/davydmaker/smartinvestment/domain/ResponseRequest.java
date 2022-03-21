package br.com.davydmaker.smartinvestment.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ResponseRequest {

    private Date timestamp;
    private String message;
    private String description;

}