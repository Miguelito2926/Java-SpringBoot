package com.ednaldo.ecommerce;

import com.ednaldo.ecommerce.domain.entity.Cliente;
import com.ednaldo.ecommerce.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

@SpringBootApplication
public class EcommerceApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clientes){
		return args -> {
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("Ednaldo"));
			clientes.save(new Cliente("Outro Cliente"));

			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
           List<Cliente> clientes1 =  clientes.encontrarPorNome("Ednaldo");
           clientes1.forEach(System.out::println);

            System.out.println("deletando clientes");
            clientes.findAll().forEach( c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.findAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
