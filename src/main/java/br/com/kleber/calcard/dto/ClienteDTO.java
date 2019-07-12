package br.com.kleber.calcard.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Detalhes do cliente")
public class ClienteDTO {

	private String nome;
	private String cpf;
	private Integer idade;
	private String sexo;
	private String estadoCivil;
	private String estado;
	private Integer dependentes;
	private Double renda;
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getDependentes() {
		return dependentes;
	}
	public void setDependentes(Integer dependentes) {
		this.dependentes = dependentes;
	}
	public Double getRenda() {
		return renda;
	}
	public void setRenda(Double renda) {
		this.renda = renda;
	}

	@Override
	public String toString() {
		return "ClienteDTO [nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", sexo=" + sexo + ", estadoCivil="
				+ estadoCivil + ", estado=" + estado + ", dependentes=" + dependentes + ", renda=" + renda + "]";
	}
}
