package br.com.davydmaker.smartinvestment.repository.builder;

import br.com.davydmaker.smartinvestment.domain.Company;

import static java.util.Objects.nonNull;

public class CompanyListQueryBuilder {

    private StringBuilder clause;
    private Company filter;

    public CompanyListQueryBuilder builder() {
        this.clause = new StringBuilder();
        return this;
    }

    public CompanyListQueryBuilder filter(final Company filter) {
        this.filter = filter;
        return this;
    }

    private void getNameBuilder() {
        if (nonNull(filter.getName())) {
            this.clause.append(" AND name = :name");
        }
    }

    private void getStatusBuilder() {
        if (nonNull(filter.getStatus())) {
            this.clause.append(" AND status = :status");
        }
    }

    public String build() {
        this.getNameBuilder();
        this.getStatusBuilder();
        return this.clause.toString();
    }

}
