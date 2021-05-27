
package modeloEstado;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import modeloPresupuesto.Sector;
import modeloSer.Adulto;
import modeloSer.MyObservable;
import modeloSer.Ser;
import presentador.Estado;

/*
 * Se encarga de gestionar menores, ancianos y parados
 * Tambien se encarga del pago de estos sectores
 */
public class MinisterioSocial {
	Estado estado;
	private final ArrayList<Ser> menores = new ArrayList<>();
	private final ArrayList<Ser> ancianos = new ArrayList<>();
	private ArrayList<Ser> parados;
	private long ahorros=0;

	public MinisterioSocial(ArrayList<Ser> parados) {
		super();
		this.parados = estado.getParadosSer();
	}

	public Observer adultoObserver = new Observer() {
		@Override
		public void update(Observable o, Object arg) {
			parados.add((menores.remove(menores.indexOf(arg))));

		}

	};

	public Observer ancianoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
			ancianos.add(parados.remove(parados.indexOf(arg)));
		}
	};
	public Observer estadoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {

			setAhorros(((Adulto) arg).getAhorro());
		}
	};
	
	public long getAhorros() {
		return ahorros;
	}

	public void setAhorros(long ahorros) {
		this.ahorros += ahorros;
	}
	
	public ArrayList<Ser> getMenores() {
		return menores;
	}


	public ArrayList<Ser> getAncianos() {
		return ancianos;
	}

	public long pagarMenores() {
		long cantidad = 0;
		for (Ser ser : menores) {
			cantidad += estado.getPagoMenores();
		}
		return cantidad;
	}

	public void pagarAncianos() {
		for (Ser ser : ancianos) {
			estado.getPagoAncianos();
		}
	}

	public void pagarParados() {
		for (int i = 0; i < estado.getParados().size(); i++) {
			estado.getParados();
		}
	}

}