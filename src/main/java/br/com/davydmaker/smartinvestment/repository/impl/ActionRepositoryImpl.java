package br.com.davydmaker.smartinvestment.repository.impl;

import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.exception.BusinessException;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import br.com.davydmaker.smartinvestment.repository.ActionRepository;
import br.com.davydmaker.smartinvestment.repository.sql.ActionQueries;
import br.com.davydmaker.smartinvestment.util.MessagesAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class ActionRepositoryImpl implements ActionRepository {

    private Logger logger = LoggerFactory.getLogger(ActionRepositoryImpl.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ActionRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean create(Long companyId, Action request) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params
                    .addValue("company_id", companyId)
                    .addValue("ticker", request.getTicker().trim().toUpperCase())
                    .addValue("price", request.getPrice());

            int count = this.jdbcTemplate.update(ActionQueries.INSERT, params);
            return count > 0;
        } catch (DuplicateKeyException e) {
            logger.error("Erro ao tentar cadastrar Ação para Empresa: {}. Já consta um registro com esse nome. Payload: {}. Erro: {}", companyId, request, e.getMessage());
            throw new BusinessException(MessagesAction.ERR002);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro ao tentar cadastrar Ação para Empresa: {}. Empresa não existe na base. Payload: {}. Erro: {}", companyId, request, e.getMessage());
            throw new BusinessException(MessagesAction.ERR003);
        } catch (Exception e) {
            logger.error("Erro inesperado ao tentar cadastrar Ação para Empresa: {}. Payload: {}. Erro: {}", companyId, request, e.getMessage());
            throw new SystemException(MessagesAction.ERR001);
        }
    }

    //TODO: doing
    public void updatePrice(String ticker, BigDecimal price) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params
                .addValue("price", price)
                .addValue("ticker", ticker);

        this.jdbcTemplate.update(ActionQueries.UPDATE_PRICE, params);
    }


}
