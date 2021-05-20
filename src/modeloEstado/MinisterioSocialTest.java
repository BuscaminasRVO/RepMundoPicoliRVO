package modeloEstado;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.jupiter.api.Test;

import modeloSer.Ser;

class MinisterioSocialTest {

	@Test
	void testPagarMenores() {
		MinisterioSocial ministerio= new MinisterioSocial();
		ArrayList<Ser> menores = ministerio.getMenores();
		int menores2 = 5;
		
		for (int i = 0; i < menores2; i++) {
			Ser e;
			do {
				e = new Ser();
			} while (e.getEsperanzaVida() < 50);
			menores.add(e);
			long pago=500;
			assertEquals(pago, ministerio.pagarMenores());
			
		}
	}

}
