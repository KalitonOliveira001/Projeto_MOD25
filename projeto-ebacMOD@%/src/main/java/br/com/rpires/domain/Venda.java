package br.com.rpires.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Venda implements Persistente {

	public enum Status {
		INICIADA, CONCLUIDA, CANCELADA;

		public static Status getByName(String name) {
			for (Status status : Status.values()) {
				if (status.name().equals(name)) {
					return status;
				}
			}
			return null;
		}
	}

	private Long codigo;
	private Cliente cliente;
	private Set<ProdutoQuantidade> produtos;
	private BigDecimal valorTotal;
	private Instant dataVenda;
	private Status status;

	public Venda() {
		produtos = new HashSet<>();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProdutoQuantidade> getProdutos() {
		return produtos;
	}

	public void addProduto(Produto produto, Integer quantidade) {
		Optional<ProdutoQuantidade> op = produtos.stream().filter(entry -> entry.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
		if (op.isPresent()) {
			ProdutoQuantidade produtoQtd = op.get();
			produtoQtd.add(quantidade);
		} else {
			ProdutoQuantidade pq = new ProdutoQuantidade();
			pq.setProduto(produto);
			pq.setQuantidade(quantidade);
			produtos.add(pq);
		}
		calcularValorTotal();
	}

	public void removerProduto(Produto produto, Integer quantidade) {
		Optional<ProdutoQuantidade> op = produtos.stream().filter(entry -> entry.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
		
		if (op.isPresent()) {
			ProdutoQuantidade produtoQtd = op.get();
			if (produtoQtd.getQuantidade()>quantidade) {
				produtoQtd.remover(quantidade);
				calcularValorTotal();
			} else {
				produtos.remove(op.get());
				calcularValorTotal();
			}
		}
	}

	public void removerTodosProdutos() {
		produtos.clear();
		valorTotal = BigDecimal.ZERO;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	private void calcularValorTotal() {
		this.valorTotal = BigDecimal.ZERO;
		this.produtos.forEach(prod -> {
			this.valorTotal = this.valorTotal.add(prod.getValorTotal());
		});
	}

	public Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

