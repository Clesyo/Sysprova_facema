package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import play.db.ebean.Model;

@Entity
public class Usuario extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idUsuario;
	@Column(nullable = false)
	private Integer matricula;
	@Column(nullable = false)
	private String senha;
	
    String papel;
	private int ativo;

	@ManyToOne
	private PerfilUser perfil=new PerfilUser();

	
	public static Model.Finder<Long, Usuario> find = new Model.Finder<Long, Usuario>(
			Long.class, Usuario.class);

	/**
	 * Gets e Sets
	 */	
	
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getPerfil() {
		return perfil.getIdPerfilUser();
	}

	public void setPerfil(Long perfil) {
		this.perfil.setIdPerfilUser(perfil);
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	
	
}
