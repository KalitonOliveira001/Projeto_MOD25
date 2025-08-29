package br.com.rpires.dao;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super();
		Class<Cliente> classe = Cliente.class;
		setTipoClasse(classe);
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setTel(entity.getTel());
		entityCadastrado.setEnd(entity.getEnd());
		entityCadastrado.setNumero(entity.getNumero());
		entityCadastrado.setCidade(entity.getCidade());
		entityCadastrado.setEstado(entity.getEstado());
	}

}

