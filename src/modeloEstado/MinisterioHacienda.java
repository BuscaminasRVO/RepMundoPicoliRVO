package modeloEstado;


import modeloPresupuesto.Presupuesto;
import presentador.Estado;

/*
 * Elabora el presupuesto y decide sobre capital, deudas, etc
 */
public class MinisterioHacienda {
	
	Estado estado;
	private long capital=0;
	
	public void calcularCapital() {
		
		capital+= calculamosProduccionPeriodica();
		capital-= pagarCostesFabricacion();
		Presupuesto presupuesto = new Presupuesto(estado.getCantidadMenores(), estado.getCantidadAncianos(), estado.getCantidadTrabajadores(), estado.getParados());
		presupuesto.establecerPorcentajes(capital);
		capital-= presupuesto.getTotal();
	}
	
	
	private long pagarCostesFabricacion() {
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
