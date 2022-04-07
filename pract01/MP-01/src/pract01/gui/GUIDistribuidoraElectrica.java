package pract01.gui;

import fundamentos.*;
import pract01.modelo.Cliente;
import pract01.modelo.DistribuidoraElectrica;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion para la facturacion del
 * consumo de los clientes de una companhia distribuidora de electricidad.
 * 
 * @author  Metodos de Programacion (UC)
 * @version feb-21
 */
public class GUIDistribuidoraElectrica {

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del main (no usados)
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int CONSULTA_CONSUMO = 0;
		final int REGISTRA_CONSUMO = 1;
		final int OBTIENE_IMPORTE = 2;
		final int MEJOR_TARIFA = 3;

		// variables auxiliares
		Lectura lect;
		int id;
		int dia;
		int hora;
		float consumo;
		Cliente.TipoTarifa tarifa;

		// Companhia distribuidora de electricidad
		DistribuidoraElectrica distribuidora = new DistribuidoraElectrica();

		// crea la ventana de menu
		Menu menu = new Menu("Distribuidora Electrica");
		menu.insertaOpcion("Consulta consumo", CONSULTA_CONSUMO);
		menu.insertaOpcion("Registra consumo", REGISTRA_CONSUMO);
		menu.insertaOpcion("Obtiene importe", OBTIENE_IMPORTE);
		menu.insertaOpcion("Mejor tarifa", MEJOR_TARIFA);

		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case  CONSULTA_CONSUMO:
				lect = new Lectura("Consulta consumo");
				lect.creaEntrada("Id cliente", 0);
				lect.creaEntrada("Dia", 1);
				lect.creaEntrada("Hora", 9);
				lect.esperaYCierra();
				id = lect.leeInt("Id cliente");
				dia = lect.leeInt("Dia");
				hora = lect.leeInt("Hora");

				consumo = distribuidora.consumoCliente(id, dia, hora);
				if (Float.isNaN(consumo)) {
					mensaje("Error", "Id de cliente incorrecto");
				} else {
					mensaje("Consumo cliente " + id,
							"Consumo (d=" + dia + ",h=" + hora + ")=" + consumo);
				}
				break;

			case REGISTRA_CONSUMO:
				lect = new Lectura("Registra consumo");
				lect.creaEntrada("Id cliente", 0);
				lect.creaEntrada("Dia", 1);
				lect.creaEntrada("Hora", 9);
				lect.creaEntrada("Consumo (kWh)", 10.0);
				lect.esperaYCierra();
				id = lect.leeInt("Id cliente");
				dia = lect.leeInt("Dia");
				hora = lect.leeInt("Hora");
				consumo = (float) lect.leeDouble("Consumo (kWh)");
				
				int ret = distribuidora.registraConsumoCliente(id, dia, hora,
						consumo);
				if (ret == -1) {
					mensaje("Error", "Id de cliente incorrecto");
				} else {
					mensaje("Cliente " + id, "Registrado consumo.");
				}
				break;

			case OBTIENE_IMPORTE:
				lect = new Lectura("Obtiene importe mensual");
				lect.creaEntrada("Id cliente", 0);
				lect.esperaYCierra();
				id = lect.leeInt("Id cliente");
				float importe = distribuidora.obtenImporteMensual(id);
				
				if (Float.isNaN(importe)) {
					mensaje("Error", "Id de cliente incorrecto");
				} else {
					mensaje("Cliente " + id,
							"Importe mensual=" + importe);
				}
				break;

			case MEJOR_TARIFA:
				lect = new Lectura("Mejor tarifa");
				lect.creaEntrada("Id cliente", 0);
				lect.esperaYCierra();
				id = lect.leeInt("Id cliente");
				tarifa = distribuidora.obtenMejorTarifa(id);
				
				if (tarifa == null) {
					mensaje("Error", "Id de cliente incorrecto");
				} else {
					mensaje("Cliente " + id, "Mejor tarifa:" + tarifa);
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
