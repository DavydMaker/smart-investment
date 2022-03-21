package br.com.davydmaker.smartinvestment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"br.com.davydmaker.*"})
public class SmartInvestmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartInvestmentApplication.class, args);
    }

}
