package br.com.davydmaker.smartinvestment.repository;

import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;

public interface CompanyRepository {

    Boolean create(CompanyCreate request);

}
