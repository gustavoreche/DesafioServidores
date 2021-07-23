package br.com.servidor.infraestrutura;

import java.util.ArrayList;
import java.util.List;

import br.com.servidor.aplicacao.EntradaDeDados;

public class DadosManuaisTest implements EntradaDeDados {
	
	private int quantidadeQueUmUsuarioPermaneceNoServidor;
	private int numeroDeUsuarioPorServidor; 
	private List<Integer> usuarios;
	
	public DadosManuaisTest(int quantidadeQueUmUsuarioPermaneceNoServidor, int numeroDeUsuarioPorServidor, 
			List<Integer> usuarios) {
		this.quantidadeQueUmUsuarioPermaneceNoServidor = quantidadeQueUmUsuarioPermaneceNoServidor;
		this.numeroDeUsuarioPorServidor = numeroDeUsuarioPorServidor; 
		this.usuarios = usuarios;
	}

	@Override
	public List<Integer> pegaInformacoes() {
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(this.quantidadeQueUmUsuarioPermaneceNoServidor);
		entrada.add(this.numeroDeUsuarioPorServidor);
		entrada.addAll(this.usuarios);
		return entrada;
	}

}
