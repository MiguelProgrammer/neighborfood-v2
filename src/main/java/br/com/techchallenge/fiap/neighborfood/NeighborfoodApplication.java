package br.com.techchallenge.fiap.neighborfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Scanner;

/*

@ComponentScan({"br.com.techchallenge.fiap.neighborfood.adapters.inbound",
        "br.com.techchallenge.fiap.neighborfood.adapters.inbound"})
@EntityScan("br.com.techchallenge.fiap.neighborfood.adapter.outbound.entities.*")
@EnableJpaRepositories("br.com.techchallenge.fiap.neighborfood.adapter.outbound.jpa.*")
 */
@SpringBootApplication
public class NeighborfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeighborfoodApplication.class, args);
        System.out.println("_______________ Lanchonete NeighborFood _______________");
    }
}
