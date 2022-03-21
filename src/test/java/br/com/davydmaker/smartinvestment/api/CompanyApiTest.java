package br.com.davydmaker.smartinvestment.api;

import br.com.davydmaker.smartinvestment.SmartInvestmentApplicationTests;
import br.com.davydmaker.smartinvestment.api.v1.request.CompanyCreate;
import br.com.davydmaker.smartinvestment.util.MessagesCompany;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

class CompanyApiTest extends SmartInvestmentApplicationTests {

    @Test
    @DisplayName("Testar cadastro com sucesso de empresa")
    void createSuccessTest() throws Exception {
        CompanyCreate request = new CompanyCreate();
        request.setName("Inter");

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/company")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals(MessagesCompany.MSG001, responseList.get("message").asText());
    }

    @Test
    @DisplayName("Testar cadastro de empresa com nome nulo")
    void createNameNullFailTest() throws Exception {
        CompanyCreate request = new CompanyCreate();
        request.setName(null);

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/company")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("Nome não pode ser nulo ou vazio.", responseList.get("message").asText());
    }

    @Test
    @DisplayName("Testar cadastro de empresa com nome vazio")
    void createNameEmptyFailTest() throws Exception {
        CompanyCreate request = new CompanyCreate();
        request.setName("");

        ResultActions result = mock.perform(
                MockMvcRequestBuilders.post("/v1/company")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Assertions.assertNotNull(result);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseList = mapper.readValue(result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ObjectNode.class);
        Assertions.assertEquals("Nome não pode ser nulo ou vazio.", responseList.get("message").asText());
    }

}
