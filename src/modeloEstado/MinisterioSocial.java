
package modeloEstado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	
	private final ArrayList<Ser> menores = new ArrayList<>();
	private final ArrayList<Ser> ancianos = new ArrayList<>();
	private Collection<Ser> parados;
	private long ahorros=0;

	public MinisterioSocial(Collection<Ser> parados) {
		super();
		this.parados = parados;
	}

	public Observer adultoObserver = new Observer() {
		@Override
		public void update(Observable o, Object arg) {
			parados.add((menores.remove(menores.indexOf(arg))));

		}

	};
	public void añadirAnciano(Ser ser) {
		ancianos.add(ser);
	}
	public void añadirSer(Ser ser) {
		ser.addAdultoObserver(this.adultoObserver);
		menores.add(ser);
	}
	public void eliminarSer(Ser ser) {
		if (!menores.remove(ser)) {
			ancianos.remove(ser);
		}
	}
	
	
	public Observer ancianoObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
//			ancianos.add(parados.remove(parados.indexOf(arg)));
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
	
	public void alimentar(long cantidadMenores,long cantidadAncianos, long cantidadParados) {
		alimentarSectores(this.parados, cantidadParados);
		alimentarSectores(this.menores, cantidadParados);
		alimentarSectores(this.ancianos, cantidadParados);
	}
	
	
	public void alimentarSectores(Collection<Ser> parados,long cantidad){
		if (!parados.isEmpty()) {
			int sueldo=(int) (cantidad/parados.size());
			for (Ser ser : parados) {
				ser.alimentar(sueldo);
			}
		}
	}

	

}