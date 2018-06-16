package com.wnsilveira.financeiro.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Categoria;
import com.wnsilveira.financeiro.domain.Fornecedor;
import com.wnsilveira.financeiro.domain.Lancamento;
import com.wnsilveira.financeiro.domain.Usuario;
import com.wnsilveira.financeiro.domain.enums.TipoLancamento;
import com.wnsilveira.financeiro.repositories.CategoriaRepository;
import com.wnsilveira.financeiro.repositories.FornecedorRepository;
import com.wnsilveira.financeiro.repositories.LancamentoRepository;
import com.wnsilveira.financeiro.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria("Mensal");
		Categoria cat2 = new Categoria("Educação");
		Categoria cat3 = new Categoria("Saúde");
		Categoria cat4 = new Categoria("Alimentação");
		Categoria cat5 = new Categoria("Serviço");
		Categoria cat6 = new Categoria("Decoração");
		Categoria cat7 = new Categoria("Perfumaria");
		
		Fornecedor for1 = new Fornecedor("Moranguinho", "12123123000145");
		Fornecedor for2 = new Fornecedor("Unopar", "");
		Fornecedor for3 = new Fornecedor("Copel", "");
		Fornecedor for4 = new Fornecedor("Sanepar", "12123123000145");
		Fornecedor for5 = new Fornecedor("SatTrack", "12123123000145");
		Fornecedor for6 = new Fornecedor("Prefeitura de Londrina", "");
		Fornecedor for7 = new Fornecedor("Auto center 3 irmãos", "12123123000145");
		Fornecedor for8 = new Fornecedor("Americanas", "");
		Fornecedor for9 = new Fornecedor("PS Store", "");
		
		Usuario user1 = new Usuario("Wesley", "wesley@gmail.com", pe.encode("123"));
		Usuario user2 = new Usuario("Debora", "debora@gmail.com", pe.encode("123"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Lancamento l1 = new Lancamento("Serviço prestado", 1000, sdf.parse("01/05/2018"), TipoLancamento.RECEITA, false, false, null, null, cat5, for7, user1);
		
		Lancamento l2 = new Lancamento("Boleto Unopar", 209.90, sdf.parse("20/05/2018"), TipoLancamento.DESPESA, false, false, null, null, cat2, for2, user1);
		
		Lancamento l3 = new Lancamento("Mensalidade Moranguinho", 555.00, sdf.parse("08/05/2018"), TipoLancamento.DESPESA, false, false, null, null, cat2, for1, user1);
		
		Lancamento l4 = new Lancamento("Chocolate", 20.00, sdf.parse("12/05/2018"), TipoLancamento.DESPESA, false, false, null, null, cat4, for8, user1);
		
		Lancamento l5 = new Lancamento("Casa", 800.00, sdf.parse("08/05/2018"), TipoLancamento.DESPESA, false, false, null, null, cat2, for1, user2);
		
		Lancamento l6 = new Lancamento("Anti Fiot", 20.00, sdf.parse("12/05/2018"), TipoLancamento.DESPESA, false, false, null, null, cat4, for8, user2);
		
		user1.getLancamentos().addAll(Arrays.asList(l1, l2, l3, l4));
		user2.getLancamentos().addAll(Arrays.asList(l5, l6));
		
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		fornecedorRepository.saveAll(Arrays.asList(for1, for2, for3, for4, for5, for6, for7, for8, for9));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		lancamentoRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
		
	}

}
