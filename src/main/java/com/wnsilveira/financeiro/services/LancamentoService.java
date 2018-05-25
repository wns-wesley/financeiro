package com.wnsilveira.financeiro.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Lancamento;
import com.wnsilveira.financeiro.domain.enums.TipoFrequencia;
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
		if (obj.isRepete() || obj.isFixo()) {
			repo.saveAll(repeteLancamentos(obj));
			return obj;
		}
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
		newObj.setTipoLancamento(obj.getTipoLancamento());
	}
	
	private List<Lancamento> repeteLancamentos(Lancamento obj) {
		Integer quantidade = 0;
		if (obj.isRepete()) {
			quantidade = obj.getQuantidade() - 1;
		} else if (obj.isFixo()) {
			quantidade = 5;
		}
		List<Lancamento> list = new ArrayList<>();
		list.add(obj);
		for (int i = 0; i < quantidade; i++) {
			Lancamento newObj = new Lancamento(list.get(i));
			Calendar cal = Calendar.getInstance();
			cal.setTime(newObj.getDataVencimento());
			if (newObj.getTipoFrequencia().equals(TipoFrequencia.MENSAL)) {
				cal.add(Calendar.MONTH, 1);
			} else if (newObj.getTipoFrequencia().equals(TipoFrequencia.DIARIO)) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			newObj.setDataVencimento(cal.getTime());
			list.add(newObj);
		}
		return list;
	}

}
