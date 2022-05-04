package pract09.gui;

import fundamentos.*;
import pract09.modelo.Agente;
import pract09.modelo.Inmobiliaria;
import pract09.modelo.Piso;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion para la gestion de
 * una inmobiliaria.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */
public class GUIGestionInmobiliaria {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del main (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_PISO = 0;
		final int ANHADE_AGENTE = 1;
		final int ASIGNA_PISO = 2;
		final int VENDE_PISO = 3;
		final int DATOS_AGENTE = 4;

		// variables auxiliares
		String ref;
		String dni;
		Lectura lect;
		Inmobiliaria.ResultadoError error;

		// crea la inmobiliaria
		Inmobiliaria inmobiliaria = new Inmobiliaria();

		// crea la ventana de menu
		Menu menu = new Menu("Inmobiliaria");
		menu.insertaOpcion("Anade piso", ANHADE_PISO);
		menu.insertaOpcion("Anade agente", ANHADE_AGENTE);
		menu.insertaOpcion("Asigna piso a agente", ASIGNA_PISO);
		menu.insertaOpcion("Vende piso", VENDE_PISO);
		menu.insertaOpcion("Datos Agente", DATOS_AGENTE);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case  ANHADE_PISO:
				lect = new Lectura("Datos Piso");
				lect.creaEntrada("Referencia", "REF1");
				lect.creaEntrada("Precio", 80000);
				lect.creaEntrada("Superficie", 70);
				lect.creaEntrada("Habitaciones", 2);

				// TODO: aplicar el patron de excepciones recuperables para
				// repetir la entrada de datos hasta que estos sean validos
				// (ver ejemplos en apuntes de teoria, diapositivas 21 y 22)
				lect.esperaYCierra();
				String referencia = lect.leeString("Referencia");
				int precio = lect.leeInt("Precio");
				int superficie = lect.leeInt("Superficie");
				int habitaciones = lect.leeInt("Habitaciones");

				// crea el piso y lo anhade a la inmobiliaria
				error = inmobiliaria.anhadePiso(
						new Piso(referencia, precio, superficie, habitaciones));

				// comprueba los errores
				switch (error) {
				case REFERENCIA_PISO_YA_EXISTENTE:
					mensaje("Error", "Ya existe otro piso con la referencia " +
							referencia);
					break;
				case NO_ERROR: 
					mensaje("Piso anhadido", "Piso " + referencia);
					break;
				default:
					mensaje("ERROR INESPERADO",
							"Codigo de error no esperado para el metodo invocado");
				}
				break;

			case ANHADE_AGENTE:
				lect = new Lectura("Datos agente");
				lect.creaEntrada("Nombre", "Pepe");
				lect.creaEntrada("DNI", "12345678A");

				// TODO: aplicar el patron de excepciones recuperables para
				// permitir al usuario 3 intentos para introducir un DNI valido
				// (ver ejemplos en apuntes de teoria, diapositivas 21 y 22)
				lect.esperaYCierra();
				String nombre = lect.leeString("Nombre");
				dni = lect.leeString("DNI");

				// Anhade el agente
				error = inmobiliaria.anhadeAgente(new Agente(nombre, dni));

				// comprueba los errores
				switch (error) {
				case DNI_AGENTE_YA_EXISTENTE:
					mensaje("Error", "Ya existe otro agente con el DNI:" + dni);
					break;
				case NO_ERROR: 
					mensaje("Agente anhadido", "Agente " + dni);
					break;
				default:
					mensaje("ERROR INESPERADO",
							"Codigo de error no esperado para el metodo invocado");
				}
				break;

			case ASIGNA_PISO:
				lect = new Lectura("Asigna Piso");
				lect.creaEntrada("Ref Piso", "REF1");
				lect.creaEntrada("DNI", "12345678A");

				lect.esperaYCierra();
				ref = lect.leeString("Ref Piso");
				dni = lect.leeString("DNI");

				// Asigna el piso al agente
				// TODO: utilizar excepciones como mecanismo de notificacion
				// de errores (ver caso de uso VENDE_PISO)
				error = inmobiliaria.asignaPisoAgente(dni, ref);

				// comprueba los errores
				switch (error) {
				case DNI_AGENTE_INCORRECTO:
					mensaje("Error", "No existe ningun agente el DNI:" + dni);
					break;
				case REFERENCIA_PISO_INCORRECTA:
					mensaje("Error", "No existe ningun piso con la referencia:" + ref);
					break;
				case PISO_YA_ASIGNADO:
					mensaje("Error", "El piso " + ref + " ya esta asignado a un agente");
					break;
				case NO_ERROR: 
					mensaje("Piso asignado", 
							"Piso " + ref + " asignado al agente " + dni);
					break;
				default:
					mensaje("ERROR INESPERADO",
							"Codigo de error no esperado para el metodo invocado");
				}
				break;

			case VENDE_PISO:
				lect = new Lectura("Venta piso");
				lect.creaEntrada("Ref Piso", "REF1");
				lect.esperaYCierra();
				ref = lect.leeString("Ref Piso");

				// TODO: ejemplo de uso de excepciones en la GUI
				try {
					// Venta de piso
					Agente agente = inmobiliaria.vendePiso2(ref);
					mensaje("Piso vendido",
							"Agente con DNI " + agente.dni() +
							"\nNum ventas: " + agente.numVentas());
				} catch (Inmobiliaria.ReferenciaPisoIncorrecta e) {
					mensaje("Error", "No existe ningun piso con esa referencia");
				} catch (Inmobiliaria.PisoNoAsignado e) {
					mensaje("Error", "El piso no esta asignado a ningun agente");
				}
				break;

			case DATOS_AGENTE:
				lect = new Lectura("DNI Agente");
				lect.creaEntrada("DNI", "12345678A");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				// Muestra datos del agente
				Agente a = inmobiliaria.buscaAgente(dni);
				if (a == null)  {
					mensaje("Error",
							"No existe ningun agente con el DNI " + dni);
				} else {
					mensaje("Agente",
							"Nombre: " + a.nombre() + "\nNum. ventas: " + a.numVentas());
				}
				break;

			default:
				throw new AssertionError("Opcion no esperada");	

			}
		}
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
