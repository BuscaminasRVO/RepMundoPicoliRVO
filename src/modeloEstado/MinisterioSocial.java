
package modeloEstado;

import java.util.ArrayList;

import modeloSer.Ser;

/*
 * Se encarga de gestionar menores, ancianos y parados
 * Tambien se encarga del pago de estos sectores
 */
public class MinisterioSocial {
	
	private final ArrayList<Ser> menores = new ArrayList<>();
	private final ArrayList<Ser> ancianos = new ArrayList<>();
	
	
	public ArrayList<Ser> getMenores() {
		return menores;
	}
	public ArrayList<Ser> getAncianos() {
		return ancianos;
	}
	
	
}