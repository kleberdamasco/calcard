package br.com.kleber.calcard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.kleber.calcard.dto.ClienteDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table
@Entity
@ApiModel(description = "Detalhes do cliente")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id gerado automaticamente")
    private Long id;
	
	private String nome;
	private String cpf;
	private Integer idade;
	private String sexo;
	private String estadoCivil;
	private String estado;
	private Integer dependentes;
	private Double renda;
	private String resultado;
	private String limite;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(ClienteDTO clienteDTO, String resultado, String limite) {
		this.nome = clienteDTO.getNome();
		this.cpf = clienteDTO.getCpf();
		this.idade = clienteDTO.getIdade();
		this.sexo = clienteDTO.getSexo();
		this.estadoCivil = clienteDTO.getEstadoCivil();
		this.dependentes = clienteDTO.getDependentes();
		this.renda = clienteDTO.getRenda();
		this.resultado = resultado;
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getLimite() {
		return limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", sexo=" + sexo
				+ ", estadoCivil=" + estadoCivil + ", estado=" + estado + ", dependentes=" + dependentes + ", renda="
				+ renda + "]";
	}
	
}
