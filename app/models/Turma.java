package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Turma extends Model {

	@Id
	@GeneratedValue
	private Long idTurma;
	private String nome;
	private int qtdQuestao;
	

	public static Model.Finder<Long, Turma> find = new Model.Finder<Long, Turma>(
			Long.class, Turma.class);
	

	@ManyToOne
	private Curso curso = new Curso();
	
	/**
	 * Gets e Sets
	 */
	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdQuestao() {
		return qtdQuestao;
	}

	public void setQtdQuestao(int qtdQuestao) {
		this.qtdQuestao = qtdQuestao;
	}
	
	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Curso
	 * 
	 */
	public long getIdCurso() {
		return curso.getIdCurso();
	}

	public void setIdCurso(Long idCurso) {
		this.curso.setIdCurso(idCurso);
	}
	
	

}
