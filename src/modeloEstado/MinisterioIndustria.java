package modeloEstado;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import modeloSer.Adulto;
import modeloSer.Comportamiento;
import modeloSer.Ser;

/*
 * Se encarga de los trabajadores y parados
 * porq se encarga de contratar y despedir 
 */
public class MinisterioIndustria {
	private  Stack<Ser> trabajadores;
	private  ArrayDeque<Ser> parados;
	
	
	
	public MinisterioIndustria() {
		super();
		this.trabajadores = new Stack<Ser>();
		this.parados = new ArrayDeque<Ser>();
	}
	

	public MinisterioIndustria(Stack<Ser> trabajadores, ArrayDeque<Ser> parados) {
		super();
		this.trabajadores = trabajadores;
		this.parados = parados;
	}

	public void contratar(long trabajadoresNecesarios) {

		while (parados.size() >= trabajadoresNecesarios) {
			for (int i = 0; i < trabajadoresNecesarios; i++) {
				trabajadores.push(parados.pop());
			}
		}
	}
	
	public void despedir(long despidosNecesarios) {

		while (trabajadores.size() >= despidosNecesarios) {
			for (int i = 0; i < despidosNecesarios; i++) {
				parados.offerLast(trabajadores.pop());
			}
		}
	}

	public Stack<Ser> getTrabajadores() {
		return trabajadores;
	}

	public ArrayList<Adulto> getParadosAdultos() {
		ArrayList<Adulto> Parados = new ArrayList<>();
		for (Iterator iterator = parados.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			Comportamiento comportamiento = ser.getComportamiento();
			// long ahorros = ((Adulto) comportamiento).getAhorro();
			Parados.add(((Adulto) comportamiento));

		}
		return Parados;
	}

	public ArrayDeque<Ser> getParados() {
		return parados;
	}

	public void sacarAdulto(Ser ser) {
		if (parados.contains(ser)) {
			parados.remove(ser);
		}else if(trabajadores.contains(ser)){
			trabajadores.remove(ser);
		}
		
	}
	
	
}
