package com.wnsilveira.financeiro.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wnsilveira.financeiro.domain.enums.TipoFrequencia;
import com.wnsilveira.financeiro.domain.enums.TipoLancamento;

@Entity
public class Lancamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	
	private Integer tipoLancamento;
	
	private boolean repete;
	
	private boolean fixo;
	
	private Integer quantidade;
	
	private Integer tipoFrequencia;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	private Fornecedor fornecedor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Lancamento() {}

	public Lancamento(String descricao, double valor, Date dataVencimento, TipoLancamento tipoLancamento,
			boolean repete, boolean fixo, Integer quantidade, TipoFrequencia tipoFrequencia, Categoria categoria,
			Fornecedor fornecedor, Usuario usuario) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.tipoLancamento = (tipoLancamento == null) ? null : tipoLancamento.getCod();
		this.repete = repete;
		this.fixo = fixo;
		this.quantidade = quantidade;
		this.tipoFrequencia = (tipoFrequencia == null) ? null : tipoFrequencia.getCod();
		this.categoria = categoria;
		this.fornecedor = fornecedor;
		this.usuario = usuario;
	}
	
	public Lancamento(Long id, String descricao, double valor, Date dataVencimento, TipoLancamento tipoLancamento,
			boolean repete, boolean fixo, Integer quantidade, TipoFrequencia tipoFrequencia, Categoria categoria,
			Fornecedor fornecedor, Usuario usuario) {
		this(descricao, valor, dataVencimento, tipoLancamento, repete, fixo, quantidade, tipoFrequencia, categoria, fornecedor, usuario);
		this.id = id;
	}
	
	public Lancamento(Lancamento obj) {
		this(obj.getId(), obj.getDescricao(), obj.getValor(), obj.getDataVencimento(), obj.getTipoLancamento(),
			obj.isRepete(), obj.isFixo(), obj.getQuantidade(), obj.getTipoFrequencia(), obj.getCategoria(), obj.getFornecedor(), obj.getUsuario());
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

	public TipoLancamento getTipoLancamento() {
		return TipoLancamento.toEnum(tipoLancamento);
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento.getCod();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isRepete() {
		return repete;
	}

	public void setRepete(boolean repete) {
		this.repete = repete;
	}

	public boolean isFixo() {
		return fixo;
	}

	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public TipoFrequencia getTipoFrequencia() {
		return TipoFrequencia.toEnum(tipoFrequencia);
	}

	public void setTipoFrequencia(TipoFrequencia tipoFrequencia) {
		this.tipoFrequencia = tipoFrequencia.getCod();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
