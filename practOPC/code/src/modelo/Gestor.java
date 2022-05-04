package modelo;

import java.util.ArrayList;

/**
 * Gestor de la empresa de reparto
 * @author Yago Hernandez
 *
 */
public class Gestor {
	// Atributos del gestor
	private final Camion[] camiones;
	public enum ResultadoError {NO_ERROR, CAMION_NO_EXISTE, PAQUETE_NO_EN_CAMION};
	
	/**
	 * Crea el gestor y los camiones de la empresa
	 */
	public Gestor() {
		camiones = new Camion[10];
		for (int k = 0; k < camiones.length; k++) {
			camiones[k] = new Camion(k);
		}
	}
	
	/**
	 * Carga un nuevo paquete con una descripcion dada en el camion indicado
	 * @param descripcion Descripcion del paquete
	 * @param idCamion Identificador del camion
	 * @return el codigo de error
	 */
	public ResultadoError cargarPaquete(String descripcion, int idCamion) {
		Camion camion = buscarCamion(idCamion);
		if (camion == null) {
			return ResultadoError.CAMION_NO_EXISTE;
		}
		
		Paquete paquete = new Paquete(descripcion);
		camion.cargar(paquete);		
		return ResultadoError.NO_ERROR;
	}
	
	/**
	 * Entrega el paquete indicado por el codigo del paquete en el camion indicado
	 * @param codigoPaquete Codigo del paquete a entregar
	 * @param idCamion Indentificador del camion que contiene el paquete
	 * @return el codigo del error
	 */
	public ResultadoError entregarPaquete(String codigoPaquete, int idCamion) {
		Camion camion = buscarCamion(idCamion);
		if (camion == null) {
			return ResultadoError.CAMION_NO_EXISTE;
		}
		
		Paquete paquete = camion.buscarPaquete(codigoPaquete);
		if (paquete == null) {
			return ResultadoError.PAQUETE_NO_EN_CAMION;
		}
		camion.descargar(paquete);	
		return ResultadoError.NO_ERROR;
	}
	
	/**
	 * Busca el camion que contiene el paquete con el codigo indicado
	 * @param codigoPaquete El codigo del paquete a buscar
	 * @return el identificador del camion que contiene el paquete
	 */
	public int buscarCamionConPaquete(String codigoPaquete) {
		for (Camion c : camiones) {
			Paquete paquete = c.buscarPaquete(codigoPaquete);
			if (paquete != null) {
				return c.id();
			}
		}
		return -1;
	}
	
	/**
	 * Busca los camiones que no tienen paquetes
	 * @return lista de los camiones libres
	 */
	public ArrayList<Integer> camionesLibres() {
		ArrayList<Integer> listaCamionesVacios = new ArrayList<Integer>();
		for (Camion c : camiones) {
			if (c.paquetes.size() == 0) {
				listaCamionesVacios.add(c.id());
			}
		}
		System.out.println(listaCamionesVacios);
		return listaCamionesVacios;
	}
	
	/**
	 * Metodo privado para buscar un camion dado un identificador
	 * @param id Identificador del camion
	 * @return el camion que tiene ese id o null si no existe ningun camion
	 */
	private Camion buscarCamion(int id) {
		for (Camion c : camiones) {
			if (c.id() == id) {
				return c;
			}
		}
		return null;
	}
}
