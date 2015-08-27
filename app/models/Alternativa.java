package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Alternativa extends Model {

	@Id
	@GeneratedValue
	private Long idAlternativa;
	@Column(nullable = false)
	private String alter01;
	@Column(nullable = false)
	private String alter02;
	@Column(nullable = false)
	private String alter03;
	@Column(nullable = false)
	private String alter04;
	@Column(nullable = false)
	private String alter05;
	
	public static Model.Finder<Long, Alternativa> find = new Model.Finder<Long, Alternativa>(
			Long.class, Alternativa.class);

	@ManyToOne
	@Column(nullable = false)
	private Questao questao = new Questao();

	/**
	 * Gets e Sets
	 */
	public Long getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	public String getAlter01() {
		return alter01;
	}

	public void setAlter01(String alter01) {
		this.alter01 = alter01;
	}

	public String getAlter02() {
		return alter02;
	}

	public void setAlter02(String alter02) {
		this.alter02 = alter02;
	}

	public String getAlter03() {
		return alter03;
	}

	public void setAlter03(String alter03) {
		this.alter03 = alter03;
	}

	public String getAlter04() {
		return alter04;
	}

	public void setAlter04(String alter04) {
		this.alter04 = alter04;
	}

	public String getAlter05() {
		return alter05;
	}

	public void setAlter05(String alter05) {
		this.alter05 = alter05;
	}

	public Questao getQuestao() {
		return questao;
	}
	

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	public Long getIdQuestao(){
		return this.questao.getIdQuestao();
	}
	
	public void setIdQuestao(Long idQuestao){
		this.questao.setIdQuestao(idQuestao);
	}

	
	
}
