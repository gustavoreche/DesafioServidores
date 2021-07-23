package br.com.servidor.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.servidor.modelo.Usuario;

public class LogicaDoSistema {
	
	private ExibeInformacaoServidor exibeServidores;
	private EntradaDeDados entradaDeDados;
	
	public LogicaDoSistema(ExibeInformacaoServidor exibeServidores, EntradaDeDados entradaDeDados) {
		this.exibeServidores = exibeServidores;
		this.entradaDeDados = entradaDeDados; 
	}
	
	public void executa() {
		List<Integer> dadosParaExecutar = this.entradaDeDados.pegaInformacoes();
		int quantidadeQueUmUsuarioPermaneceNoServidor = pegaPrimeiroValorDaEntrada(dadosParaExecutar);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		entrandoClientesNoServidor(dadosParaExecutar, 
				quantidadeQueUmUsuarioPermaneceNoServidor, usuarios);
		
		AtomicBoolean removeUsuariosDoServidor = new AtomicBoolean(true);
		while(removeUsuariosDoServidor.get()) {
			for (int i = 0; i < usuarios.size(); i++) {
				if(usuarios.get(i).getTempoQueEstaNoServidor() >= quantidadeQueUmUsuarioPermaneceNoServidor) {
					usuarios.remove(usuarios.get(i));
					i--;
					continue;
				}
				usuarios.get(i).setTempoQueEstaNoServidor();
			}
			removeUsuariosDoServidor.set(usuarios.size() > 0);
			this.exibeServidores.exibeServidoresPopulados(usuarios);
		}
		
		this.exibeServidores.exibeCusto();
		
	}

	private void entrandoClientesNoServidor(List<Integer> entradaDoUsuario, int quantidadeQueUmUsuarioPermaneceNoServidor, List<Usuario> usuarios) {
		int numeroDeUsuariosPorServidor = pegaPrimeiroValorDaEntrada(entradaDoUsuario);
		AtomicInteger idServidor = new AtomicInteger();
		AtomicInteger contadorDeInsercaoNoServidor = new AtomicInteger();
		List<Integer> servidoresDisponiveis = new ArrayList<Integer>();
		UsuariosExpirados usuariosExpirados = new UsuariosExpirados(quantidadeQueUmUsuarioPermaneceNoServidor);
		for (Integer usuarioNovo : entradaDoUsuario) {
			
			usuariosExpirados.remove(usuarios, servidoresDisponiveis);
			
			defineIdDoServidorParaOUsuario(numeroDeUsuariosPorServidor, usuarios, idServidor, contadorDeInsercaoNoServidor,
					servidoresDisponiveis, usuarioNovo);
			
			this.exibeServidores.exibeServidoresPopulados(usuarios);
			
			usuarios.forEach(usuario -> {
				usuario.setTempoQueEstaNoServidor();
			});
		}
	}

	private int pegaPrimeiroValorDaEntrada(List<Integer> entradaDoUsuario) {
		int valor = entradaDoUsuario.get(0);
		entradaDoUsuario.remove(0);
		return valor;
	}
	
	private void defineIdDoServidorParaOUsuario(int numeroDeUsuariosPorServidor, List<Usuario> usuarios, AtomicInteger idServidor,
			AtomicInteger contadorDeInsercaoNoServidor, List<Integer> servidoresDisponiveis, Integer usuarioNovo) {
		for (int i = 0; i < usuarioNovo; i++) {
			int idDoServidor = 0;
			if(contadorDeInsercaoNoServidor.get() >= numeroDeUsuariosPorServidor) {
				idServidor.set(idServidor.get() + 1);
				contadorDeInsercaoNoServidor.set(0);
			}
			idDoServidor = idServidor.get();
			contadorDeInsercaoNoServidor.set(contadorDeInsercaoNoServidor.get() + 1);
			usuarios.add(new Usuario(idDoServidor));									
		}
		if(usuarios.isEmpty()) {
			contadorDeInsercaoNoServidor.set(0);
		}
	}

}
