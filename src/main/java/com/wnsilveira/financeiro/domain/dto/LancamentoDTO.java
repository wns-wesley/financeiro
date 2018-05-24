package com.wnsilveira.financeiro.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.wnsilveira.financeiro.domain.Lancamento;

public class LancamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private double valor;
	private Date dataVencimento;
	private Integer tipo;
	
	public LancamentoDTO() {}
	
	public LancamentoDTO(Lancamento obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.dataVencimento = obj.getDataVencimento();
		this.tipo = obj.getTipoLancamento().getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}
