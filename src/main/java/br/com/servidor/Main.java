package br.com.servidor;

import br.com.servidor.aplicacao.IniciaSistema;
import br.com.servidor.infraestrutura.DadosManuais;
import br.com.servidor.infraestrutura.ExibeNoConsole;

public class Main {
	
	public static void main(String[] args) {
		
		DadosManuais dadosManuais = new DadosManuais();
		ExibeNoConsole exibeNoConsole = new ExibeNoConsole();
		
		new IniciaSistema(exibeNoConsole, dadosManuais).executa();
	}

}
