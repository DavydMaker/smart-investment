package br.com.davydmaker.smartinvestment.repository;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CompanyRepositoryTest extends SmartInvestmentApplicationTests {

    @Autowired
    private CompanyRepository repository;

    @Test
    @DisplayName("Testar cadastro com sucesso de empresa")
    void createSuccessTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName("Inter");

        Assertions.assertDoesNotThrow(() -> this.repository.create(request));

        Boolean response = this.repository.create(request);
        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Testar falha no cadastro devido nome nulo")
    void createDataNullFailTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName(null);

        Assertions.assertThrows(SystemException.class, () -> this.repository.create(request));
    }

    @Test
    @DisplayName("Testar falha no cadastro devido request nula")
    void createRequestNullFailTest() {
        Assertions.assertThrows(SystemException.class, () -> this.repository.create(null));
    }

}
