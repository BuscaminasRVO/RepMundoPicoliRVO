package ensayos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import modeloSer.Adulto;
import modeloSer.Ser;

public class EstadoEnsayo {
	public ArrayList<Ser> seres = new ArrayList<>();
	public ArrayList<Ser> menores = new ArrayList<>();
	public ArrayList<Ser> adultos = new ArrayList<>();
	public ArrayList<Ser> ancianos = new ArrayList<>();
	public ArrayDeque<Ser> parados = new ArrayDeque<>();
	public LinkedList<Ser> trabajadores= new LinkedList<>();
	private long ahorros=0;
	

	

	private Observer adultoObserver = new Observer() {
		@Override
		public void update(Observable o, Object arg) {
			adultos.add(menores.remove(menores.indexOf(arg)));
			
		}

	};
	
	
	private Observer ancianoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
			ancianos.add(adultos.remove(adultos.indexOf(arg)));
		}
	};
	private Observer estadoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
			
			setAhorros(((Adulto)arg).getAhorro());
		}
	};

	public EstadoEnsayo() {
		super();
		Ser ser = new Ser();
		addSer(ser);
		ser.addAdultoObserver(adultoObserver);
		ser.addAncianoObserver(ancianoObserver);
		ser.addEstadoObserver(estadoObserver);
		
		
	}

	private void addSer(Ser ser) {
		menores.add(ser);
		seres.add(ser);
	}

	public void envejecer() {
		for (Iterator iterator = seres.iterator(); iterator.hasNext();) {
			Ser ser = (Ser) iterator.next();
			ser.envejecer();
		}
		
		
	}
	
	public long getAhorros() {
		return ahorros;
	}

	public void setAhorros(long ahorros) {
		this.ahorros += ahorros;
	}
	

}
