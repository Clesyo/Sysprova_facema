package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class SearchDiscProf extends Model{
	
	@Id
	@GeneratedValue
	private Long idSearch;
	
	@OneToOne
	private Disciplina disciplina = new Disciplina();
	
	@OneToOne
	private Professor professor = new Professor();
	
	private String periodo;

	/**
	 * 
	 * Gets e Sets
	 */
	
	public Long getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Long idSearch) {
		this.idSearch = idSearch;
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Disciplina
	 * 
	 */
	public Long getIdDisciplina() {
		return disciplina.getIdDisciplina();
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.disciplina.setIdDisciplina(idDisciplina);;
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Professor
	 * 
	 */
	public Long getIdProfessor() {
		return professor.getIdProf();
	}
	
	public void setIdProfessor(Long idProfessor) {
		this.professor.setIdProf(idProfessor);;
	}
	

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	

}
