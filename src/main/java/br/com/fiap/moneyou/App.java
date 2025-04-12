package br.com.fiap.moneyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API do Money You", description = "Consiste em confrinhos para você guardar seu dinheiro", version = "v1"))
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
