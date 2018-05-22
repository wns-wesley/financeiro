package com.wnsilveira.financeiro;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wnsilveira.financeiro.domain.Categoria;
import com.wnsilveira.financeiro.domain.Fornecedor;
import com.wnsilveira.financeiro.domain.Lancamento;
import com.wnsilveira.financeiro.domain.enums.TipoLancamento;
import com.wnsilveira.financeiro.repositories.CategoriaRepository;
import com.wnsilveira.financeiro.repositories.FornecedorRepository;
import com.wnsilveira.financeiro.repositories.LancamentoRepository;

@SpringBootApplication
public class FinanceiroApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Mensal");
		Categoria cat2 = new Categoria("Educação");
		Categoria cat3 = new Categoria("Saúde");
		Categoria cat4 = new Categoria("Alimentação");
		Categoria cat5 = new Categoria("Jardinagem");
		Categoria cat6 = new Categoria("Decoração");
		Categoria cat7 = new Categoria("Perfumaria");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
		Fornecedor for1 = new Fornecedor("Moranguinho", "12123123000145");
		Fornecedor for2 = new Fornecedor("Unopar", "");
		Fornecedor for3 = new Fornecedor("Copel", "");
		Fornecedor for4 = new Fornecedor("Sanepar", "12123123000145");
		Fornecedor for5 = new Fornecedor("SatTrack", "12123123000145");
		Fornecedor for6 = new Fornecedor("Prefeitura de Londrina", "");
		Fornecedor for7 = new Fornecedor("Auto center 3 irmãos", "12123123000145");
		Fornecedor for8 = new Fornecedor("Americanas", "");
		Fornecedor for9 = new Fornecedor("PS Store", "");
		
		fornecedorRepository.saveAll(Arrays.asList(for1, for2, for3, for4, for5, for6, for7, for8, for9));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Lancamento l1 = new Lancamento("Luz", 80.00, sdf.parse("10/05/2018"), TipoLancamento.DESPESA);
		l1.setCategoria(cat1);
		l1.setFornecedor(for3);
		
		Lancamento l2 = new Lancamento("Boleto Unopar", 209.90, sdf.parse("20/05/2018"), TipoLancamento.DESPESA);
		l2.setCategoria(cat2);
		l2.setFornecedor(for2);
		
		Lancamento l3 = new Lancamento("Mensalidade Moranguinho", 555.00, sdf.parse("08/05/2018"), TipoLancamento.DESPESA);
		l3.setCategoria(cat2);
		l3.setFornecedor(for1);
		
		Lancamento l4 = new Lancamento("Chocolate", 20.00, sdf.parse("12/05/2018"), TipoLancamento.DESPESA);
		l4.setCategoria(cat4);
		l4.setFornecedor(for8);
		
		lancamentoRepository.saveAll(Arrays.asList(l1, l2, l3, l4));
	}
}
