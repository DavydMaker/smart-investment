package br.com.davydmaker.smartinvestment.service;

import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.repository.CompanyRepository;
import br.com.davydmaker.smartinvestment.util.MessagesCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    /**
     * Cadastrar nova Empresa
     *
     * @param request Detalhes da Empresa
     * @return Retorna objeto de response com informação se foi cadastrado a ação
     */
    public ResponseRequest create(CompanyCreate request) {
        Boolean hasCreated = this.repository.create(request);
        return ResponseRequest.builder()
                .message(hasCreated ? MessagesCompany.MSG001 : MessagesCompany.MSG002)
                .timestamp(new Date())
                .build();
    }

}