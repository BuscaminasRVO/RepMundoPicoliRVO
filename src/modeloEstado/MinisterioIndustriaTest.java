package modeloEstado;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import modeloSer.Ser;



class MinisterioIndustriaTest {
	

	@Test
	void testContratar() {
		MinisterioIndustria ministerio= new MinisterioIndustria();
		ArrayDeque<Ser> parados = ministerio.getParados();
		Stack<Ser> trabajadores = ministerio.getTrabajadores();
		int parados2 = 5;
		
		for (int i = 0; i < parados2; i++) {
			Ser e;
			do {
				e = new Ser();
			} while (e.getEsperanzaVida() < 50);
			parados.push(e);
		}
		
		int edad = 18;
		for (int i = 0; i < edad; i++) {
			for (Ser ser : parados) {
				ser.envejecer();
			}
		}
		
		System.out.println(ministerio.getParados());
		ministerio.contratar(5);
		
		System.out.println(ministerio.getTrabajadores());
		int resultado=5;
		assertEquals(resultado, trabajadores.size());
		
		
	}
	@Test
	void testDespedir() {
		MinisterioIndustria ministerio= new MinisterioIndustria();
		ArrayDeque<Ser> parados = ministerio.getParados();
		Stack<Ser> trabajadores = ministerio.getTrabajadores();
		int trabajadores2 = 5;
		
		for (int i = 0; i < trabajadores2; i++) {
			Ser e;
			do {
				e = new Ser();
			} while (e.getEsperanzaVida() < 50);
			trabajadores.push(e);
		}
		
		int edad = 18;
		for (int i = 0; i < edad; i++) {
			for (Ser ser : trabajadores) {
				ser.envejecer();
			}
		}
		int resultado=5;
		System.out.println(trabajadores.size());
//		ministerio.despedir(5);
		System.out.println(parados.size());
		
	}
	

}
