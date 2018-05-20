package com.wnsilveira.financeiro.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Fornecedor;
import com.wnsilveira.financeiro.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repo;
	
	public Fornecedor find(Integer id) {
		Optional<Fornecedor> obj = repo.findById(id);
//		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
		return obj.orElse(null);
	}

}
