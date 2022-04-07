package pract02.modelo;

/**
 * Cada una de las medidas que componen una senhal.
 * 
 * @author Metodos de Programacion (UC)
 * @version mar-2021
 */
public class Medida {
	// instante de tiempo en que se tomo la medida (en centesimas de segundo)
	private final double tiempo;

	// valor de la medida
	private final double valor;

	/**
	 * Construye una medida con los valores indicados.
	 * @param tiempo instante de tiempo en que se tomo la medida.
	 * @param valor valor de la medida.
	 */
	public Medida(double tiempo, double valor) {
		this.tiempo = tiempo;
		this.valor = valor;
	}

	/**
	 * Retorna el instante de tiempo en que se tomo la medida. 
	 * @return el instante de tiempo en que se tomo la medida. 
	 */
	public double tiempo() {
		return tiempo;
	}

	/**
	 * Retorna el valor de la medida.
	 * @return el valor de la medida.
	 */
	public double valor() {
		return valor;
	}

}
