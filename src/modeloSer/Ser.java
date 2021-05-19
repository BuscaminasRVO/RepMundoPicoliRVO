package modeloSer;

import java.util.Observer;

import utiles.Coordenada;
import utiles.Utiles;

public class Ser {
	private static final int vidaMax = 120;
	private static final int vidaMin = 0;
	private MyObservable aAdultos = new MyObservable();
	private MyObservable aAnciano = new MyObservable();
	private MyObservable aEstado = new MyObservable();
	protected float esperanzaVida;
	protected int edad;
	private Comportamiento comportamiento;

	public Ser() {
		super();
		esperanzaVida = calculaEsperanzaVida(vidaMin, vidaMax);
		comportamiento = new Menor();

		;
	}

	public boolean isAlive() {
		return this.edad <= this.esperanzaVida;
	}

	public boolean envejecer() {
		this.edad++;
		if (pasaAAdulto() && ((Menor) (comportamiento)).getFactorDesarrollo() >= 55) {
			comportamiento = new Adulto();
			this.aAdultos.notifica(comportamiento);
		}
		if (pasaAAnciano()) {
			// Una solucion para no tener clases sin propiedades
			// son los objetos anonimos
			

			aEstado.notifica(comportamiento);
			

			comportamiento = new Comportamiento() {
				float calculaPendienteUna = calculaPendiente(new Coordenada(.3f, 1), new Coordenada(1, 0));
				float calculaPendienteDos = calculaPendiente(new Coordenada(0, 2), new Coordenada(.3f, 1));

				@Override
				public float alimentar(int sueldo, float esperanzaVida) {
					return recalcularVejez(sueldo, esperanzaVida);
				}

				float recalcularVejez(int sueldo, float esperanzaVida) {

					float coeficiente = (float) sueldo / Edades.anciano.getNecesidadVital();
					if (coeficiente >= .3f) {
						return esperanzaVida -= calculaPendienteUna * coeficiente - calculaPendienteUna;
					}
					return esperanzaVida -= Math.round(calculaPendienteDos * coeficiente - (calculaPendienteDos + 1));
				}
			};

			this.aAnciano.notifica(this);
		}
		return isAlive();
	}

	public float calculaPendiente(Coordenada uno, Coordenada dos) {
		return (float) (uno.getPosY() - dos.getPosY()) / (uno.getPosX() - dos.getPosX());
	}

	public boolean vivir(int sueldo) {
		this.esperanzaVida = comportamiento.alimentar(sueldo, this.esperanzaVida);
		return envejecer();
	}

	public Ser(Ser ser) {
		this.edad = ser.edad;
		this.esperanzaVida = ser.esperanzaVida;
	}

	public float getEsperanzaVida() {
		return esperanzaVida;
	}

	public void setEsperanzaVida(float esperanzaVida) {
		this.esperanzaVida = esperanzaVida;
	}

	private int calculaEsperanzaVida(int minimo, int maximo) {
		return Utiles.dameNumero(maximo);
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	protected void recalcularEsperanzaDeVida(int sueldo) {
		// TODO recalculando
	}

	public boolean pasaAAnciano() {
		return edad == Edades.adulto.getEdadMaxima();
	}

	public boolean pasaAAdulto() {
		return edad == Edades.menor.getEdadMaxima();
	}

	public void addAdultoObserver(Observer obj) {
		aAdultos.addObserver(obj);

	}
	public void addEstadoObserver(Observer obj) {
		aEstado.addObserver(obj);

	}

	public void addAncianoObserver(Observer obj) {
		aAnciano.addObserver(obj);

	}
	public Comportamiento getComportamiento() {
		return comportamiento;
	}
}
