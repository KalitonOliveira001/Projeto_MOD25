package br.com.rpires.service;

import br.com.rpires.dao.IVendaDAO;
import br.com.rpires.domain.Venda;
import br.com.rpires.service.generic.GenericService;

public class VendaService extends GenericService<Venda, Long> implements IVendaService {

	public VendaService(IVendaDAO dao) {
		super(dao);
	}

}

