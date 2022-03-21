package br.com.davydmaker.smartinvestment.api;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.domain.Action;
import br.com.davydmaker.smartinvestment.util.MessagesAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

class ActionApiTest extends SmartInvestmentApplicationTests {

    @Test
    @DisplayName("Testar cadastro com sucesso de ação")
    void createSuccessTest() throws Exception {
        Action request = Action.builder()
                .ticker("ABC0001")
                .price(new BigDecimal(10))
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals(MessagesAction.MSG001, responseList.get("message").asText());
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que o mesmo ticker acabou de ser cadastro")
    void createNewDuplicateEntryFailTest() throws Exception {
        Action request = Action.builder()
                .ticker("ABC0001")
                .price(new BigDecimal(10))
                .build();

        mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals(MessagesAction.ERR002, responseList.get("description").asText());
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que o mesmo ticker já consta cadastrado no sistema")
    void createOldDuplicateEntryFailTest() throws Exception {
        Action request = Action.builder()
                .ticker("BIDI11")
                .price(new BigDecimal(10))
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals(MessagesAction.ERR002, responseList.get("description").asText());
    }

    @Test
    @DisplayName("Teste de cadastro com falha devido que empresa informada não existe na base")
    void createCompanyNotFoundFailTest() throws Exception {
        Action request = Action.builder()
                .ticker("BIDI12")
                .price(new BigDecimal(10))
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/99")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals(MessagesAction.ERR003, responseList.get("description").asText());
    }

    @Test
    @DisplayName("Testar cadastro com falha devido ticker nulo")
    void createTickerNullFailTest() throws Exception {
        Action request = Action.builder()
                .ticker(null)
                .price(new BigDecimal(10))
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("Ticker não pode ser nulo ou vazio", responseList.get("message").asText());
    }

    @Test
    @DisplayName("Testar cadastro com falha devido ticker vazio")
    void createTickereMPTYFailTest() throws Exception {
        Action request = Action.builder()
                .ticker(null)
                .price(new BigDecimal(10))
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("Ticker não pode ser nulo ou vazio", responseList.get("message").asText());
    }

    @Test
    @DisplayName("Testar cadastro com falha devido preço nulo")
    void createPriceNullFailTest() throws Exception {
        Action request = Action.builder()
                .ticker("ABC0001")
                .price(null)
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("Preço não pode ser nulo", responseList.get("message").asText());
    }

    @Test
    @DisplayName("Testar cadastro com falha devido preço 0")
    void createPriceZeroFailTest() throws Exception {
        Action request = Action.builder()
                .ticker("ABC0001")
                .price(BigDecimal.ZERO)
                .build();

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/action/1")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("O Valor Mínimo da Ação deve ser 1", responseList.get("message").asText());
    }

}
