package br.com.servidor.infraestrutura;

import java.util.ArrayList;
import java.util.List;

import br.com.servidor.aplicacao.EntradaDeDados;

public class DadosManuais implements EntradaDeDados {

	@Override
	public List<Integer> pegaInformacoes() {
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(4);
		entrada.add(2);
		entrada.add(1);
		entrada.add(3);
		entrada.add(0);
		entrada.add(1);
		entrada.add(0);
		entrada.add(1);
		return entrada;
	}

}
