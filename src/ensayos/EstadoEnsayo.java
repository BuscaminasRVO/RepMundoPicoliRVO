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

	


	public EstadoEnsayo() {
		super();
		Ser ser = new Ser();
		addSer(ser);
		
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
	


}
