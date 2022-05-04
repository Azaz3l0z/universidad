package pract09.modelo;

/**
 * Piso gestionado por la inmobiliaria.
 * 
 * @author  Metodos de Programacion (UC)
 * @version oct-21
 */
public class Piso {	
	// caracteristicas del piso
	private final String referencia;
	private final int superficie;
	private final int numHabitaciones;
	private final double precio;
	
	// Agente que tiene asignado el piso
	private Agente agente = null;
	
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
	 * Retorna el agente que tiene asignado el piso.
	 * @return el agente asignado al piso o
	 *         null si no tiene ningun asgente asignado
	 */
	public Agente agenteAsignado() {
		return agente;
	}
	
	/**
	 * Asigna el agente que se encarga del piso.
	 * @param agente agente asignado al piso
	 */
	public void asignaAgente(Agente agente) {
		this.agente = agente;
	}

	/**
	 * Indica si el piso tiene algun agente asignado.
	 * @return verdadero si el piso tiene algun agente asignado.
	 */
	public boolean estaAsignado() {
		return agente != null;
	}

}
