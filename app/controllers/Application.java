package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Usuario;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	// Metodo da pagina principal
	 
	public static Result index() {
		return TODO;
	}

	public static Result inicio() {
		return ok(index.render());
	}
	public static Result inicioSecretaria() {
	
		return ok(indexSecretaria.render());
	}
	
	public static Result inicioProfessor() {
		
		return ok(indexProfessor.render());
	}
public static Result inicioCoordenador() {
		
		return ok(indexCoordenador.render());
	}
public static Result inicioNucleo() {
	
	return ok(indexNucleo.render());
}
	

}
