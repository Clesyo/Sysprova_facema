package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.h2.engine.Mode;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Questao extends Model implements ObjetoRastreavel {

	@Id
	@GeneratedValue
	private Long idQuestao;

	private String enunciado;
	public Integer auxiliar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questao")
	private List<Alternativa> alternativas;

	@ManyToOne
	private Disciplina disciplina = new Disciplina();
	@ManyToOne
	private Prova prova = new Prova();

	private String periodo;

	private boolean avalCoord;
	private boolean avalNucleo;
	
	private boolean questao_ok;
	
	public static Model.Finder<Long, Questao> find = new Model.Finder<Long, Questao>(
			Long.class, Questao.class);

	/**
	 * Gets e Sets
	 */
	
	
	public boolean isQuestao_ok() {
		return questao_ok;
	}

	public Integer getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Integer auxiliar) {
		this.auxiliar = auxiliar;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public void setQuestao_ok(boolean questao_ok) {
		this.questao_ok = questao_ok;
	}
	
	public boolean isAvalCoord() {
		return avalCoord;
	}

	public void setAvalCoord(boolean avalCoord) {
		this.avalCoord = avalCoord;
	}

	public boolean isAvalNucleo() {
		return avalNucleo;
	}

	public void setAvalNucleo(boolean avalNucleo) {
		this.avalNucleo = avalNucleo;
	}

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Disciplina
	 * 
	 */
	public Long getIdDisciplina() {
		return this.disciplina.getIdDisciplina();
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.disciplina.setIdDisciplina(idDisciplina);
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Prova
	 * 
	 */

	public Long getIdProva() {
		return this.prova.getIdProva();
	}

	public void setIdProva(Long idProva) {
		this.prova.setIdProva(idProva);
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;

	}

	/**
	 * 
	 * Retorna a quantidade de questões já cadastradas por disciplinas
	 * 
	 */
	public static int returnQtdQuestCadastrada(Long idDiscip) {

		int qtdQuestCadastrada = Questao.find.where()
				.eq("disciplina_id_disciplina", idDiscip).findList().size();

		return qtdQuestCadastrada;
	}
	
	/**
	 * 
	 * Metodo responsável por retornar uma lista de Status de uma Questão
	 *  
	 */

	public List<Status> getListaStatus() {
		List<Status> listStatus = Status.find.where()
				.eq("questao_id_questao", this.idQuestao).findList();

		return listStatus;
	}

	public Questao() {
		this.alternativas = new ArrayList<Alternativa>();
	}

	@Override
	public void mudarStatus(String descricaoNovaSituacao, String motivo,
			Usuario usuario) {
		// TODO Auto-generated method stub

	}
	@Override
	public String toString() {
		return getEnunciado();
	}

}