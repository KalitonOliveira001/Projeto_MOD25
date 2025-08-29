package br.com.rpires;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.service.IProdutoService;
import br.com.rpires.service.ProdutoService;

public class ProdutoServiceTest {

	private IProdutoService produtoService;

	private Produto produto;

	public ProdutoServiceTest() {
		IProdutoDAO dao = new ProdutoDAO();
		produtoService = new ProdutoService(dao);
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		// Limpa todos os produtos antes de cada teste
		Collection<Produto> list = produtoService.buscarTodos();
		Iterator<Produto> iterator = list.iterator();
		while (iterator.hasNext()) {
			Produto prod = iterator.next();
			produtoService.excluir(prod.getCodigoProduto());
		}
	}

	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoService.cadastrar(produto);

		Produto produtoConsultado = this.produtoService.consultar(this.produto.getCodigoProduto());
		assertNotNull(produtoConsultado);
	}

	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A2");
		produto.setDescricao("Produto 2");
		produto.setNome("Produto 2");
		produto.setValor(BigDecimal.ONE);
		Boolean retorno = produtoService.cadastrar(produto);
		assertTrue(retorno);
		
		Collection<Produto> list = produtoService.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 1);
	}

	@Test
	public void excluir() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A3");
		produto.setDescricao("Produto 3");
		produto.setNome("Produto 3");
		produto.setValor(BigDecimal.TEN);
		produtoService.cadastrar(produto);

		produtoService.excluir(produto.getCodigoProduto());
		assertNull(produtoService.consultar(produto.getCodigoProduto()));
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A4");
		produto.setDescricao("Produto 4");
		produto.setNome("Produto 4");		
		produto.setValor(BigDecimal.TEN);
		produtoService.cadastrar(produto);

		produto.setNome("Rodrigo Pires");
		produtoService.alterar(produto);
		
		Produto produtoAlterado = produtoService.consultar(produto.getCodigoProduto());
		assertNotNull(produtoAlterado);
		assertEquals("Rodrigo Pires", produtoAlterado.getNome());
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A5");
		produto.setDescricao("Produto 5");
		produto.setNome("Produto 5");
		produto.setValor(BigDecimal.TEN);
		produtoService.cadastrar(produto);

		Collection<Produto> list = produtoService.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 1);
	}

	@After
	public void end() {
		Collection<Produto> list = produtoService.buscarTodos();
		Iterator<Produto> iterator = list.iterator();
		while (iterator.hasNext()) {
			Produto prod = iterator.next();
			produtoService.excluir(prod.getCodigoProduto());
		}
	}
}

