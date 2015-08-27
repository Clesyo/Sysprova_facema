package controllers;

import java.util.List;

import models.Curso;
import models.Professor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ProfessorCrud extends Controller {

	private static final Form<Professor> pForm = Form.form(Professor.class);

	public static Result listarProfessor() {
		List<Professor> professores = Professor.find.findList();
		List<Curso> cursos = Curso.find.findList();
		return ok(views.html.professor.render(professores, cursos));

	}

	public static Result gravarProfessor() {

		Form<Professor> form = pForm.bindFromRequest();
		List<Professor> prof = Professor.find.findList();
		List<Curso> cursos = Curso.find.findList();
		if (form.hasErrors()) {
			flash("erro", "Erro ao tentar salvar professor");

			return ok(views.html.professor.render(prof, cursos));
		}
		String idCurso = Form.form().bindFromRequest().get("idCurso");
		String tipo = Form.form().bindFromRequest().get("tipoProfe");
		Professor professor = form.get();
		professor.setIdCurso(Long.parseLong(idCurso));
		professor.setTipoProfe(tipo);
		professor.save();
		flash("sucesso", "Professor Inserido com sucesso!");
		return redirect(routes.ProfessorCrud.listarProfessor());

	}

	public static Result detalhar(Long id) {
		Form<Professor> proForm = Form.form(Professor.class).fill(
				Professor.find.byId(id));
		List<Curso> cursos = Curso.find.findList();
		return ok(views.html.alterarProfessor.render(id, cursos, proForm));
	}

	public static Result alterarProfessor(Long id) {
		Form<Professor> form = pForm.bindFromRequest();
		Form.form(Professor.class).fill(Professor.find.byId(id));
		List<Curso> cursos = Curso.find.findList();

		Form<Professor> alterarProfe = Form.form(Professor.class)
				.bindFromRequest();

		if (alterarProfe.hasErrors()) {
			return badRequest(views.html.alterarProfessor.render(id, cursos,
					alterarProfe));
		}
		
		String idCurso = Form.form().bindFromRequest().get("idCurso");
		alterarProfe.get().setIdCurso(Long.parseLong(idCurso));
		alterarProfe.get().update(id);
		flash("sucesso", "Professor " + alterarProfe.get().getNome()
				+ " alterado com sucesso");

		return redirect(routes.ProfessorCrud.listarProfessor());
	}

	public static Result removerProfessor(Long id) {
		Professor.find.ref(id).delete();
		flash("sucesso", "Professor removido com sucesso");

		return listarProfessor();
	}
}
