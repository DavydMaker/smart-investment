package br.com.davydmaker.smartinvestment.controller;

import br.com.davydmaker.smartinvestment.api.v1.ActionApi;
import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ActionController implements ActionApi {

    @Autowired
    private ActionService service;

    @Override
    public ResponseEntity<ResponseRequest> create(Long companyId, @Valid Action request) {
        return new ResponseEntity<>(this.service.create(companyId, request), HttpStatus.OK);
    }

}
