package GUI;

import java.util.ArrayList;

import fundamentos.*;
import modelo.*;

public class GUIEmpresaCamiones {
	public static void main(String args[]) {
		// Crear el gestor de camiones
		Gestor gestor = new Gestor();
		Gestor.ResultadoError error;
		
		// Ventanas del menu
		final int CARGAR = 0;
		final int ENTREGAR = 1;
		final int BUSCAR = 2;
		final int CAMIONES_VACIOS = 3;
		
		// Variables auxiliares
		Lectura lect;
		
		// Crear el menu		
		Menu menu = new Menu("EmpresaCamiones");
		menu.insertaOpcion("Carga paquete en camión", CARGAR);
		menu.insertaOpcion("Entrega paquete de camión", ENTREGAR);
		menu.insertaOpcion("Busca camión con paquete", BUSCAR);
		menu.insertaOpcion("Lista camiones vacíos", CAMIONES_VACIOS);
		
		int opcion;
		
		while (true) {
			opcion = menu.leeOpcion();
			
			// Seleccion de la ventana elegida
			switch (opcion) {
			case CARGAR:
				lect = new Lectura("Cargar camion");
				lect.creaEntrada("Id del camion", "0");
				lect.creaEntrada("Descripcion", "Television 4k");
				
				lect.esperaYCierra();
				
				int id = lect.leeInt("Id del camion");
				String descripcion = lect.leeString("Descripcion");
				error = gestor.cargarPaquete(descripcion, id);
				switch (error) {
				case CAMION_NO_EXISTE:
					mensaje("Error", "No existe el identificador del camion especificado");
					break;
				case NO_ERROR:
					break;
					
				default:
					mensaje("Error", "Se ha producido un error imprevisto en el desarrollo del programa");
				}
				
				break;
			case ENTREGAR:
				lect = new Lectura("Entregar paquete");
				lect.creaEntrada("Id del camion", "0");
				lect.creaEntrada("Codigo del paquete", "PAQ1");
				
				lect.esperaYCierra();
				
				id = lect.leeInt("Id del camion");
				String codigo = lect.leeString("Codigo del paquete");
				error = gestor.entregarPaquete(codigo, id);
				switch(error) {
				case CAMION_NO_EXISTE:
					mensaje("Error", "No existe el identificador del camion especificado");
					break;
				case PAQUETE_NO_EN_CAMION:
					mensaje("Error", "El paquete solicitado no se encuentra en el camion especificado");
					break;
				default:
					mensaje("Error", "Se ha producido un error imprevisto en el desarrollo del programa");
				}				
				break;
				
			case BUSCAR:
				lect = new Lectura("Buscar paquete");
				lect.creaEntrada("Codigo del paquete", "PAQ1");
				
				lect.esperaYCierra();
				
				codigo = lect.leeString("Codigo del paquete");
				id = gestor.buscarCamionConPaquete(codigo);
				if (id == -1) {
					mensaje("Error", "El paquete solicitado no se ha encontrado");
				} else {
					mensaje("Paquete Encontrado", 
							"El paquete solicitado está en el camion " + Integer.toString(id));
				}
				break;
				
			case CAMIONES_VACIOS:
				ArrayList<Integer> listaCamionesVacios = gestor.camionesLibres();
				String camionesLibres = "";
				for (Integer i : listaCamionesVacios) {
					camionesLibres += " " + i.toString();
				}
				mensaje("Sex", "Los camiones vacios son:" + camionesLibres);
				break;
			}
		}
	}
	
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
}
