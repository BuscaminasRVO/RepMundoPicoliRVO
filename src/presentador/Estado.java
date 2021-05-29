package presentador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import modeloEstado.MinisterioHacienda;
import modeloEstado.MinisterioIndustria;
import modeloEstado.MinisterioSocial;
import modeloPresupuesto.Presupuesto;
import modeloSer.Adulto;
import modeloSer.Ser;

public class Estado {
	
	private long demanda=0;
	private long ahorros=0;
	private int potenciaTrabajador = 450;
	private long produccionPotencial= 1790;
	
	private final ArrayList<Ser> seres = new ArrayList<>();
	
	public MinisterioHacienda ministerioHacienda;
	public MinisterioIndustria ministerioIndustria;
	public MinisterioSocial ministerioSocial;
	
	
	


	public Estado() {
		super();
		this.ministerioHacienda = new MinisterioHacienda(this, 0);
		this.ministerioSocial = new MinisterioSocial();
		this.ministerioIndustria = new MinisterioIndustria();
	}


	public Estado(long demanda) {
		super();
		this.demanda= demanda;
		this.ministerioHacienda = new MinisterioHacienda(this, 0);
		this.ministerioSocial = new MinisterioSocial();
		this.ministerioIndustria = new MinisterioIndustria();
		
		for (int i = 0; i < demanda/potenciaTrabajador; i++) {
			naceSer();
			
		}
		
		//Esto es la historia
		int historia=0;
		
		do{
			
			terminarPeriodo();
			comenzarPeriodo();
			
			historia++;
		}while(historia <1);
	}

	private void comenzarPeriodo() {
		long trabajadoresNecesarios= demanda - produccionPotencial;
		contratar(trabajadoresNecesarios);
		long trabajadoresFaltantes= trabajadoresNecesarios-getCantidadTrabajadores();
		establecerNacimiento(trabajadoresFaltantes);
	}

	private void establecerNacimiento(long trabajadoresFaltantes) {
		for (int i = 0; i < trabajadoresFaltantes; i++) {
			seres.add(new Ser());
		}
	}
	

	private void contratar(long trabajadoresNecesarios) {
		ministerioIndustria.contratar(trabajadoresNecesarios);
	}


	private void terminarPeriodo() {
		
		Presupuesto presupuesto = ministerioHacienda.nuevoPresupuesto();
		ministerioHacienda.calcularCapital();
		long fabricado = ministerioIndustria.getTrabajadores().size();
		long presupuestoTotal = (fabricado + getAhorros());
		alimentar(presupuesto);
		ahorros +=  fabricado - presupuestoTotal;
		envejecer();
		
	}

	public void alimentar(Presupuesto presupuesto) {
		ministerioSocial.alimentar(ministerioSocial.getMenores().size(), ministerioSocial.getAncianos().size(), ministerioSocial.getParados().size());
	}
	private void envejecer() {
		for (Iterator iterator = seres.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			ser.envejecer();
		}
	}

	public int getPotenciaTrabajador() {
		return potenciaTrabajador;
	}

	private void naceSer() {
		Ser ser=new Ser();
		seres.add(ser);
		ministerioSocial.añadirSer(ser);
		ser.addAncianoObserver(ancianoObserver);
		ser.addEstadoObserver(estadoObserver);
	}
	
	public Observer estadoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {

			setAhorros(((Adulto) arg).getAhorro());
		}
	};
	
	public Observer ancianoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {

			Ser ser = (Ser) arg;
			ministerioSocial.añadirAnciano(ser);
			ministerioIndustria.sacarAdulto(ser);
		}
	};
	
	public long getAhorros() {
		return ahorros;
	}

	public void setAhorros(long ahorros) {
		this.ahorros += ahorros;
	}
	
	public long getCantidadMenores() {
		return ministerioSocial.getMenores().size();
	}
	public long getCantidadAncianos() {
		return ministerioSocial.getAncianos().size();
	}
	
	public long getCantidadTrabajadores() {
		return ministerioIndustria.getTrabajadores().size();
	}

	public ArrayList<Adulto> getParados() {
		return ministerioIndustria.getParadosAdultos();
	}
	
//	public long getPagoMenores() {
//		return this.presupuesto.getPagoMenores();
//	}
//
//
//	public long getPagoAncianos() {
//		return this.presupuesto.getPagoAncianos();
//		
//	}
//
//
//	public long getPagoParados() {
//		return this.presupuesto.getTotalParados(getParados());
//		
//	}
	
//	public ArrayList<Ser> getParadosSer() {
//		ArrayList<Ser> parados = new ArrayList<Ser>();
//		for (Ser ser : ministerioIndustria.getParados()) {
//			parados.add(ser);
//		}
//		return parados;
//	}


	public ArrayList<Ser> getSeres() {
		return seres;
	}
	
	
	
}

