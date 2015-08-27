package models;


public interface ObjetoRastreavel {

	/**
	 * Método básico que deve ser implementado
	 * Muda a situação do objeto para uma que será passada por parâmetro
	 */
	public void mudarStatus(String descricaoNovaSituacao, String motivo, Usuario usuario);
	
}
