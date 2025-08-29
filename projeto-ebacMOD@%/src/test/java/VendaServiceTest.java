package br.com.rpires;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.IVendaDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.dao.VendaDAO;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.Venda;
import br.com.rpires.domain.Venda.Status;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.service.ClienteService;
import br.com.rpires.service.IClienteService;
import br.com.rpires.service.IProdutoService;
import br.com.rpires.service.IVendaService;
import br.com.rpires.service.ProdutoService;
import br.com.rpires.service.VendaService;

public class VendaServiceTest {

	private IVendaService vendaService;
	private IClienteService clienteService;
	private IProdutoService produtoService;

	private Cliente cliente;
	private Produto produto;

	public VendaServiceTest() {
		IVendaDAO vendaDao = new VendaDAO();
		vendaService = new VendaService(vendaDao);
		IClienteDAO clienteDao = new ClienteDAO();
		clienteService = new ClienteService(clienteDao);
		IProdutoDAO produtoDao = new ProdutoDAO();
		produtoService = new ProdutoService(produtoDao);
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		this.cliente = new Cliente();
		this.cliente.setCpf(12312312312L);
		this.cliente.setNome("Rodrigo");
		clienteService.cadastrar(this.cliente);

		this.produto = new Produto();
		this.produto.setCodigo("A001");
		this.produto.setNome("Produto 1");
		this.produto.setDescricao("Produto 1");
		this.produto.setValor(BigDecimal.valueOf(100));
		produtoService.cadastrar(this.produto);
	}

	@Test
	public void testCadastrar() throws TipoChaveNaoEncontradaException {
		Venda venda = new Venda();
		venda.setCliente(this.cliente);
		venda.setCodigo(1L);
		venda.setDataVenda(Instant.now());
		venda.setStatus(Status.INICIADA);
		venda.addProduto(this.produto, 2);

		Boolean retorno = vendaService.cadastrar(venda);
		assertTrue(retorno);
	}

	@Test
	public void testConsultar() throws TipoChaveNaoEncontradaException {
		Venda venda = new Venda();
		venda.setCliente(this.cliente);
		venda.setCodigo(2L);
		venda.setDataVenda(Instant.now());
		venda.setStatus(Status.INICIADA);
		venda.addProduto(this.produto, 2);
		vendaService.cadastrar(venda);

		Venda vendaConsultada = vendaService.consultar(2L);
		assertNotNull(vendaConsultada);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	}

	@Test
	public void testExcluir() throws TipoChaveNaoEncontradaException {
		Venda venda = new Venda();
		venda.setCliente(this.cliente);
		venda.setCodigo(3L);
		venda.setDataVenda(Instant.now());
		venda.setStatus(Status.INICIADA);
		venda.addProduto(this.produto, 2);
		vendaService.cadastrar(venda);

		assertNotNull(vendaService.consultar(3L));
		vendaService.excluir(3L);
		assertNull(vendaService.consultar(3L));
	}

	@Test
	public void testAlterar() throws TipoChaveNaoEncontradaException {
		Venda venda = new Venda();
		venda.setCliente(this.cliente);
		venda.setCodigo(4L);
		venda.setDataVenda(Instant.now());
		venda.setStatus(Status.INICIADA);
		venda.addProduto(this.produto, 2);
		vendaService.cadastrar(venda);

		Venda vendaConsultada = vendaService.consultar(4L);
		assertNotNull(vendaConsultada);

		vendaConsultada.addProduto(this.produto, 1);
		vendaConsultada.setStatus(Status.CONCLUIDA);
		vendaService.alterar(vendaConsultada);

		Venda vendaAlterada = vendaService.consultar(4L);
		assertEquals(Status.CONCLUIDA, vendaAlterada.getStatus());
		assertEquals(3, vendaAlterada.getProdutos().stream().mapToInt(p -> p.getQuantidade()).sum());
	}
}

