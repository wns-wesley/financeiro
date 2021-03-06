package com.wnsilveira.financeiro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wnsilveira.financeiro.domain.Lancamento;
import com.wnsilveira.financeiro.domain.Usuario;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	@Transactional(readOnly=true)
	Page<Lancamento> findByUsuario(Usuario usuario, Pageable pageRequest);

}
