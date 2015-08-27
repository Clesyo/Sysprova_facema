package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Observacao extends Model {

	@Id
	@GeneratedValue
	private Long idObs;
	private String descricao;

	@ManyToOne
	private Questao questao = new Questao();

	private String avaliador;
	private boolean questaoOkCoord;
	private boolean questaoOkNucleo;
	private boolean questaoOk;
	
	public static Model.Finder<Long, Observacao> find = new Model.Finder<>(
			Long.class, Observacao.class);

	/**
	 * 
	 * Gets e Sets
	 */

	
	public Long getIdObs() {
		return idObs;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public boolean isQuestaoOkCoord() {
		return questaoOkCoord;
	}

	public void setQuestaoOkCoord(boolean questaoOkCoord) {
		this.questaoOkCoord = questaoOkCoord;
	}

	public boolean isQuestaoOkNucleo() {
		return questaoOkNucleo;
	}

	public void setQuestaoOkNucleo(boolean questaoOkNucleo) {
		this.questaoOkNucleo = questaoOkNucleo;
	}

	public void setIdObs(Long idObs) {
		this.idObs = idObs;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getIdQuestao() {
		return questao.getIdQuestao();
	}

	public void setIdQuestao(long idQuestao) {
		this.questao.setIdQuestao(idQuestao);
		;
	}

	public String getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(String avaliador) {
		this.avaliador = avaliador;
	}

	public boolean isQuestaoOk() {
		return questaoOk;
	}

	public void setQuestaoOk(boolean questaoOk) {
		this.questaoOk = questaoOk;
	}

	
	

	
	
}
