package com.gvendas.gestaovendas.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "preco_custo")
	private BigDecimal precoCusto;
	
	@Column(name = "preco_venda")
	private BigDecimal preco_venda;
	
	@Column(name = "observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", 
	referencedColumnName = "codigo")
	private Categoria categoria;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, codigo, descricao, observacao, precoCusto, preco_venda, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Produto)) {
			return false;
		}
		Produto other = (Produto) obj;
		return Objects.equals(categoria, other.categoria) && codigo == other.codigo
				&& Objects.equals(descricao, other.descricao) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(precoCusto, other.precoCusto) && Objects.equals(preco_venda, other.preco_venda)
				&& Objects.equals(quantidade, other.quantidade);
	}

	
}