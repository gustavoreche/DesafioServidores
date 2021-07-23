package br.com.servidor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.servidor.aplicacao.IniciaSistema;
import br.com.servidor.infraestrutura.DadosManuaisTest;
import br.com.servidor.infraestrutura.ExibeNoConsole;

public class MainTest {
	
	ExibeNoConsole exibeNoConsole;
	
	@Before
	public void init() {
		this.exibeNoConsole = new ExibeNoConsole();
	}
	
	@After
	public void finish() {
		System.out.println("---------------------------");
	}
	
	@Test
	public void testeDoExemplo_ok() {
		List<Integer> usuarios = new ArrayList<Integer>();
		usuarios.add(1);
		usuarios.add(3);
		usuarios.add(0);
		usuarios.add(1);
		usuarios.add(0);
		usuarios.add(1);
		DadosManuaisTest dadosManuaisTest = new DadosManuaisTest(4, 2, usuarios);
		new IniciaSistema(this.exibeNoConsole, dadosManuaisTest).executa();
		
		int i = 0;
		assertEquals("1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2,2", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2,2", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2,2,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1,2,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("0", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals(15, this.exibeNoConsole.retornaCusto());
	}
	
	@Test
	public void testandoOutrosValores_ok() {
		List<Integer> usuarios = new ArrayList<Integer>();
		usuarios.add(5);
		usuarios.add(1);
		usuarios.add(0);
		usuarios.add(0);
		usuarios.add(3);
		usuarios.add(2);
		DadosManuaisTest dadosManuaisTest = new DadosManuaisTest(2, 5, usuarios);
		new IniciaSistema(this.exibeNoConsole, dadosManuaisTest).executa();
		
		int i = 0;
		assertEquals("5", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("5,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("0", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("3", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("5", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("2", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("0", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals(7, this.exibeNoConsole.retornaCusto());
	}
	
	@Test
	public void testandoOutrosValores2_ok() {
		List<Integer> usuarios = new ArrayList<Integer>();
		usuarios.add(0);
		usuarios.add(3);
		usuarios.add(2);
		usuarios.add(1);
		usuarios.add(0);
		DadosManuaisTest dadosManuaisTest = new DadosManuaisTest(3, 1, usuarios);
		new IniciaSistema(this.exibeNoConsole, dadosManuaisTest).executa();
		
		int i = 0;
		assertEquals("0", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1,1,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1,1,1,1,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1,1,1,1,1,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1,1,1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("1", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals("0", this.exibeNoConsole.retornaHistorico().get(i++));
		assertEquals(18, this.exibeNoConsole.retornaCusto());
	}
	
}
