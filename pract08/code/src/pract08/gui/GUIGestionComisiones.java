package pract08.gui;

import fundamentos_test.*;

import pract08.modelo.Tienda;
import pract08.modelo.Vendedor;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para la gestion de las
 * comisiones de los vendedores de una tienda.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version abr-22
 */

public class GUIGestionComisiones {
	// opciones del menu
	public static final int NUEVO_VENDEDOR = 0;
	public static final int NUEVA_VENTA = 1;
	public static final int DATOS_VENDEDOR = 2;
	public static final int RANKING_VENDEDOR = 3;
	public static final int ELIMINA_VENDEDOR = 4;
	public static final int FIN_APLICACION = 5;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String nombre;
		Lectura lect;

		// crea la tienda
		// TODO
		Tienda tienda = new Tienda();
		Tienda.ResultadoError error;
		Vendedor vendedor;
		
		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Comisiones tienda");
		menu.insertaOpcion("Nuevo Vendedor", NUEVO_VENDEDOR);
		menu.insertaOpcion("Nueva venta", NUEVA_VENTA);
		menu.insertaOpcion("Datos Vendedor", DATOS_VENDEDOR);
		menu.insertaOpcion("Ranking vendedor", RANKING_VENDEDOR);
		menu.insertaOpcion("Elimina vendedor", ELIMINA_VENDEDOR);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case NUEVO_VENDEDOR:
				lect = FundamentosFactory.getLectura("Nuevo Vendedor");
				lect.creaEntrada("Nombre", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				
				// anhade el vendedor a la tienda
				// TODO
				vendedor = new Vendedor(nombre);
				error = tienda.anhadeVendedor(vendedor);
				if (tienda.errorToBoolean(error)) { // TODO
					mensaje("ERROR", "Ya existe el vendedor " + nombre);
				} else {
					mensaje("Vendedor anhadido",
							"Vendedor " + nombre + " anhadido con exito");
				}
				break;

			case NUEVA_VENTA:
				lect = FundamentosFactory.getLectura("Datos Venta");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.creaEntrada("Importe", "100.0");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				double importe = lect.leeDouble("Importe");
				
				// anhade la venta al vendedor
				// TODO
				error = tienda.nuevaVenta(nombre, importe);
				if (tienda.errorToBoolean(error)) { // TODO
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Venta anhadida",
							"Venta anhadida a " + nombre);
				}
				break;

			case DATOS_VENDEDOR:
				lect = FundamentosFactory.getLectura("Datos Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				
				// busca el vendedor y muestra sus datos
				// TODO
				vendedor = tienda.buscaVendedor(nombre);
				if (vendedor == null) { // TODO
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Datos vendedor",
							"Nombre:" + vendedor.nombre() +
							" Comision: " + vendedor.comisiones());
				}
				break;

			case RANKING_VENDEDOR:
				lect = FundamentosFactory.getLectura("Ranking Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				
				// muestra la posicion en el ranking del vendedor
				// TODO
				int posRanking = tienda.ranking(nombre);
				if (posRanking == -1) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Ranking vendedor",
							"Nombre:" + nombre +
							" Posicion Ranking: " + posRanking);
				}
				break;

			case ELIMINA_VENDEDOR:
				lect = FundamentosFactory.getLectura("Elimina Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				
				// elimina el vendedor
				// TODO
				error = tienda.eliminaVendedor(nombre);
				if (tienda.errorToBoolean(error)) { // TODO
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Vendedor eliminado",
							"Eliminado " + nombre);
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
		Mensaje msj = FundamentosFactory.getMensaje(titulo);
		msj.escribe(txt);
	}

}
