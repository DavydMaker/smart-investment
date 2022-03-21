package br.com.davydmaker.smartinvestment.controller;

import br.com.davydmaker.smartinvestment.api.v1.CompanyApi;
import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService service;

    @Override
    public ResponseEntity<ResponseRequest> create(@Valid CompanyCreate request) {
        return new ResponseEntity<>(this.service.create(request), HttpStatus.OK);
    }

}
