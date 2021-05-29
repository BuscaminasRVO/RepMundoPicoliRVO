package modeloEstado;


import java.util.ArrayList;
import modeloSer.Adulto;

import modeloPresupuesto.Presupuesto;
import presentador.Estado;

/*
 * Elabora el presupuesto y decide sobre capital, deudas, etc
 */
public class MinisterioHacienda {
	
	private Estado estado;
	private long capital=0;
	private Presupuesto presupuesto;
	
	
	
	public MinisterioHacienda() {
		super();
	}
	
	public MinisterioHacienda(Estado estado, long capital) {
		super();
		this.estado = estado;
		this.capital = capital;
		this.presupuesto = new Presupuesto(0,0,0, new ArrayList<Adulto>());
	}
	
	public void calcularCapital() {
		
		capital+= calculamosProduccionPeriodica();
		capital-= pagarCostesFabricacion();
		presupuesto.establecerPorcentajes(capital);
		capital-= presupuesto.getTotal();
	}
	public Presupuesto nuevoPresupuesto() {
		return presupuesto;
	}
	
	public long pagarCostesFabricacion() {
		long costesFabricacion =0;
		return costesFabricacion;
	}


	private long calculamosProduccionPeriodica() {
		long produccionAnual=0;
		for (int i = 0; i < estado.getCantidadTrabajadores(); i++) {
			produccionAnual+= estado.getPotenciaTrabajador();
		}
		return produccionAnual;
	}

}
