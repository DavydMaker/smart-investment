package br.com.davydmaker.smartinvestment.controller;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.util.MessagesCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;

class CompanyControllerTest extends SmartInvestmentApplicationTests {

    @Autowired
    private CompanyController controller;

    @Test
    @DisplayName("Testar cadastro com sucesso de empresa")
    void createSuccessTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName("Inter");

        Assertions.assertDoesNotThrow(() -> this.controller.create(request));

        ResponseEntity<ResponseRequest> response = this.controller.create(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(MessagesCompany.MSG001, response.getBody().getMessage());
    }

    @Test
    @DisplayName("Testar falha no cadastro devido nome nulo")
    void createSystemExceptionNameNullFailTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName(null);

        Assertions.assertThrows(ConstraintViolationException.class, () -> this.controller.create(request));
    }

    @Test
    @DisplayName("Testar falha no cadastro devido nome vazio")
    void createSystemExceptioNameEmptyFailTest() {
        CompanyCreate request = new CompanyCreate();
        request.setName("");

        Assertions.assertThrows(ConstraintViolationException.class, () -> this.controller.create(request));
    }

}