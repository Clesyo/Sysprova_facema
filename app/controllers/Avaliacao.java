package controllers;

import java.util.List;

import models.Observacao;
import models.Questao;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Avaliacao extends Controller {

	public static Result avaliacaoCoord() {
		return ok(views.html.avaliacaoCoord.render());
	}

	public static Result avaliacaoNU() {
		return ok(views.html.avaliacaoNucleo.render());
	}
	
	public static Result funcaoProf() {
		return ok(views.html.funcaoProfessor.render());
	}
	
	public static Result listagemCorrecao(){
		return ok(views.html.listagemCorrecao.render());
	}

	public static Result avaliarQuestaoCoord(Long idDiscip, String responsavel) {

		List<Questao> lista = Questao.find.where()
				.eq("disciplina_id_disciplina", idDiscip).findList();
		return ok(views.html.listaQuestaoCoord.render(idDiscip, lista, responsavel));
	}

	public static Result avaliarQuestaoNA(Long idDiscip, String responsavel) {

		List<Questao> lista = Questao.find.where()
				.eq("disciplina_id_disciplina", idDiscip).findList();
		return ok(views.html.listaQuestaoNA.render(idDiscip, lista, responsavel));
	}
	
	public static Result corrigirQuestao(Long idDiscip){
		
		List<Questao> lista = Questao.find.where()
				.eq("disciplina_id_disciplina", idDiscip).findList();
		return ok(views.html.correcaoQuestao.render(idDiscip, lista));
	}

	public static Result registroObservacao(Long idQuestao) {

		Observacao obs = new Observacao();

		String responsavel = Form.form().bindFromRequest().get("resp");
		String idD = Form.form().bindFromRequest().get("idD");
		String descricao = Form.form().bindFromRequest().get("descricao");
        
		obs.setIdQuestao(idQuestao);
		
		System.out.println("Descrição - > "+descricao);
		
	
		
		
		if (responsavel.equals("co")) {

			if(descricao.equals("")){
				obs.setDescricao("QUESTÃO OK!");
				obs.setQuestaoOkCoord(true);
			}else{
				obs.setDescricao(descricao);
				obs.setQuestaoOkCoord(true);			
			}
			obs.setAvaliador("coordenador");
		} else if (responsavel.equals("nu")) {

			if(descricao.equals("")){
				obs.setDescricao("QUESTÃO OK!");
				obs.setQuestaoOkNucleo(true);
				obs.setAvaliador("nucleo");
			
			}else{
				obs.setDescricao(descricao);
				obs.setQuestaoOkNucleo(true);
				obs.setAvaliador("nucleo");
			}
			
		}
       
		obs.save();

		if (responsavel.equals("co")) {

			return redirect(routes.Avaliacao.avaliarQuestaoCoord(
					Long.parseLong(idD), responsavel));
		} else {
			return redirect(routes.Avaliacao.avaliarQuestaoNA(
					Long.parseLong(idD), responsavel));
		}
	}
}
