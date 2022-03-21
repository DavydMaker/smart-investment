package br.com.davydmaker.smartinvestment.repository;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.exception.BusinessException;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

class ActionRepositoryTest extends SmartInvestmentApplicationTests {

    @Autowired
    private ActionRepository repository;

    @Test
    @DisplayName("Teste de cadastro com sucesso de ação")
    void createSuccessTest() {
        Action request = Action.builder()
                .ticker("ABC001")
                .price(new BigDecimal(10))
                .build();

        Assertions.assertDoesNotThrow(() -> this.repository.create(1L, request));

        request.setTicker("ABC0002");
        Boolean response = this.repository.create(1L, request);
        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Teste de falha no cadastro devido a dados nulos")
    void createDataNullFailTest() {
        Action request = Action.builder()
                .ticker(null)
                .price(null)
                .build();

        Assertions.assertThrows(SystemException.class, () -> this.repository.create(1L, request));
    }

    @Test
    @DisplayName("Teste de falha no cadastro devido a request nula")
    void createRequestNullFailTest() {
        Assertions.assertThrows(SystemException.class, () -> this.repository.create(1L, null));
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que o mesmo ticker acabou de ser cadastro")
    void createNewDuplicateEntryFailTest() {
        Action request = Action.builder()
                .ticker("ABC001")
                .price(new BigDecimal(10))
                .build();

        Assertions.assertDoesNotThrow(() -> this.repository.create(1L, request));
        Assertions.assertThrows(BusinessException.class, () -> this.repository.create(1L, request));
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que o mesmo ticker já consta cadastrado no sistema")
    void createOldDuplicateEntryFailTest() {
        Action request = Action.builder()
                .ticker("BIDI11")
                .price(new BigDecimal(10))
                .build();

        Assertions.assertThrows(BusinessException.class, () -> this.repository.create(1L, request));
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que empresa informada não existe na base")
    void createCompanyNotFoundFailTest() {
        Action request = Action.builder()
                .ticker("ABC001")
                .price(new BigDecimal(10))
                .build();

        Assertions.assertThrows(BusinessException.class, () -> this.repository.create(99L, request));
    }

}
