package pract07.modelo;

/**
 * Piso gestionado por la inmobiliaria.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class Piso {
	// caracteristicas del piso
	private final String referencia;
	private final int superficie;
	private final int numHabitaciones;
	private final double precio;
	private Agente agente;
	
	/**
	 * Construye un piso con los datos indicados.
	 * @param refererencia referencia del piso
	 * @param precio Precio del piso
	 * @param superficie Superficie del piso en m2
	 * @param numHabitaciones Numero de habitaciones del piso
	 */
	public Piso(String refererencia, double precio, int superficie, int numHabitaciones) {
		this.referencia = refererencia;
		this.precio = precio;
		this.superficie = superficie;
		this.numHabitaciones = numHabitaciones;
	}

	/**
	 * Retorna la referencia del piso.
	 * @return Referencia del piso
	 */
	public String referencia() {
		return referencia;
	}

	/**
	 * Retorna la superficie del piso en m2.
	 * @return Superficie del piso en m2
	 */
	public int superficie() {
		return superficie;
	}

	/**
	 * Retorna el numero de habitaciones del piso.
	 * @return Numero de habitaciones del piso
	 */
	public int numHabitaciones() {
		return numHabitaciones;
	}
	
	/**
	 * Retorna el precio del piso.
	 * @return Precio del piso
	 */
	public double precio() {
		return precio;
	}
	
	/**
	 * Retorna el agente asignado al piso.
	 * @return el agente asignado al piso 
	 */
	public Agente agente() {
		return agente;
	}
	
	/**
	 * Asigna un agente al piso.
	 * @param agente Agente que se le asignara al piso
	 */
	public void asignaAgente(Agente agente) {
		this.agente = agente;
	}

}
