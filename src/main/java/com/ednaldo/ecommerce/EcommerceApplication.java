package com.ednaldo.ecommerce;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Ednaldo");
			clientes.salvar(cliente);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
