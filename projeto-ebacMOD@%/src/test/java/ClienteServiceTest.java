package br.com.rpires;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.domain.Cliente;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.service.ClienteService;
import br.com.rpires.service.IClienteService;

public class ClienteServiceTest {

	private IClienteService clienteService;

	private Cliente cliente;

	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDAO();
		clienteService = new ClienteService(dao);
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		// Limpa todos os clientes antes de cada teste
		Collection<Cliente> list = clienteService.buscarTodos();
		Iterator<Cliente> iterator = list.iterator();
		while (iterator.hasNext()) {
			Cliente cli = iterator.next();
			clienteService.excluir(cli.getCpf());
		}
	}

	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12345678901L);
		cliente.setNome("Rodrigo");
		cliente.setTel(1199999999L);
		cliente.setEnd("Rua A");
		cliente.setNumero(10);
		cliente.setCidade("São Paulo");
		cliente.setEstado("SP");
		clienteService.cadastrar(cliente);

		Cliente clienteConsultado = this.clienteService.consultar(this.cliente.getCpf());
		assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(10987654321L);
		cliente.setNome("Teste");
		cliente.setTel(1199999999L);
		cliente.setEnd("Rua B");
		cliente.setNumero(20);
		cliente.setCidade("Rio de Janeiro");
		cliente.setEstado("RJ");
		Boolean retorno = clienteService.cadastrar(cliente);
		assertTrue(retorno);
		
		Collection<Cliente> list = clienteService.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 1);
	}

	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12345678901L);
		cliente.setNome("Rodrigo");
		cliente.setTel(1199999999L);
		cliente.setEnd("Rua A");
		cliente.setNumero(10);
		cliente.setCidade("São Paulo");
		cliente.setEstado("SP");
		clienteService.cadastrar(cliente);

		clienteService.excluir(cliente.getCpf());
		assertNull(clienteService.consultar(cliente.getCpf()));
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12345678901L);
		cliente.setNome("Rodrigo");
		cliente.setTel(1199999999L);
		cliente.setEnd("Rua A");
		cliente.setNumero(10);
		cliente.setCidade("São Paulo");
		cliente.setEstado("SP");
		clienteService.cadastrar(cliente);

		cliente.setNome("Rodrigo Pires");
		clienteService.alterar(cliente);

		Cliente clienteAlterado = clienteService.consultar(cliente.getCpf());
		assertNotNull(clienteAlterado);
		assertEquals("Rodrigo Pires", clienteAlterado.getNome());
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12345678901L);
		cliente.setNome("Rodrigo");
		cliente.setTel(1199999999L);
		cliente.setEnd("Rua A");
		cliente.setNumero(10);
		cliente.setCidade("São Paulo");
		cliente.setEstado("SP");
		clienteService.cadastrar(cliente);

		Collection<Cliente> list = clienteService.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 1);
	}

	@After
	public void end() {
		Collection<Cliente> list = clienteService.buscarTodos();
		Iterator<Cliente> iterator = list.iterator();
		while (iterator.hasNext()) {
			Cliente cli = iterator.next();
			clienteService.excluir(cli.getCpf());
		}
	}
}

