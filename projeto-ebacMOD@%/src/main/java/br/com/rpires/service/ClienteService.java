package br.com.rpires.service;

import br.com.rpires.dao.IClienteDAO;

import br.com.rpires.domain.Cliente;
import br.com.rpires.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
	}

}

