package br.com.davydmaker.smartinvestment;

import com.google.gson.Gson;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Tag("integration")
@ActiveProfiles("h2")
@AutoConfigureMockMvc
@Sql(scripts = {
        "/data/schema.sql",
        "/data/data.sql"
}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public abstract class SmartInvestmentApplicationTests {

    @Autowired
    protected MockMvc mock;

    protected static final Gson gson = new Gson();

}
