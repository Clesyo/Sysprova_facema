package controllers;

import java.util.List;

import models.Curso;
import models.Turma;
import models.Turma;
import models.Turma;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class TurmaCrud extends Controller {

	private static final Form<Turma> turmaForm = Form.form(Turma.class);

	public static Result listarTurma() {
		List<Curso> curso = Curso.find.findList();
		List<Turma> turma = Turma.find.findList();		

		return ok(views.html.turma.render(curso, turmaForm, turma));

	}

	public static Result gravarTurma() {

		Form<Turma> form = turmaForm.bindFromRequest();
		String idCurso = Form.form().bindFromRequest().get("idCurso");
		List<Curso> curso = Curso.find.findList();
		List<Turma> listTurma = Turma.find.findList();		

		if(form.hasErrors()){
			flash("erro", "Erro ao gravar os dados");
			return ok(views.html.turma.render(curso, turmaForm, listTurma));
		}
		Turma turma = form.get();
		turma.setIdCurso(Long.parseLong(idCurso));	
		turma.save();
		flash("sucesso", "Dados Gravados com sucesso");
		return redirect(routes.TurmaCrud.listarTurma());

	}
	public static Result detalharTurma(Long id){
		Form<Turma> formT = Form.form(Turma.class).fill(Turma.find.byId(id));
		List<Curso> curso = Curso.find.findList();
		return ok(views.html.alterarTurma.render(id, formT, curso));
	}
	
	public static Result alterarTurma(Long id) {
		
		Form.form(Turma.class).fill(Turma.find.byId(id));
		List<Curso> curso = Curso.find.findList();
		Form<Turma> alterarForm = Form.form(Turma.class).bindFromRequest();
		if (alterarForm.hasErrors()) {
			return badRequest(views.html.alterarTurma.render(id, alterarForm, curso));
		}
		String idCurso = Form.form().bindFromRequest().get("idCurso");
		alterarForm.get().setIdCurso(Long.parseLong(idCurso));
		alterarForm.get().update(id);
		flash("sucesso", "Turma " + alterarForm.get().getNome()
				+ " alterado com sucesso");

		return redirect(routes.TurmaCrud.listarTurma());
	}
	
	public static Result removerTurma(Long id){
		Turma.find.ref(id).delete();
		flash("sucesso", "Turma removido com sucesso");
		return listarTurma();
		
	}

}
