package com.wnsilveira.financeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Lancamento;
import com.wnsilveira.financeiro.repositories.LancamentoRepository;
import com.wnsilveira.financeiro.services.exceptions.DataIntegrityException;
import com.wnsilveira.financeiro.services.exceptions.ObjectNotFoundException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository repo;
	
	public Lancamento find(Long id) {
		Optional<Lancamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Lancamento.class.getName()));
	}
	
	public Lancamento insert(Lancamento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Lancamento update(Lancamento obj) {
		Lancamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir esse lançamento.");
		}
	}
	
	public List<Lancamento> findAll() {
		return repo.findAll();
	}
	
	public Page<Lancamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
//	public Lancamento fromDTO(LancamentoDTO objDTO) {
//		return new Lancamento(objDTO.getId(), objDTO.getNome());
//	}
	
	private void updateData(Lancamento newObj, Lancamento obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataVencimento(obj.getDataVencimento());
		newObj.setCategoria(obj.getCategoria());
		newObj.setFornecedor(obj.getFornecedor());
		newObj.setTipo(obj.getTipo());
	}

}
