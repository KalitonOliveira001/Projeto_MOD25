package br.com.rpires.domain;

import java.math.BigDecimal;

public class ProdutoQuantidade {

	private Produto produto;
	private Integer quantidade;

	public ProdutoQuantidade() {
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void add(Integer quantidade) {
		this.quantidade += quantidade;
	}

	public void remover(Integer quantidade) {
		this.quantidade -= quantidade;
	}

	public BigDecimal getValorTotal() {
		return this.produto.getValor().multiply(BigDecimal.valueOf(this.quantidade));
	}

}

