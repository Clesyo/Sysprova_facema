package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Professor extends Model {

	@Id
	@GeneratedValue
	private Long idProf;

	private String nome;

	@Column(unique = false, nullable = false)
	private Integer cpf;
    private String tipoProfe;
	private String email;
	@Column(nullable = false)
	private Integer matricula;

	private int ativo;
	


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
	private List<Disciplina> disciplinas;
	
	
	@ManyToOne 
	private Curso curso = new Curso();
	
	@OneToOne 
	private Usuario usuario = new Usuario();
	
	public static Model.Finder<Long, Professor> find = new Model.Finder<Long, Professor>(
			Long.class, Professor.class);


	/**
	 * Gets e Sets
	 */
	
	
	public String getTipoProfe() {
		return tipoProfe;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setTipoProfe(String tipoProfe) {
		this.tipoProfe = tipoProfe;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Long getIdProf() {
		return idProf;
	}

	public void setIdProf(Long idProf) {
		this.idProf = idProf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	
	/**
	 * 
	 * Metodos para manipulção dos IDs da entidade Curso
	 * 
	 */
	public Long getIdCurso(){
		return this.curso.getIdCurso();
	}
	public void setIdCurso(Long idCurso){
		this.curso.setIdCurso(idCurso);
	}
	public Professor() {
		this.disciplinas = new ArrayList<Disciplina>();
	}
}
