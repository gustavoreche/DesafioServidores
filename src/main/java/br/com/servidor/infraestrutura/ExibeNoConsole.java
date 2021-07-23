package br.com.servidor.infraestrutura;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import br.com.servidor.aplicacao.ExibeInformacaoServidor;
import br.com.servidor.modelo.Usuario;

public class ExibeNoConsole implements ExibeInformacaoServidor {
	
	private AtomicInteger servidoresUtilizados;
	private int valorPorServidor;
	private long custo;
	private List<String> historicoServidor = new ArrayList<String>();
	
	public ExibeNoConsole() {
		this.servidoresUtilizados = new AtomicInteger();
		this.valorPorServidor = 1;
	}
	
	@Override
	public void exibeServidoresPopulados(List<Usuario> usuarios) {
		if(!usuarios.isEmpty()) {
			StringBuffer textoExibido = new StringBuffer();
			Map<Integer, List<Usuario>> servidoresAgrupados = usuarios.stream()
					.collect(Collectors.groupingBy(usuario -> usuario.getIdServidor()));
			servidoresAgrupados.forEach((id, idServidor) -> {
				servidoresUtilizados.set(servidoresUtilizados.get() + 1);
				textoExibido.append(idServidor.size() + ",");				
			});
			String servidorAtual = textoExibido.toString().substring(0, textoExibido.length() - 1);
			this.historicoServidor.add(servidorAtual);
			System.out.println(servidorAtual);
		} else {
			this.historicoServidor.add("0");
			System.out.println(0);
		}
	}
	
	@Override
	public void exibeCusto() {
		this.custo = this.valorPorServidor * servidoresUtilizados.get();
		System.out.println(this.valorPorServidor * servidoresUtilizados.get());
	}

	@Override
	public List<String> retornaHistorico() {
		return this.historicoServidor;
	}

	@Override
	public long retornaCusto() {
		return this.custo;
	}

}
