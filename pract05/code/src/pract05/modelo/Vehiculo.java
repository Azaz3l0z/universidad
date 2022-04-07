package pract05.modelo;

/**
 * Vehiculo que se carga en un barco transbordador.
 * 
 * @author Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-21
 */
public class Vehiculo {
	private final String matricula;
	private final double peso;
	private final int numOcupantes;
	
	/**
	 * Construye un vehiculo con los datos indicados.
	 * @param matricula matricula del vehiculo
	 * @param peso peso del vehiculo (en Kg)
	 * @param numOcupantes numero de ocupantes del vehiculo
	 */
	public Vehiculo(String matricula, double peso, int numOcupantes) {
		this.matricula = matricula;
		this.peso = peso;
		this.numOcupantes = numOcupantes;
	}

	/**
	 * Retorna el peso del vehiculo.
	 * @return peso del vehiculo
	 */
	public double peso() {
		return peso;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 * @return  matricula del vehiculo
	 */
	public String matricula() {
		return matricula;
	}

	/**
	 * Retorna el numero de ocupantes del vehiculo.
	 * @return numero de ocupantes del vehiculo
	 */
	public int numOcupantes() {
		return numOcupantes;
	}
	
}
