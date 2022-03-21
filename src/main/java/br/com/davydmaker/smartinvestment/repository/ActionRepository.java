package br.com.davydmaker.smartinvestment.repository;

import br.com.davydmaker.smartinvestment.domain.Action;

public interface ActionRepository {

    Boolean create(Long companyId, Action request);

}