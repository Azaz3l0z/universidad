package examen;

/**
 * Estancia realizada en una habitacion del hotel por dos huespedes.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class Estancia {
	private final String dniHuesped1;
	private final String dniHuesped2;
	private String[] dnis;
	private double tiempo;
	
	/**
	 * Construye la estancia.
	 * @param dniHuesped1 DNI de uno de los huespedes.
	 * @param dniHuesped2 DNI de otro de los huespedes.
	 */
	public Estancia(String dniHuesped1, String dniHuesped2) {
		this.dniHuesped1 = dniHuesped1;
		this.dniHuesped2 = dniHuesped2;
		this.dnis = new String[] {dniHuesped1, dniHuesped2};
		this.tiempo = System.currentTimeMillis();
	}

	/**
	 * Retorna el DNI de huesped 1 de la estancia.
	 * @return el DNI de huesped 1 de la estancia.
	 */
	public String dniHuesped1() {
		return dniHuesped1;
	}

	/**
	 * Retorna el DNI de huesped 2 de la estancia.
	 * @return el DNI de huesped 2 de la estancia.
	 */
	public String dniHuesped2() {
		return dniHuesped2;
	}
	
	/**
	 * Retorna la lista de todos los DNIs de la estancia.
	 * @return la lista de todos los DNIs de la estancia.
	 */
	public String[] dnis() {
		return dnis;
	}

	/**
	 * Anhadido personal. Retorna el tiempo asignado a la estancia (el de inicio o el total).
	 * @return el tiempo asignado a la estancia (el de inicio o el total).
	 */
	public double tiempo() {
		return tiempo;
	}
	
	/**
	 * Anhadido personal. Fija el tiempo de la estancia restando el tiempo actual y el tiempo de inicio.
	 */
	public void fijarTiempo() {
		this.tiempo = System.currentTimeMillis() - tiempo;
	}
	
	
}
