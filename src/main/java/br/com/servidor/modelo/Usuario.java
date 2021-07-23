package br.com.servidor.modelo;

public class Usuario {
	
	private int tempoQueEstaNoServidor;
	private int idServidor;

	public Usuario(int idServidor) {
		this.tempoQueEstaNoServidor = 0;
		this.idServidor = idServidor;
	}
	
	public int getTempoQueEstaNoServidor() {
		return tempoQueEstaNoServidor;
	}
	
	public int getIdServidor() {
		return idServidor;
	}
	
	public void setTempoQueEstaNoServidor() {
		this.tempoQueEstaNoServidor++;
	}

}
