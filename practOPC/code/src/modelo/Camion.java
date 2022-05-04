package modelo;

import java.util.ArrayList;

/**
 * Camiones propiedad de la empresa
 * @author Yago Hernandez
 *
 */
public class Camion {
	// Atributos del camion
	private int id;
	ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
	
	/**
	 * Crea un camion
	 * @param id del camion
	 */
	public Camion(int id) {
		this.id = id;
	}
	
	/**
	 * Carga el camion con el paquete indicado
	 * @param paquete que se cargara en el cambion
	 */
	public void cargar(Paquete paquete) {
		paquetes.add(paquete);
	}
	
	/**
	 * Saca el paquete indicado del camion
	 * @param paquete a sacar del camion
	 */
	public void descargar(Paquete paquete) {
		paquetes.remove(paquete);
	}
	
	/**
	 * Busca el paquete indicado por el codigo	
	 * @param codigo Codigo del paquete a encontrar
	 * @return el paquete al que pertenece el codigo o null si no
	 * 		   se ha encontrado nada
	 */
	public Paquete buscarPaquete(String codigo) {
		for (Paquete p : paquetes) {
			if (p.codigo().equals(codigo)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Retorna el identificador del camion
	 * @return el identificador del camion
	 */
	public int id() {
		return id;
	}
}
