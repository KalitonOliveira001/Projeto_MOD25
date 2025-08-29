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

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAOTest {
	
	private IProdutoDAO produtoDao;

	private Produto produto;
	
	public ProdutoDAOTest() {
		produtoDao = new ProdutoDAO();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		// Limpa todos os produtos antes de cada teste
		Collection<Produto> list = produtoDao.buscarTodos();
		Iterator<Produto> iterator = list.iterator();
		while (iterator.hasNext()) {
			Produto prod = iterator.next();
			produtoDao.excluir(prod.getCodigoProduto());
		}
	}
	
	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);

		Produto produtoConsultado = this.produtoDao.consultar(this.produto.getCodigoProduto());
		assertNotNull(produtoConsultado);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A2");
		produto.setDescricao("Produto 2");
		produto.setNome("Produto 2");
		produto.setValor(BigDecimal.ONE);
		Boolean retorno = produtoDao.cadastrar(produto);
		assertTrue(retorno);
		
		Collection<Produto> list = produtoDao.buscarTodos();
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
		produtoDao.cadastrar(produto);

		produtoDao.excluir(produto.getCodigoProduto());
		assertNull(produtoDao.consultar(produto.getCodigoProduto()));
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A4");
		produto.setDescricao("Produto 4");
		produto.setNome("Produto 4");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);

		produto.setNome("Rodrigo Pires");
		produtoDao.alterar(produto);
		
		Produto produtoAlterado = produtoDao.consultar(produto.getCodigoProduto());
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
		produtoDao.cadastrar(produto);

		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 1);
	}
	
	@After
	public void end() {
		Collection<Produto> list = produtoDao.buscarTodos();
		Iterator<Produto> iterator = list.iterator();
		while (iterator.hasNext()) {
			Produto prod = iterator.next();
			produtoDao.excluir(prod.getCodigoProduto());
		}
	}
}

