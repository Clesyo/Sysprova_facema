package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Prova extends Model {

	@Id
	@GeneratedValue
	private Long idProva;

	public static Model.Finder<Long, Prova> find = new Model.Finder<Long, Prova>(
			Long.class, Prova.class);
	
	@OneToOne
	private Turma turma = new Turma();
	
	
	private String dataInicial;
	private String dataFinal;
	
	
	/**
	 * Gets e Sets
	 */
	public Long getIdProva() {
		return idProva;
	}

	public void setIdProva(Long idProva) {
		this.idProva = idProva;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Turma
	 * 
	 */
	public Long getIdTurma(){
		return this.turma.getIdTurma();
	}
	public void setIdTurma(Long idTurma){
		this.turma.setIdTurma(idTurma);
	}
	
	/**
	 * 
	 * Metodo para recuperar o ID da ultima Prova de uma determinada
	 * prova de uma Turma 
	 * 
	 */
	public static Long findByIdProva(Long idTurma){
		List<Prova> lProva = Prova.find.where().eq("turma_id_turma", idTurma).findList();
		
		if(lProva.size() > 0 ){
		Long id = lProva.get(lProva.size() - 1).getIdProva();
		return id;
		}
	
		return null;
	}
}
