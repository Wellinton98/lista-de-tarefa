package com.listatarefa.tarefa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TarefaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefaApplication.class, args);
		System.out.println("Aplicação iniciada com sucesso!");
		System.err.println("Acesso: http://localhost:8080");
	}

}
