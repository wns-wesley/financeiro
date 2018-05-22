package com.wnsilveira.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Fornecedor;
import com.wnsilveira.financeiro.domain.dto.FornecedorDTO;
import com.wnsilveira.financeiro.repositories.FornecedorRepository;
import com.wnsilveira.financeiro.services.exceptions.DataIntegrityException;
import com.wnsilveira.financeiro.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repo;
	
	public Fornecedor find(Integer id) {
		Optional<Fornecedor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
	}
	
	public Fornecedor insert(Fornecedor obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Fornecedor update(Fornecedor obj) {
		Fornecedor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um fornecedor que possui lançamentos.");
		}
	}
	
	public List<Fornecedor> findAll() {
		return repo.findAll();
	}
	
	public Page<Fornecedor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Fornecedor fromDTO(FornecedorDTO objDTO) {
		return new Fornecedor(objDTO.getId(), objDTO.getNome(), objDTO.getCpfOuCnpj());
	}
	
	private void updateData(Fornecedor newObj, Fornecedor obj) {
		newObj.setNome(obj.getNome());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
	}

}
