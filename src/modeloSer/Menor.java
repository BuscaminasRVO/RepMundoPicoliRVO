package modeloSer;

public class Menor implements Comportamiento{
	private float factorDesarrollo;

	// Esto solo los menores
	@Override
	public float alimentar(int sueldo,float esperanzaVida) {
		if (sueldo==Edades.menor.getNecesidadVital()) {
			factorDesarrollo+=5.55;
			
		}else if (sueldo>=0&&sueldo<Edades.menor.getNecesidadVital()) {
			float resultado=sueldo*factorDesarrollo/100f;
			factorDesarrollo+=resultado;
			
		}
		return esperanzaVida;
	}

	public float getFactorDesarrollo() {
		return factorDesarrollo;
	}



	
}
