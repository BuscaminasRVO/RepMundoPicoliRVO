package presentador;

import java.util.ArrayList;
import java.util.Iterator;

import modeloEstado.MinisterioHacienda;
import modeloEstado.MinisterioIndustria;
import modeloEstado.MinisterioSocial;
import modeloPresupuesto.Presupuesto;
import modeloSer.Adulto;
import modeloSer.Ser;

public class Estado {
	
	private long demanda= 0;
	private float porcentajeAumento;
	private long produccionPotencial=0;
	private int potenciaTrabajador = 450;
	private long produccion;
	
	
	MinisterioHacienda ministerioHacienda;
	MinisterioIndustria ministerioIndustria;
	MinisterioSocial ministerioSocial;
	Presupuesto presupuesto;
	
	private final ArrayList<Ser> seres = new ArrayList<>();
	

	public Estado() {
		for (int i = 0; i < demanda/potenciaTrabajador; i++) {
			naceSer();
		}
		//Esto es la historia
		int historia=0;
		
		do{
			terminarPeriodo();
			comenzarPeriodo();
			historia++;
		}while(historia <120);
	}
	

	private void comenzarPeriodo() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		ministerioHacienda.calcularCapital();
		envejecer();
		
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
		seres.add(new Ser());
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
	
	public long getPagoMenores() {
		return this.presupuesto.getPagoMenores();
	}


	public long getPagoAncianos() {
		return this.presupuesto.getPagoAncianos();
		
	}


	public long getPagoParados() {
		return this.presupuesto.getTotalParados(getParados());
		
	}
	
	
	
	
	
}

