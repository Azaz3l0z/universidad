package pract02.gui;

import fundamentos.*;
import pract02.modelo.Instrumento;
import pract02.modelo.Senhal;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion para el procesado
 * de senhales.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version mar-21
 */
public class GUISenhales {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del main (no usados)
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int CAPTURA_SENHAL = 0;
		final int FILTRA_SENHAL = 1;
		final int PARAMETROS_SENHAL = 2;
		
		// Crea el instrumento de medida con una frecuencia de muestreo de
		// 100 medidas/s
		Instrumento instrumento = new Instrumento(100);

		// variables auxiliares
		Lectura lect;

		// Senhal capturada por el instrumento (vale null hasta que se capture
		// alguna senhal). 
		Senhal senhalCapturada = null;

		// crea la ventana de menu
		Menu menu = new Menu("Gestion senhales");
		menu.insertaOpcion("Captura senhal", CAPTURA_SENHAL);
		menu.insertaOpcion("Filtra senhal", FILTRA_SENHAL);
		menu.insertaOpcion("Parametros senhal", PARAMETROS_SENHAL);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case  CAPTURA_SENHAL:
				lect = new Lectura("Captura senhal");
				lect.creaEntrada("Duracion", 20);
				lect.esperaYCierra();
				double duracion = lect.leeDouble("Duracion");

				senhalCapturada = instrumento.capturaSenal(duracion);
				muestraSenhal(senhalCapturada, "Senhal capturada");
				break;

			case FILTRA_SENHAL:
				if (senhalCapturada == null) {
					mensaje("ERROR", "Primero debes capturar la senhal");
					break;
				}
				lect = new Lectura("Numero de filtrados");
				lect.creaEntrada("Numero de filtrados", 2);
				lect.esperaYCierra();
				int numFiltrados = lect.leeInt("Numero de filtrados");

				senhalCapturada.filtraSenal(numFiltrados);
				muestraSenhal(senhalCapturada,
						"Senhal filtrada " + numFiltrados + " veces");
				break;

			case PARAMETROS_SENHAL:
				if (senhalCapturada == null) {
					mensaje("ERROR", "Primero debes capturar la senhal");
					break;
				}
				double amplitud = senhalCapturada.calculaAmplitud();
				double fase = senhalCapturada.calculaFase();
				double periodo = senhalCapturada.calculaPeriodo();
				
				muestraSenhal(senhalCapturada, "Amplitud:" + amplitud + 
						"    Fase:" + fase + "    Periodo:" + periodo);
				break;

			default:
				throw new AssertionError("Opcion no esperada");
			}

		}
	}

	/**
	 * Muestra la senhal de forma grafica.
	 * @param senhal senhal a mostrar.
	 * @param titulo titulo de la grafica.
	 */
	private static void muestraSenhal(Senhal senhal, String titulo) {
		Grafica ventGrafica = new Grafica();
		ventGrafica.ponTitulo(titulo);
		for (int i = 0; i < senhal.numMedidas(); i++) {
			ventGrafica.inserta(senhal.medida(i).tiempo(),
					senhal.medida(i).valor());
		}
		ventGrafica.pinta();
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

}
