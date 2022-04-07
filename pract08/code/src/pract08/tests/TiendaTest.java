package pract08.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import fundamentos_test.*;
import fundamentos_test.test.pipes.TestPipes;
import pract08.gui.GUIGestionComisiones;

/**
 * Test de la clase Tienda.
 * Usa "Fundamentos test".
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-22
 */
class TiendaTest {
	// datos globales para los tests
	private static String[] nombres = {"Pepa", "Lolo", "John"};
	private static final double FACTOR_COMISION = 0.05;
	
	// opciones del menu
	private static final int NUEVO_VENDEDOR = GUIGestionComisiones.NUEVO_VENDEDOR;
	private static final int NUEVA_VENTA = GUIGestionComisiones.NUEVA_VENTA;
	private static final int DATOS_VENDEDOR = GUIGestionComisiones.DATOS_VENDEDOR;
	private static final int RANKING_VENDEDOR = GUIGestionComisiones.RANKING_VENDEDOR;
	private static final int ELIMINA_VENDEDOR = GUIGestionComisiones.ELIMINA_VENDEDOR;
	private static final String[] OPTION_NAMES = {"NUEVO_VENDEDOR",
		"NUEVA_VENTA", "DATOS_VENDEDOR", "RANKING_VENDEDOR", "ELIMINA_VENDEDOR"};

	@Test
	void nuevoVendedorNombreRepetidoTests() {
		System.out.println("nuevoVendedorNombreRepetidoTests");

		interaccionGUI(NUEVO_VENDEDOR, "Pepa",
				"anhadido", "Ya existe");

		interaccionGUI(NUEVO_VENDEDOR, "Lolo",
				"anhadido", "Ya existe");

		// repite Pepa
		interaccionGUI(NUEVO_VENDEDOR, "Pepa",
				"Ya existe", "anhadido");
	}

	@Test
	void nuevaVentaErrorNombreTest() {
		System.out.println("nuevaVentaErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}

		// comprueba error al anhadir venta a un vendedor no existente
		interaccionGUI(NUEVA_VENTA, "nombre mal" + "\n" + "100.0" + "\n",
				"No existe", "Venta anhadida");


		// comprueba que puede anhadir ventas a los vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVA_VENTA, nombre + "\n" + "100.0" + "\n",
					"Venta anhadida", "No existe");
		}
	}

	@Test
	void datosVendedorErrorNombreTest() {
		System.out.println("datosVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}

		// comprueba error al obtener los datos de un vendedor no existente
		interaccionGUI(DATOS_VENDEDOR, "nombre mal",
				"No existe", "Nombre");


		// comprueba que se puede obtener los datos de los vendedores
		for (String nombre: nombres) {
			interaccionGUI(DATOS_VENDEDOR, nombre,
					"Nombre", "No existe");
		}
	}

	@Test
	void rankingVendedorErrorNombreTest() {
		System.out.println("rankingVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}

		// comprueba error al obtener los datos de un vendedor no existente
		interaccionGUI(RANKING_VENDEDOR, "nombre mal",
				"No existe", "Nombre");


		// comprueba que se puede obtener los datos de los vendedores
		for (String nombre: nombres) {
			interaccionGUI(RANKING_VENDEDOR, nombre,
					"Nombre", "No existe");
		}
	}

	@Test
	void eliminaVendedorErrorNombreTest() {
		System.out.println("eliminaVendedorErrorNombreTest");

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}

		// comprueba error al obtener los datos de un vendedor no existente
		interaccionGUI(ELIMINA_VENDEDOR, "nombre mal",
				"No existe", "Nombre");


		// comprueba que se pueden eliminar los vendedores
		for (String nombre: nombres) {
			interaccionGUI(ELIMINA_VENDEDOR, nombre,
					"Eliminado", "No existe");
		}
	}

	@Test
	void comisionVentaSimpleTest() {
		System.out.println("comisionVentaSimpleTest");
		final double importeVenta = 100.0;
		double comision;

		// anhade vendedore
		interaccionGUI(NUEVO_VENDEDOR, "Pepe",
				"anhadido", "Ya existe");

		// comprueba que el vendedor comienza con 0 de comision
		comision = leeDoubleGUI(DATOS_VENDEDOR, "Pepe",
				"Nombre", "No existe");	
		assertEquals(0.0, comision);

		// anhade una venta
		interaccionGUI(NUEVA_VENTA, "Pepe" + "\n" + importeVenta + "\n",
				"Venta anhadida", "No existe");

		// comprueba que la comision es correcta
		comision = leeDoubleGUI(DATOS_VENDEDOR, "Pepe",
				"Nombre", "No existe");	
		assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);
	}

	@Test
	void comisionesVentaTest() {
		System.out.println("comisionesVentaTest");
		final double importeVenta = 100.0;
		double comision;

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}

		// comprueba que los vendedores comienzan con 0 de comision
		for (String nombre: nombres) {
			comision = leeDoubleGUI(DATOS_VENDEDOR, nombre,
					"Nombre", "No existe");	
			assertEquals(0.0, comision);
		}

		// anhade ventas
		for (String nombre: nombres) {
			interaccionGUI(NUEVA_VENTA, nombre + "\n" + importeVenta + "\n",
					"Venta anhadida", "No existe");
		}

		// comprueba que las comisiones son correctas
		for (String nombre: nombres) {
			comision = leeDoubleGUI(DATOS_VENDEDOR, nombre,
					"Nombre", "No existe");	
			assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);
		}
		
		// anhade otra venta a uno de los vendedores
		interaccionGUI(NUEVA_VENTA, "Lolo" + "\n" + importeVenta + "\n",
				"Venta anhadida", "No existe");

		// comprueba que las comisiones son correctas
		for (String nombre: nombres) {
			comision = leeDoubleGUI(DATOS_VENDEDOR, nombre,
					"Nombre", "No existe");	
			if (nombre.equals("Lolo")) {
				assertEquals(importeVenta * 2 * FACTOR_COMISION, comision, 0.0001);
			} else {
				assertEquals(importeVenta * FACTOR_COMISION, comision, 0.0001);		
			}
		}	
	}

	@Test
	void rankingSimpleTest() {
		System.out.println("rankingSimpleTest");
		final double importeVenta = 100.0;

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}
		
		// anhade una venta para uno de los vendedores
		interaccionGUI(NUEVA_VENTA, nombres[1] + "\n" + importeVenta + "\n",
				"Venta anhadida", "No existe");

		// comprueba que es el primero del ranking
		int posRanking = leeIntGUI(RANKING_VENDEDOR, nombres[1],
				"Nombre", "No existe");	
		assertEquals(1, posRanking);
	}

	@Test
	void rankingTest() {
		System.out.println("rankingTest");
		int posRanking;

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}
		
		// anhade ventas diferentes para cada vendedor
		interaccionGUI(NUEVA_VENTA, nombres[0] + "\n" + 10 + "\n",
				"Venta anhadida", "No existe");
		interaccionGUI(NUEVA_VENTA, nombres[1] + "\n" + 30 + "\n",
				"Venta anhadida", "No existe");
		interaccionGUI(NUEVA_VENTA, nombres[2] + "\n" + 20 + "\n",
				"Venta anhadida", "No existe");

		// comprueba que las posiciones en el ranking son correctas
		posRanking = leeIntGUI(RANKING_VENDEDOR, nombres[0],
				"Nombre", "No existe");	
		assertEquals(3, posRanking);
		posRanking = leeIntGUI(RANKING_VENDEDOR, nombres[1],
				"Nombre", "No existe");	
		assertEquals(1, posRanking);
		posRanking = leeIntGUI(RANKING_VENDEDOR, nombres[2],
				"Nombre", "No existe");	
		assertEquals(2, posRanking);
	}

	@Test
	void eliminaVendedorTest() {
		System.out.println("eliminaVendedorTest");

		// anhade vendedores
		for (String nombre: nombres) {
			interaccionGUI(NUEVO_VENDEDOR, nombre,
					"anhadido", "Ya existe");
		}
		
		// elimina uno de ellos
		interaccionGUI(ELIMINA_VENDEDOR, nombres[1],
				"Eliminado", "No existe");
		
		// comprueba que se pueden obtener los datos de los no eliminados
		interaccionGUI(DATOS_VENDEDOR, nombres[0],
				"Nombre", "No existe");	
		interaccionGUI(DATOS_VENDEDOR, nombres[2],
				"Nombre", "No existe");	
		
		// comprueba que no se puede hacer nada con el eliminado
		interaccionGUI(DATOS_VENDEDOR, nombres[1],
				"No existe", "Nombre");
		interaccionGUI(ELIMINA_VENDEDOR, nombres[1],
				"No existe", "Eliminado");
		interaccionGUI(RANKING_VENDEDOR, nombres[1],
				"No existe", "Ranking");
		interaccionGUI(NUEVA_VENTA, nombres[1] + "\n" + 100,
				"No existe", "anhadida");
	}
	
	// metodos para la infraestructura de test

	@BeforeAll
	public static void preparaModoTest() {
		FundamentosFactory.setTestMode(true);
		System.out.println("\n[ENABLED TEST MODE]\n");
	}

	@AfterAll
	public static void finalizaModoTest() {
		FundamentosFactory.setTestMode(false);
		System.out.println("\n[DISABLED TEST MODE]\n");
	}

	/**
	 * Se ejecuta antes de cada test.
	 * @throws IOException error accediendo a las pipes.
	 */
	@BeforeEach
	public void lanzaMain() throws IOException {
		TestPipes.init(GUIGestionComisiones.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaMain() throws InterruptedException {
		TestPipes.toMain("" + GUIGestionComisiones.FIN_APLICACION);
		TestPipes.tMain.join();
	}

	private String interaccionGUI(int opcionMenu, String datos,
			String esperado, String noEsperado) {
		String respuesta;
		String entrada = opcionMenu + "\n" + datos;
		TestPipes.toMain(entrada);
		respuesta = TestPipes.readStringFromMain();
		System.out.println(OPTION_NAMES[opcionMenu] + ":" +
				datos.replace('\n', ':') + " -> " + respuesta);
		assertTrue(respuesta.contains(esperado),
				"Respuesta:" + respuesta + "\nNo contiene \"" + esperado + "\"");
		assertFalse(respuesta.contains(noEsperado),
				"Respuesta:" + respuesta + "\nContiene \"" + noEsperado + "\"");
		return respuesta;
	}

	private double leeDoubleGUI(int opcionMenu, String datos,
			String esperado, String noEsperado) {
		String respuesta = interaccionGUI(opcionMenu, datos, esperado, noEsperado);

		// obtiene la ultima palabra y la convierte a double
		String ultimaPalabra = respuesta.substring(respuesta.lastIndexOf(" ") + 1);	
		double doubleRet = Double.NaN;
		try {
			return Double.parseDouble(ultimaPalabra);
		} catch (NumberFormatException e) {
			fail("Respuesta:" + respuesta + "\ndeberia terminar en un double");
		}
		return doubleRet;
	}

	private int leeIntGUI(int opcionMenu, String datos,
			String esperado, String noEsperado) {
		String respuesta = interaccionGUI(opcionMenu, datos, esperado, noEsperado);

		// obtiene la ultima palabra y la convierte a int
		String ultimaPalabra = respuesta.substring(respuesta.lastIndexOf(" ") + 1);	
		int intRet = Integer.MIN_VALUE;
		try {
			return Integer.parseInt(ultimaPalabra);
		} catch (NumberFormatException e) {
			fail("Respuesta:" + respuesta + "\ndeberia terminar en un int");
		}
		return intRet;
	}

}
