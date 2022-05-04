package pract09.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract09.modelo.*;


/**
 * Test de la clase Inmobiliaria.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-20
 */
class InmobiliariaTest {

	@Test
	void anhadeAgenteSimpleTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		Agente agente;

		// anhade 3 agentes
		inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));
		inmobiliaria.anhadeAgente(new Agente("A", "DNI2"));
		inmobiliaria.anhadeAgente(new Agente("B", "DNI3"));

		// comprueba que los encuentra
		agente = inmobiliaria.buscaAgente("DNI3");
		assertEquals("B", agente.nombre());
		assertEquals("DNI3", agente.dni());
		assertEquals(0, agente.numVentas());

		agente = inmobiliaria.buscaAgente("DNI2");
		assertEquals("A", agente.nombre());
		assertEquals("DNI2", agente.dni());	
		assertEquals(0, agente.numVentas());

		agente = inmobiliaria.buscaAgente("DNI1");
		assertEquals("A", agente.nombre());
		assertEquals("DNI1", agente.dni());
		assertEquals(0, agente.numVentas());	

		// comprueba que no encuentra los que no existen
		agente = inmobiliaria.buscaAgente("DNI4");
		assertNull(agente);
	}

	@Test
	void anhadePisoSimpleTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade un agente
		inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));

		// anhade 3 pisos
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));
		inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));
		inmobiliaria.anhadePiso(new Piso("REF3", 30.0, 300, 3));

		// comprueba que se pueden asignar los pisos que existen
		inmobiliaria.asignaPisoAgente("DNI1", "REF2");
		inmobiliaria.asignaPisoAgente("DNI1", "REF3");
		inmobiliaria.asignaPisoAgente("DNI1", "REF1");	

		// comprueba que no se pueden asignar los pisos que no existen
		try {
			inmobiliaria.asignaPisoAgente("DNI1", "REF0");
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		try {
			inmobiliaria.asignaPisoAgente("DNI1", "REF4");
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void nuevoPisoRefRepetidaTests() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		Piso piso = new Piso("REF1", 100000.0, 100, 3);

		// anhade un agente
		inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));

		// anhade el mismo piso 2 veces
		inmobiliaria.anhadePiso(piso);
		try {	
			inmobiliaria.anhadePiso(piso);
			fail("Permite anhadir dos veces el mismo piso");
		} catch (Inmobiliaria.ReferenciaPisoYaExistente e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// anhade un piso distinto
		inmobiliaria.anhadePiso(new Piso("REF2", 100000.0, 100, 3));

		// prueba que los pisos se pueden asignar al agente
		inmobiliaria.asignaPisoAgente("DNI1", "REF2");
		inmobiliaria.asignaPisoAgente("DNI1", "REF1");	
	}

	@Test
	void nuevoAgenteDniRepetidoTests() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade 3 agentes
		inmobiliaria.anhadeAgente(new Agente("A", "DNI10"));
		inmobiliaria.anhadeAgente(new Agente("A", "DNI20"));
		inmobiliaria.anhadeAgente(new Agente("B", "DNI30"));

		// comprueba que falla anhadir agentes con el mismo DNI
		try {
			inmobiliaria.anhadeAgente(new Agente("P", "DNI10"));
			fail("Permite anhadir dos agentes con el mismo  DNI");
		} catch (Inmobiliaria.DniAgenteYaExistente e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		try {
			inmobiliaria.anhadeAgente(new Agente("P", "DNI30"));
			fail("Permite anhadir dos agentes con el mismo  DNI");
		} catch (Inmobiliaria.DniAgenteYaExistente e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		try {
			inmobiliaria.anhadeAgente(new Agente("P", "DNI20"));
			fail("Permite anhadir dos agentes con el mismo  DNI");
		} catch (Inmobiliaria.DniAgenteYaExistente e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
	}

	@Test
	void asignaVendePisoSimpleTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade un agente
		Agente agenteAnhadido = new Agente("A", "DNI10");
		inmobiliaria.anhadeAgente(agenteAnhadido);

		// anhade un piso
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));

		// asigna piso al agente
		inmobiliaria.asignaPisoAgente("DNI10", "REF1");

		// comprueba que esta asignado
		Agente agente = inmobiliaria.vendePiso("REF1");;

		assertEquals(agenteAnhadido, agente);
		assertEquals(1, agente.numVentas());
	}


	@Test
	void vendePisoNoAsignadoErrorTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade un piso
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));

		// comprueba que no puede vender un piso no asignado
		try {
			inmobiliaria.vendePiso("REF1");	
		} catch (Inmobiliaria.PisoNoAsignado e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	void eliminaPisosVendidosTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade un agente
		inmobiliaria.anhadeAgente(new Agente("A", "DNI10"));

		// anhade 2 pisos
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));
		inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));

		// asigna y vende los pisos
		inmobiliaria.asignaPisoAgente("DNI10", "REF1");
		inmobiliaria.vendePiso("REF1");
		inmobiliaria.asignaPisoAgente("DNI10", "REF2");
		inmobiliaria.vendePiso("REF2");

		// comprueba que no puede asignar un piso vendido
		try {
			inmobiliaria.asignaPisoAgente("DNI10", "REF2");
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// comprueba que no puede vender un piso ya vendido
		try {
			inmobiliaria.vendePiso("REF1");	
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}		

	@Test
	void asignaPisoErroresTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// comprueba que falla asignar un agente y un piso que no existen
		try {
			inmobiliaria.asignaPisoAgente("DNI10", "REF1");
		} catch (Inmobiliaria.DniAgenteIncorrecto e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// anhade un agente
		inmobiliaria.anhadeAgente(new Agente("A", "DNI101"));

		// comprueba que falla asignar un piso no existente
		try {
			inmobiliaria.asignaPisoAgente("DNI101", "REF1");
		} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// anhade un piso
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));

		// comprueba que falla asignar a un DNI no existente
		try {
			inmobiliaria.asignaPisoAgente("DNI2", "REF1");
		} catch (Inmobiliaria.DniAgenteIncorrecto e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// asigna el piso
		inmobiliaria.asignaPisoAgente("DNI101", "REF1");

		// anhade otro agente
		inmobiliaria.anhadeAgente(new Agente("B", "DNI200"));

		// asigna un piso ya asignado
		try {
			inmobiliaria.asignaPisoAgente("DNI200", "REF1");
		} catch (Inmobiliaria.PisoYaAsignado e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
	}

	@Test
	void numVentasTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// anhade 3 agentes
		Agente agente1 = new Agente("A", "DNI1");
		Agente agente2 = new Agente("A", "DNI2");
		Agente agente3 = new Agente("B", "DNI3");
		inmobiliaria.anhadeAgente(agente1);
		inmobiliaria.anhadeAgente(agente2);
		inmobiliaria.anhadeAgente(agente3);

		// anhade 3 pisos
		inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));
		inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));
		inmobiliaria.anhadePiso(new Piso("REF3", 30.0, 300, 3));

		// asigna un piso a un agente y otro a otro
		inmobiliaria.asignaPisoAgente("DNI1", "REF2");
		inmobiliaria.asignaPisoAgente("DNI3", "REF1");

		// vende los pisos
		assertEquals(agente3, inmobiliaria.vendePiso("REF1"));
		assertEquals(agente1, inmobiliaria.vendePiso("REF2"));

		// comprueba el numero de ventas
		assertEquals(1, agente1.numVentas());
		assertEquals(0, agente2.numVentas());
		assertEquals(1, agente3.numVentas());

		// asigna y vende otro piso
		inmobiliaria.asignaPisoAgente("DNI3", "REF3");
		assertEquals(agente3, inmobiliaria.vendePiso("REF3"));

		// comprueba el numero de ventas
		assertEquals(1, agente1.numVentas());
		assertEquals(0, agente2.numVentas());
		assertEquals(2, agente3.numVentas());
	}

}
