package controllers;

import java.time.LocalDate;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.Alternativa;
import models.Curso;
import models.Disciplina;
import models.Observacao;
import models.Professor;
import models.Prova;
import models.Questao;
import models.Turma;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class QuestaoCrud extends Controller {

	private static final Form<Questao> formQuest = Form.form(Questao.class);
	private static final Form<Alternativa> formAlter = Form
			.form(Alternativa.class);

	public static Result listagem() {

		return ok(views.html.listagem.render());
	}

	public static Result parseId(Long idDiscip, Long idProva) {

		return ok(views.html.questao.render(idDiscip, idProva));
	}

	public static Result gravarQuestao() {
		Form<Questao> formQuestao = formQuest.bindFromRequest();
		Form<Alternativa> formAlternativa = formAlter.bindFromRequest();

		String idDiscip = Form.form().bindFromRequest().get("idDiscip");
		String idProva = Form.form().bindFromRequest().get("idProva");

		if (formQuestao.hasErrors() && formAlternativa.hasErrors()) {
			flash("erro", "Erro ao gravar dados");
			return ok(views.html.questao.render(Long.parseLong(idDiscip),
					Long.parseLong(idProva)));
		}

		Questao questao = formQuestao.get();
		questao.setIdDisciplina(Long.parseLong(idDiscip));
		questao.setIdProva(Long.parseLong(idProva));
		questao.setAvalCoord(false);
		questao.setAvalNucleo(false);
		questao.setQuestao_ok(false);

		int mes = LocalDate.now().getMonth().getValue();
		if (mes <= 6) {
			questao.setPeriodo(LocalDate.now().getYear() + ".1");
		} else {
			questao.setPeriodo(LocalDate.now().getYear() + ".2");
		}
		questao.save();

		List<Questao> listQuest = Questao.find.findList();
		long idQuest = listQuest.get(listQuest.size() - 1).getIdQuestao();

		Alternativa alternativa = formAlternativa.get();
		alternativa.setIdQuestao(idQuest);
		alternativa.save();
		flash("sucesso", "Dados Gravados com sucesso");
		return redirect(routes.QuestaoCrud.listagem());
	}

	//coordenador
	public static Result alterarQuestaoCoord(Long idQ, Long idA) {

		Form<Questao> questao = formQuest.bindFromRequest();
		Form<Alternativa> alternatica = formAlter.bindFromRequest();

		String id = Form.form().bindFromRequest().get("idD");
		if (questao.hasErrors() && alternatica.hasErrors()) {
			List<Questao> lista = Questao.find.where()
					.eq("disciplina_id_disciplina", Long.parseLong(id))
					.findList();
			return badRequest(views.html.correcaoQuestao.render(
					Long.parseLong(id), lista));
		}

		Questao q = questao.get();
		q.setAvalCoord(true);
		q.setQuestao_ok(false);

		List<Observacao> obs = Observacao.find.where()
				.eq("questao_id_questao", q.getIdQuestao()).findList();
		
		for(int i = 0; i < obs.size(); i++){
			Observacao o = new Observacao();
			o = obs.get(i);
			o.setQuestaoOkCoord(true);
			o.update();
		}
		
//		Questao j = Questao.find.byId(idQ);

		Alternativa a = alternatica.get();

//		System.out.println("Cod. Questao:" + idQ);
//		System.out.println("Cod. Alternativa:" + idA);
//		System.out.println();
//		System.out.println("ENUNCIADO:");
//		System.out.println(q.getEnunciado());
//		System.out.println();
//		System.out.println("A) " + a.getAlter01());
//		System.out.println("B) " + a.getAlter02());
//		System.out.println("C) " + a.getAlter03());
//		System.out.println("D) " + a.getAlter04());
//		System.out.println("E) " + a.getAlter05());
//		System.out.println("--------------------------");
//		System.out.println(id);
//		System.out.println("N.A: "+ q.isAvalNucleo());
//		System.out.println("COOR: "+ q.isAvalCoord());
		
		
		//q.update(idQ);
		//a.update(idA);

		return redirect(routes.Avaliacao.corrigirQuestao(Long.parseLong(id)));
	}
	
	
	//nucleo
	public static Result alterarQuestaNucleo(Long idQ, Long idA) {

		Form<Questao> questao = formQuest.bindFromRequest();
		Form<Alternativa> alternatica = formAlter.bindFromRequest();

		String id = Form.form().bindFromRequest().get("idD");
		if (questao.hasErrors() && alternatica.hasErrors()) {
			List<Questao> lista = Questao.find.where()
					.eq("disciplina_id_disciplina", Long.parseLong(id))
					.findList();
			return badRequest(views.html.correcaoQuestao.render(
					Long.parseLong(id), lista));
		}

		Questao q = questao.get();
		Alternativa a = alternatica.get();

		q.setAvalCoord(true);
		
		q.setQuestao_ok(true);

		List<Observacao> obs = Observacao.find.where()
				.eq("questao_id_questao", q.getIdQuestao()).findList();
		
		for(int i = 0; i < obs.size(); i++){
			Observacao o = new Observacao();
			o = obs.get(i);
			o.setQuestaoOkNucleo(true);
			o.update();
		}
		
Questao j = Questao.find.byId(idQ);

//		Alternativa a = alternatica.get();
//
//		System.out.println("Cod. Questao:" + idQ);
//		System.out.println("Cod. Alternativa:" + idA);
//		System.out.println();
//		System.out.println("ENUNCIADO:");
//		System.out.println(q.getEnunciado());
//		System.out.println();
//		System.out.println("A) " + a.getAlter01());
//		System.out.println("B) " + a.getAlter02());
//		System.out.println("C) " + a.getAlter03());
//		System.out.println("D) " + a.getAlter04());
//		System.out.println("E) " + a.getAlter05());
//		System.out.println("--------------------------");
//		System.out.println(id);
//		System.out.println("N.A: "+ q.isAvalNucleo());
//		System.out.println("COOR: "+ q.isAvalCoord());
		
		
		 q.update(idQ);
		 a.update(idA);

		return redirect(routes.Avaliacao.corrigirQuestao(Long.parseLong(id)));
	}

}
