package pract07.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract07.modelo.*;
import pract07.modelo.Inmobiliaria.ResultadoError;


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
		ResultadoError error;

		// anhade 3 agentes
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI2"));
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(new Agente("B", "DNI3"));
		assertEquals(ResultadoError.NO_ERROR, error);

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
		ResultadoError error;

		// anhade un agente
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade 3 pisos
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(new Piso("REF3", 30.0, 300, 3));
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que se pueden asignar los pisos que existen
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF2");
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF3");
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que no se pueden asignar los pisos que no existen
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF0");
		assertEquals(ResultadoError.REFERENCIA_PISO_INCORRECTA, error);
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF4");
		assertEquals(ResultadoError.REFERENCIA_PISO_INCORRECTA, error);
	}

	@Test
	void nuevoPisoRefRepetidaTests() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		Piso piso = new Piso("REF1", 100000.0, 100, 3);
		ResultadoError error;

		// anhade un agente
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI1"));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade el mismo piso 2 veces
		error = inmobiliaria.anhadePiso(piso);	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(piso);
		assertEquals(ResultadoError.REFERENCIA_PISO_YA_EXISTENTE, error);

		// anhade un piso distinto
		error = inmobiliaria.anhadePiso(new Piso("REF2", 100000.0, 100, 3));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// prueba que los pisos se pueden asignar al agente
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF2");	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);	
	}

	@Test
	void nuevoAgenteDniRepetidoTests() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		ResultadoError error;

		// anhade 3 agentes
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI10"));	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI20"));	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(new Agente("B", "DNI30"));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que falla anhadir agentes con el mismo DNI
		error = inmobiliaria.anhadeAgente(new Agente("P", "DNI10"));	
		assertEquals(ResultadoError.DNI_AGENTE_YA_EXISTENTE, error);
		error = inmobiliaria.anhadeAgente(new Agente("P", "DNI30"));	
		assertEquals(ResultadoError.DNI_AGENTE_YA_EXISTENTE, error);
		error = inmobiliaria.anhadeAgente(new Agente("P", "DNI20"));	
		assertEquals(ResultadoError.DNI_AGENTE_YA_EXISTENTE, error);
	}

	@Test
	void asignaVendePisoSimpleTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		ResultadoError error;

		// anhade un agente
		Agente agenteAnhadido = new Agente("A", "DNI10");
		error = inmobiliaria.anhadeAgente(agenteAnhadido);	
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade un piso
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// asigna piso al agente
		error = inmobiliaria.asignaPisoAgente("DNI10", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que esta asignado
		Agente agente = inmobiliaria.vendePiso("REF1");;

		assertEquals(agenteAnhadido, agente);
		assertEquals(1, agente.numVentas());
	}


	@Test
	void vendePisoNoAsignadoErrorTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		Agente agente;
		ResultadoError error;

		// anhade un piso
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que no puede vender un piso no asignado
		agente = inmobiliaria.vendePiso("REF1");
		assertNull(agente);
	}

	@Test
	void eliminaPisosVendidosTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		ResultadoError error;
		Agente agente;

		// anhade un agente
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI10"));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade 2 pisos
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// asigna y vende los pisos
		error = inmobiliaria.asignaPisoAgente("DNI10", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);
		agente = inmobiliaria.vendePiso("REF1");
		assertNotNull(agente);
		error = inmobiliaria.asignaPisoAgente("DNI10", "REF2");	
		assertEquals(ResultadoError.NO_ERROR, error);
		agente = inmobiliaria.vendePiso("REF2");
		assertNotNull(agente);

		// comprueba que no puede asignar un piso vendido
		error = inmobiliaria.asignaPisoAgente("DNI10", "REF2");	
		assertEquals(ResultadoError.REFERENCIA_PISO_INCORRECTA, error);

		// comprueba que no puede vender un piso ya vendido
		agente = inmobiliaria.vendePiso("REF1");
		assertNull(agente);
	}		

	@Test
	void asignaPisoErroresTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		ResultadoError error;

		// comprueba que falla asignar un agente y un piso que no existen
		error = inmobiliaria.asignaPisoAgente("DNI10", "REF1");
		assertTrue(error == ResultadoError.DNI_AGENTE_INCORRECTO ||
				error == ResultadoError.REFERENCIA_PISO_INCORRECTA);

		// anhade un agente
		error = inmobiliaria.anhadeAgente(new Agente("A", "DNI101"));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que falla asignar un piso no existente
		error = inmobiliaria.asignaPisoAgente("DNI101", "REF1");	
		assertEquals(ResultadoError.REFERENCIA_PISO_INCORRECTA, error);

		// anhade un piso
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// comprueba que falla asignar a un DNI no existente
		error = inmobiliaria.asignaPisoAgente("DNI2", "REF1");	
		assertEquals(ResultadoError.DNI_AGENTE_INCORRECTO, error);

		// asigna el piso
		error = inmobiliaria.asignaPisoAgente("DNI101", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade otro agente
		error = inmobiliaria.anhadeAgente(new Agente("B", "DNI200"));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// asigna un piso ya asignado
		error = inmobiliaria.asignaPisoAgente("DNI200", "REF1");	
		assertEquals(ResultadoError.PISO_YA_ASIGNADO, error);
	}

	@Test
	void numVentasTest() {
		Inmobiliaria inmobiliaria = new Inmobiliaria();
		ResultadoError error;

		// anhade 3 agentes
		Agente agente1 = new Agente("A", "DNI1");
		Agente agente2 = new Agente("A", "DNI2");
		Agente agente3 = new Agente("B", "DNI3");
		error = inmobiliaria.anhadeAgente(agente1);	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(agente2);	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadeAgente(agente3);	
		assertEquals(ResultadoError.NO_ERROR, error);

		// anhade 3 pisos
		error = inmobiliaria.anhadePiso(new Piso("REF1", 10.0, 100, 1));	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(new Piso("REF2", 20.0, 200, 2));	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.anhadePiso(new Piso("REF3", 30.0, 300, 3));	
		assertEquals(ResultadoError.NO_ERROR, error);

		// asigna un piso a un agente y otro a otro
		error = inmobiliaria.asignaPisoAgente("DNI1", "REF2");	
		assertEquals(ResultadoError.NO_ERROR, error);
		error = inmobiliaria.asignaPisoAgente("DNI3", "REF1");	
		assertEquals(ResultadoError.NO_ERROR, error);

		// vende los pisos
		assertEquals(agente3, inmobiliaria.vendePiso("REF1"));
		assertEquals(agente1, inmobiliaria.vendePiso("REF2"));

		// comprueba el numero de ventas
		assertEquals(1, agente1.numVentas());
		assertEquals(0, agente2.numVentas());
		assertEquals(1, agente3.numVentas());

		// asigna y vende otro piso
		error = inmobiliaria.asignaPisoAgente("DNI3", "REF3");	
		assertEquals(ResultadoError.NO_ERROR, error);
		assertEquals(agente3, inmobiliaria.vendePiso("REF3"));

		// comprueba el numero de ventas
		assertEquals(1, agente1.numVentas());
		assertEquals(0, agente2.numVentas());
		assertEquals(2, agente3.numVentas());
	}

}
