package modeloPresupuesto;

import java.util.ArrayList;

import modeloSer.Adulto;
import modeloSer.Edades;

public class Presupuesto {
	
	private Sector menores, ancianos, trabajadores;
	private ArrayList <Sector> sectores = new ArrayList<Sector>();
	private long parados;
	
	public Presupuesto(long cantidadMenores,long cantidadAncianos,long cantidadTrabajadores, ArrayList<Adulto> parados) {
		sectores.add(this.menores = new Sector(.55f, 1f, cantidadMenores, Edades.menor));
		sectores.add(this.ancianos = new Sector(.3f, 1f, cantidadAncianos, Edades.anciano));
		sectores.add(this.trabajadores = new Sector(1f, 2f, cantidadTrabajadores, Edades.adulto));
		this.parados = getTotalParados(parados);
	}
	
	public long calculaDeficit(long cantidad) {
		return cantidad - getTotal();
	}

	public long getTotal() {
		long total= 0;
		for (Sector sector : sectores) {
			total=+ sector.getCantidadCorregida();
		}
		total+=this.parados;
		return total;
	}

	private long getTotalParados(ArrayList<Adulto> parados) {
		long total= 0;
		for (Adulto adulto : parados) {
			total+= adulto.calcularNecesidad();
		}
		return 0;
	}

	public void establecerPorcentajes(long cantidad) {
		for (Sector sector : sectores) {
			long diferencia = getTotal() - cantidad;
			sector.setPorcentajeReal(calculaCantidad(diferencia,sector.getCantidadCorregida(), sector.getCantidadMinima()));
		}
		
	}

	private long calculaCantidad(long diferencia, long cantidadSector, long cantidadMinima) {
		long maximaRebaja= cantidadSector - cantidadMinima;
		if(maximaRebaja>=diferencia) {
			return cantidadSector-diferencia;
		}else {
			return cantidadMinima;
		}
	}
//	private float calculaPorcentaje(long cantidad,long diferencia) {
//		float abs = Math.abs((float)diferencia/cantidad);
//		return abs;
//	}


	public Long getPagoMenores() {
		return (long)(menores.getCantidadCorregida());
	}

	public Long getPagoAncianos() {
		return (long)(ancianos.getCantidadCorregida());
	}

	public Long getPagoTrabajadores() {
		return (long)(trabajadores.getCantidadCorregida());
	}

	public Long getPagoParados() {
		return parados;
	}
	public void setMenores(long i) {
		menores.setCantidad(i);
	}
	public void setAncianos(long i) {
		ancianos.setCantidad(i);
	}
	public void setTrabajadores(long i) {
		trabajadores.setCantidad(i);
	}

	public long getPagoTotal() {
		return getPagoMenores()+getPagoParados()+getPagoTrabajadores()+ getPagoAncianos();
	}
}
