package br.com.servidor.aplicacao;

import java.util.List;

import br.com.servidor.modelo.Usuario;

public interface ExibeInformacaoServidor {
	
	public void exibeServidoresPopulados(List<Usuario> usuarios);
	public void exibeCusto();
	
	public List<String> retornaHistorico();
	public long retornaCusto();

}
