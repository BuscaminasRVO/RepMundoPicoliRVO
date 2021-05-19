package modeloSer;

public class Adulto implements Comportamiento {
	private long ahorro;


	@Override
	public float alimentar(int sueldo, float esperanzaVida) {
		int necesidadVitalSatisfecha = 0;
		int resto = sueldo - Edades.adulto.getNecesidadVital();
		// resto sera -20
		this.ahorro += resto;
		// ahorros -10
		if (ahorro < 0) {
			necesidadVitalSatisfecha = (int) (Edades.adulto.getNecesidadVital() - ahorro);
			this.ahorro = 0;
		}

		return recalcularEsperanzaDeVida(necesidadVitalSatisfecha, esperanzaVida);
	}

	private float recalcularEsperanzaDeVida(int necesidadVitalSatisfecha, float esperanzaVida) {
		if (necesidadVitalSatisfecha <= 99 && necesidadVitalSatisfecha >= 0) {
			float resultado = 1 - necesidadVitalSatisfecha / 100f;

			esperanzaVida -= resultado;
			
		}
		return esperanzaVida;
	}

	public void setAhorro(long ahorro) {
		this.ahorro = ahorro;
	}

	public long getAhorro() {
		return ahorro;
	}

	public long calcularNecesidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
