package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import modeloSer.Comportamiento;
import modeloSer.Edades;
import modeloSer.Ser;
import utiles.Coordenada;


class SerTest {

	@Ignore
	void testEnvejecer() {
		Ser ser = new Ser();
		System.out.println("esperanza de vida " + ser.getEsperanzaVida());
		for (int i = 0; ser.isAlive(); i++) {
			ser.vivir(1);
		}
	}

	@Test
	void testRecalculoVidaAnciano() {
		Comportamiento comportamiento = new Comportamiento() {
			float calculaPendienteUna = calculaPendiente(new Coordenada(.3f, 1), new Coordenada(1, 0));
			float calculaPendienteDos = calculaPendiente(new Coordenada(0, 2), new Coordenada(.3f, 1));

			// como la funcion es recta calcula la pendiente de la curva
			public float calculaPendiente(Coordenada uno, Coordenada dos) {
				return (float) (uno.getPosY() - dos.getPosY()) / (uno.getPosX() - dos.getPosX());
			}

			@Override
			public float alimentar(int sueldo, float esperanzaVida) {
				return recalcularVejez(sueldo, esperanzaVida);
			}

			float recalcularVejez(int sueldo, float esperanzaVida) {
				// TODO recalcular la esperanza de vida
				float coeficiente = (float) sueldo / Edades.anciano.getNecesidadVital();
				if (coeficiente >= .3f) {
					return esperanzaVida -= calculaPendienteUna * coeficiente - calculaPendienteUna;
				}
				return esperanzaVida -= Math.round(calculaPendienteDos * coeficiente - (calculaPendienteDos + 1));
			}
		};
		float esperanzaVida = 80f;
		int sueldo[] = { 50, 15, 0, 25 };
		float resultado[] = { 80f, 79f, 78, 79.2f };
		for (int i = 0; i < resultado.length; i++) {
			assertEquals(resultado[i], comportamiento.alimentar(sueldo[i], esperanzaVida),.1f);
		}
	}

}
