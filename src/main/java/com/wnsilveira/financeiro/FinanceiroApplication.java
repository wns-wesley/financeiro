package com.wnsilveira.financeiro;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wnsilveira.financeiro.domain.Categoria;
import com.wnsilveira.financeiro.repositories.CategoriaRepository;

@SpringBootApplication
public class FinanceiroApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Cama, Mesa e Banho");
		Categoria cat4 = new Categoria("Eletrônicos");
		Categoria cat5 = new Categoria("Jardinagem");
		Categoria cat6 = new Categoria("Decoração");
		Categoria cat7 = new Categoria("Perfumaria");
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
	}
}
