package br.com.rpires.domain;

public class Cliente implements Persistente {

	private Long cpf;
	private String nome;
	private Long tel;
	private String end;
	private Integer numero;
	private String cidade;
	private String estado;

	public Cliente(Long cpf, String nome, Long tel, String end, Integer numero, String cidade, String estado) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.tel = tel;
		this.end = end;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Cliente() {

	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public Long getCodigo() {
		return this.cpf;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + "]";
	}


}

