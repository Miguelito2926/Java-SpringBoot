package com.ednaldo.ecommerce;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.entity.Pedido;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import com.ednaldo.ecommerce.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
                System.out.println("Salvando cliente");
                Cliente ednaldo = new Cliente(null, "Ednaldo");
                clienteRepository.save(ednaldo);

            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
