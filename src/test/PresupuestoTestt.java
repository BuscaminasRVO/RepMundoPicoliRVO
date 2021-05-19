package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloPresupuesto.Presupuesto;
import modeloSer.Adulto;

class PresupuestoTestt {
	
	ArrayList<Adulto> adultos;
	
	@BeforeEach
	public void bedore() {
		adultos = new ArrayList<>();
		adultos.add(new Adulto());
		adultos.add(new Adulto());
		adultos.add(new Adulto());
		adultos.get(0).alimentar(0, 120);
		adultos.get(1).alimentar(0, 120);
		adultos.get(2).alimentar(0, 120);
	}
	
	@Test
	void testPresupuesto() {
		Presupuesto presupuesto = new Presupuesto(10,10,10,adultos);
		long menores = 500, ancianos = 150, trabajadores = 1000,parados = 300;
		int cantidad = 1850;
		presupuesto.establecerPorcentajes(cantidad);
		assertEquals(presupuesto.getPagoMenores(), menores);
		assertEquals(presupuesto.getPagoAncianos(), ancianos);
		assertEquals(presupuesto.getPagoTrabajadores(), trabajadores);
		assertEquals(presupuesto.getPagoParados(), parados);
		assertEquals(-150, presupuesto.calculaDeficit(cantidad));
		adultos.get(0).alimentar(150, 120);
		adultos.get(1).alimentar(175, 120);
		adultos.get(2).alimentar(125, 120);
		presupuesto = new Presupuesto(10, 10, 10, adultos);
		cantidad = 1000;
		parados = 150;
		presupuesto.establecerPorcentajes(cantidad);
		assertEquals(presupuesto.getPagoMenores(), menores);
		assertEquals(presupuesto.getPagoAncianos(), ancianos);
		assertEquals(presupuesto.getPagoTrabajadores(), trabajadores);
		assertEquals(presupuesto.getPagoParados(), parados);
		assertEquals(-850, presupuesto.calculaDeficit(cantidad));
		
	}

}
