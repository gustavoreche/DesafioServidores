package br.com.servidor.aplicacao;

public class IniciaSistema {
	
	private ExibeInformacaoServidor exibeServidores;
	private EntradaDeDados entradaDeDados;
	
	public IniciaSistema(ExibeInformacaoServidor exibeServidores, EntradaDeDados entradaDeDados) {
		this.exibeServidores = exibeServidores;
		this.entradaDeDados = entradaDeDados;
	}
	
	public void executa() {
		new LogicaDoSistema(this.exibeServidores, this.entradaDeDados).executa();
	}

}
