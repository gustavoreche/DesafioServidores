package br.com.servidor.aplicacao;

import java.util.List;
import java.util.stream.Collectors;

import br.com.servidor.modelo.Usuario;

public class UsuariosExpirados {
	
	private int quantidadeQueUmUsuarioPermaneceNoServidor;
	
	public UsuariosExpirados(int quantidadeQueUmUsuarioPermaneceNoServidor) {
		this.quantidadeQueUmUsuarioPermaneceNoServidor = quantidadeQueUmUsuarioPermaneceNoServidor;
	}
	

	public void remove(List<Usuario> usuarios, List<Integer> servidoresDisponiveis) {
		List<Usuario> usuarioComTempoDeServidorExpirado = pegaUsuariosExpirados(usuarios);
		while(!usuarioComTempoDeServidorExpirado.isEmpty()) {
			Usuario usuarioExpirado = usuarioComTempoDeServidorExpirado.get(0);	
			usuarioComTempoDeServidorExpirado.remove(0);
			servidoresDisponiveis.add(usuarioExpirado.getIdServidor());
			usuarios.remove(usuarioExpirado);
		}		
	}

	private List<Usuario> pegaUsuariosExpirados(List<Usuario> usuarios) {
		return usuarios.stream()
				.filter(usuario -> usuario.getTempoQueEstaNoServidor() >= this.quantidadeQueUmUsuarioPermaneceNoServidor)
				.collect(Collectors.toList());
	}
	
}
