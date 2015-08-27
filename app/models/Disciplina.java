package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Disciplina extends Model {

	@Id
	@GeneratedValue
	private Long idDisciplina;
	private String nome;
	private Integer qtdQuestao;
	private int cargaHoraria;

	public static Model.Finder<Long, Disciplina> find = new Model.Finder<Long, Disciplina>(
			Long.class, Disciplina.class);

	@ManyToOne
	private Professor professor = new Professor();
	@ManyToOne
	private Turma turma = new Turma();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
	private List<Questao> questoes;

	/**
	 * Gets e Sets
	 */
	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdQuestao() {
		return qtdQuestao;
	}

	public void setQtdQuestao(Integer qtdQuestao) {
		this.qtdQuestao = qtdQuestao;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Professor
	 * 
	 */
	public long getIdProfessor() {
		return professor.getIdProf();
	}

	public void setIdProfessor(long idProf) {
		this.professor.setIdProf(idProf);
	}

	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Turma
	 * 
	 */
	public Long getIdTurma() {
		return turma.getIdTurma();
	}

	public void setIdTurma(Long idTurma) {
		this.turma.setIdTurma(idTurma);
		;
	}

	/**
	 * 
	 * Metodo respnsavel por recuperar um objeto do tipo Disciplina.
	 * 
	 */
	public static Disciplina byIdDisciplina(Long idProva, Long idProf) {
		Prova prova = Prova.find.byId(idProva);// o ID é o que estará na questão

		Turma turma = Turma.find.byId(prova.getIdTurma());// o ID é o que estará
															// na prova;

		Professor prof = Professor.find.byId(idProf);

		List<Disciplina> disciplinas = Disciplina.find.where()
				.eq("turma_id_turma", turma.getIdTurma()).findList();

		for (Disciplina d : disciplinas) {
			if (prof.getIdProf().compareTo(d.getIdProfessor()) == 0) {

				return d;
			}
		}
		return null;

	}


	public Disciplina() {
		this.questoes = new ArrayList<Questao>();
	}

}
