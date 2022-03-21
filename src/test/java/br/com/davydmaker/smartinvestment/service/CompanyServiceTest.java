package br.com.davydmaker.smartinvestment.service;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import br.com.davydmaker.smartinvestment.util.MessagesCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CompanyServiceTest extends SmartInvestmentApplicationTests {

    @Autowired
    private CompanyService service;

    @Test
    @DisplayName("Testar cadastro com sucesso de empresa")
    void createSuccessTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName("Inter");

        Assertions.assertDoesNotThrow(() -> this.service.create(request));

        ResponseRequest response = this.service.create(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(MessagesCompany.MSG001, response.getMessage());
    }

    @Test
    @DisplayName("Testar falha no cadastro devido nome nulo")
    void createDataNullFailTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName(null);

        Assertions.assertThrows(SystemException.class, () -> this.service.create(request));
    }

    @Test
    @DisplayName("Testar falha no cadastro devido request nulo")
    void createRequestNullFailTest() {
        Assertions.assertThrows(SystemException.class, () -> this.service.create(null));
    }

}
