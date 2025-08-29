package br.com.rpires.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.rpires.domain.Persistente;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T, E> {

	private Map<E, T> map;

	private Class<T> tipoClasse;

	public abstract void atualizarDados(T entity, T entityCadastrado);

	public GenericDAO() {
		this.map = new HashMap<>();
	}

	public Class<T> getTipoClasse() {
		return tipoClasse;
	}

	public void setTipoClasse(Class<T> tipoClasse) {
		this.tipoClasse = tipoClasse;
	}

	@Override
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
		if (this.map.containsKey(getChave(entity))) {
			return false;
		}

		this.map.put(getChave(entity), entity);
		return true;
	}

	@Override
	public void excluir(E valor) {
		this.map.remove(valor);
	}

	@Override
	public void alterar(T entity) throws TipoChaveNaoEncontradaException {
		T entityCadastrado = this.map.get(getChave(entity));
		if (entityCadastrado != null) {
			atualizarDados(entity, entityCadastrado);
		}
	}

	@Override
	public T consultar(E valor) {
		return this.map.get(valor);
	}

	@Override
	public Collection<T> buscarTodos() {
		return this.map.values();
	}

	@SuppressWarnings("unchecked")
	private E getChave(T entity) {
		if (entity instanceof Cliente) {
			return (E) ((Cliente) entity).getCpf();
		} else if (entity instanceof Produto) {
			return (E) ((Produto) entity).getCodigoProduto();
		} else if (entity instanceof Venda) {
			return (E) ((Venda) entity).getCodigo();
		}
		return null;
	}

}

