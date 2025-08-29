package br.com.rpires.dao;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Venda;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {

	public VendaDAO() {
		super();
		Class<Venda> classe = Venda.class;
		setTipoClasse(classe);
	}

	@Override
	public void atualizarDados(Venda entity, Venda entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setCliente(entity.getCliente());
		// entityCadastrado.setProdutos(entity.getProdutos()); // Removido, pois não há setter para Set<ProdutoQuantidade>
		entityCadastrado.setStatus(entity.getStatus());
		entityCadastrado.setDataVenda(entity.getDataVenda());
	}

}

