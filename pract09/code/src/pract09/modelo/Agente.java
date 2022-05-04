package pract09.modelo;

/**
 * Agente de la inmobiliaria.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-20
 */
public class Agente {
	
	private final String nombre;
	private final String dni;
	private int numVentas = 0;
	
	/**
	 * Construye un agente con los datos indicados.
	 * @param nombre Nombre del agente
	 * @param dni DNI del agente
	 */
	public Agente(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}

	/**
	 * Retorna el nombre del agente.
	 * @return Nombre del agente
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna el DNI del agente.
	 * @return DNI del agente
	 */
	public String dni() {
		return dni;
	}
	
	/**
	 * Retorna el numero de pisos vendidos por el agente.
	 * @return el numero de pisos vendidos por el agente.
	 */
	public int numVentas()  {
		return numVentas;
	}
	
	/**
	 * Anhade una nueva venta al agente.
	 */
	public void anhadeVenta() {
		numVentas++;
	}
}
