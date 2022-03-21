package br.com.davydmaker.smartinvestment.repository.impl;

import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.Company;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import br.com.davydmaker.smartinvestment.repository.CompanyRepository;
import br.com.davydmaker.smartinvestment.repository.builder.CompanyListQueryBuilder;
import br.com.davydmaker.smartinvestment.repository.sql.CompanyQueries;
import br.com.davydmaker.smartinvestment.util.MessagesCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    private Logger logger = LoggerFactory.getLogger(CompanyRepositoryImpl.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean create(CompanyCreate request) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", request.getName());

            int count = this.jdbcTemplate.update(CompanyQueries.INSERT, params);
            return count > 0;
        } catch (Exception e) {
            logger.error("Erro inesperado ao tentar cadastrar Empresa. Payload: {}. Erro: {}", request, e.getMessage());
            throw new SystemException(MessagesCompany.ERR001);
        }
    }

    //TODO: doing
    public void updateStatus(Long id, Integer status) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params
                    .addValue("status", status)
                    .addValue("id", id);

            this.jdbcTemplate.update(CompanyQueries.UPDATE_STATUS, params);
        } catch (Exception e) {

        }
    }

    //TODO: doing
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params
                .addValue("id", id);

        this.jdbcTemplate.update(CompanyQueries.DELETE, params);
    }

    //TODO: doing
    public List<Company> list(Company request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + request.getName() + "%");
        params.addValue("status", request.getStatus());

        String query = String.format(
                CompanyQueries.LIST,
                new CompanyListQueryBuilder().builder().filter(request).build());

        return this.jdbcTemplate.query(query, params, (rs, rowNum) ->
                Company.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .status(rs.getInt("status"))
                        .build()
        );
    }

}
