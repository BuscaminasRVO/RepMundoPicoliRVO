package presentador;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class EstadoTest {

	@Test
	void testEstado() {
		Estado estado = new Estado(1800);
		int esperadoSeres = 14;
		
		assertEquals(esperadoSeres, estado.getSeres().size());
	}
}
