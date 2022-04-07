package pract02.modelo;

/**
 * Instrumento de medida del laboratorio.
 * 
 * @author Metodos de Programacion (UC)
 * @version mar-2021
 */
public class Instrumento {
	// Frecuencia con la que muestrea el instrumento.
	private final int frecuenciaMuestreo; // muestras por segundo
	
	/**
	 * Construye un instrumento de medida con la frecuencia de muestreo
	 * indicada.
	 * @param frecuenciaMuestreo frecuencia de muestreo en medidas/s
	 */
	public Instrumento(int frecuenciaMuestreo) {
		this.frecuenciaMuestreo = frecuenciaMuestreo;
	}

	/**
	 * Retorna un array de medidas correspondientes a la senhal en el intervalo
	 * con la duracion indicada.
	 * 
	 * @param duracion duracion del intervalo durante el que se desean obtener
	 * las medidas.
	 * @return array de medidas en el intervalo indicado.
	 */
	public Senhal capturaSenal(double duracion) {
		// Este metodo simula la existencia de un instrumento real de medida
		final int numMedidas = (int) (frecuenciaMuestreo * duracion);
		final double a = 2.5;
		final double periodo = 10;
		final double fase = Math.PI / 6; 
		final double factor_ruido = 0.2; 

		Senhal senhal = new Senhal(numMedidas);
		for (int i = 0; i < numMedidas; i++) {
			double t = duracion / numMedidas * i;
			double valor = a * Math.sin(2 * Math.PI * t / periodo - fase) +
					factor_ruido * (2 * Math.sin(200 * t) + Math.cos(500 * t) / 2);
			senhal.registraMedida(i, new Medida(t, valor));
		}

		return senhal;
	}

}
