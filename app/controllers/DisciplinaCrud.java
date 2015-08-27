package controllers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import models.Disciplina;
import models.Professor;
import models.SearchDiscProf;
import models.Turma;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class DisciplinaCrud extends Controller {

	private static final Form<Disciplina> formDiscip = Form
			.form(Disciplina.class);

	public static Result listarDisciplina() {
		List<Professor> professor = Professor.find.findList();
		List<Disciplina> disciplina = Disciplina.find.findList();
		List<Turma> turmas = Turma.find.findList();

		return ok(views.html.disciplina.render(professor, turmas, disciplina));

	}

	public static Result gravarDisciplina() {
		Form<Disciplina> form = formDiscip.bindFromRequest();
		String idProf = Form.form().bindFromRequest().get("idProf");
		String idTurma = Form.form().bindFromRequest().get("idTurma");

		if (form.hasErrors()) {
			flash("erro", "Erro ao gravar dados");
			return redirect(routes.DisciplinaCrud.listarDisciplina());
		}
		Disciplina disciplina = form.get();
		disciplina.setIdProfessor(Long.parseLong(idProf));
		disciplina.setIdTurma(Long.parseLong(idTurma));
		disciplina.save();

		List<Object> d = Disciplina.find.findIds();
		Long idDisciplina = (Long) d.get(d.size() - 1);

		SearchDiscProf auxDiscProf = new SearchDiscProf();

		auxDiscProf.setIdDisciplina(idDisciplina);
		auxDiscProf.setIdProfessor(disciplina.getIdProfessor());
		/**
		 * Condicional responsavel por determinar o periodo no qual a disciplina
		 * foi ministrada por um professor.,
		 */
		int mes = LocalDate.now().getMonth().getValue();
		if (mes <= 6) {
			auxDiscProf.setPeriodo(LocalDate.now().getYear() + ".1");
		} else {
			auxDiscProf.setPeriodo(LocalDate.now().getYear() + ".2");
		}
		auxDiscProf.save();

		flash("sucesso", "Dados Gravados com Sucesso");

		return redirect(routes.DisciplinaCrud.listarDisciplina());
	}

	public static Result detalharDisciplina(Long id) {

		Form<Disciplina> formD = Form.form(Disciplina.class).fill(
				Disciplina.find.byId(id));
		List<Professor> professor = Professor.find.findList();
		List<Turma> turmas = Turma.find.findList();

		return ok(views.html.alterarDisciplina.render(id, formD, professor,
				turmas));
	}

	public static Result alterarDisciplina(Long id) {

		Form.form(Disciplina.class).fill(Disciplina.find.byId(id));
		List<Professor> professor = Professor.find.findList();
		List<Turma> turmas = Turma.find.findList();
		Form<Disciplina> alterForm = Form.form(Disciplina.class)
				.bindFromRequest();

		if (alterForm.hasErrors()) {
			return badRequest(views.html.alterarDisciplina.render(id,
					alterForm, professor, turmas));
		}

		String idProf = Form.form().bindFromRequest().get("idProf");
		alterForm.get().setIdProfessor(Long.parseLong(idProf));
		alterForm.get().update(id);
		flash("sucesso", "Disciplina " + alterForm.get().getNome()
				+ " alterado com sucesso");

		return redirect(routes.DisciplinaCrud.listarDisciplina());
	}

	public static Result removerDisciplina(Long id) {
		Disciplina.find.ref(id).delete();
		flash("sucesso", "Disciplina removida com sucesso");
		return listarDisciplina();
	}

}
