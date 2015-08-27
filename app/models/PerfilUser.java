package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class PerfilUser extends Model {
	@Id
	@GeneratedValue
	private Long idPerfilUser;
	@Column(nullable = false)
	private String descricao;
	
	public static Model.Finder<Long, PerfilUser> find = new Model.Finder<Long, PerfilUser>(
			Long.class,PerfilUser.class);

	
	public Long getIdPerfilUser() {
		return idPerfilUser;
	}
	public void setIdPerfilUser(Long idPerfilUser) {
		this.idPerfilUser = idPerfilUser;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	

}
