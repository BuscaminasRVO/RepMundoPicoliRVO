
package modeloEstado;

import java.util.ArrayList;

import modeloPresupuesto.Sector;
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
	

	
	public ArrayList<Ser> getMenores() {
		return menores;
	}
	public ArrayList<Ser> getAncianos() {
		return ancianos;
	}
	public long pagarMenores() {
		long cantidad=0;
		for (Ser ser : menores) {
			cantidad+=estado.getPagoMenores();
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