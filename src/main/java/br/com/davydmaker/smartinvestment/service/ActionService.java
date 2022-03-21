package br.com.davydmaker.smartinvestment.service;

import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.repository.ActionRepository;
import br.com.davydmaker.smartinvestment.util.MessagesAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActionService {

    @Autowired
    private ActionRepository repository;


    /**
     * Cadastrar Ação para Empresa
     *
     * @param companyId Identificador da Empresa
     * @param request   Detalhes da Ação
     * @return Retorna objeto de response com informação se foi cadastrado a empresa
     */
    //TODO: quando tentar cadastrar Ação em empresa que não existe informar para o usuário (hoje consa apenas validação de CONSTRAINT, o que ainda permite cadastrar para empresas excluídas)
    public ResponseRequest create(Long companyId, Action request) {
        Boolean hasCreated = this.repository.create(companyId, request);
        return ResponseRequest.builder()
                .message(hasCreated ? MessagesAction.MSG001 : MessagesAction.MSG002)
                .timestamp(new Date())
                .build();
    }

    //TODO: doing
    //TODO: verificar se Ação existe e caso não informar para usuário
    //TODO: receber identificador da empresa também para ter double check
    void updatePrice(Action request) {

    }

}