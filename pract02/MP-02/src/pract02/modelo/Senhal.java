package pract02.modelo;

/**
 * Senhal capturada por un instrumento de medida.
 * Contiene metodos para calcular la amplitud, el periodo y la fase inicial
 * de la forma de onda sinusoidal. Descrita por la funcion:
 *                   f(t) = Amplitud * sin(2*pi*t/periodo - fase)
 * 
 * @author Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version sep-2021
 */
public class Senhal {
	// medidas que forman la senhal
	private Medida[] medidas;

	/**
	 * Construye una senhal con capacidad para el numero de medidas indicado.
	 * @param numMedidas numero de medidas de la senhal.
	 */
	public Senhal(int numMedidas) {
		medidas = new Medida[numMedidas];
	}

	/**
	 * Retorna el numero de medidas de la senhal.
	 * @return el numero de medidas de la senhal.
	 */
	public int numMedidas() {
		return medidas.length;
	}

	/**
	 * Asigna el valor a la medida i-esima de la senhal.
	 * @param i indice de la medida a asignar.
	 * @param medida medida a asignar.
	 */
	public void registraMedida(int i, Medida medida) {
		medidas[i] = medida;
	}
	
	/**
	 * Retorna la medida i-esima de la senhal.
	 * @param i indice de la medida a retornar.
	 * @return la medida i-esima de la senhal.
	 */
	public Medida medida(int i) {
		return medidas[i];
	}

	/**
	 * Calcula y retorna la amplitud de la señal. Se calcula como la diferencia
	 * entre los valores maximo y minimo de las medidas que componen la senhal.
	 * @return amplitud de la senhal.
	 */
	public double calculaAmplitud() {
		double maximo = -Double.MAX_VALUE;
		double minimo = Double.MAX_VALUE;

		for (int i = 0; i < numMedidas(); i++) {
			minimo = (medida(i).valor() < minimo)? medida(i).valor(): minimo;
			maximo = (medida(i).valor() > maximo)? medida(i).valor(): maximo;
		}

		return maximo - minimo;
	}

	/**
	 * Calcula y retorna el periodo de la señal.
	 * Se calcula como la diferencia entre los dos primeros instantes de tiempo
	 * en que la señal pasa por el valor 0 con pendiente ascendente.
	 * @return periodo de la senhal.
	 */
	public double calculaPeriodo() {
		int indexIni = 0;
		int fst_cero = puntoCrucePorCeroAscendente(indexIni);
		int scnd_cero = puntoCrucePorCeroAscendente(indexIni + medidas.length/10);
		double periodo = Math.abs(medida(fst_cero).tiempo() - medida(scnd_cero).tiempo());
		
		return periodo;        
	}

	/**
	 * Calcula y retorna la fase inicial.
	 * Corresponde al instante del primer cruce por cero con pendiente
	 * ascendente multiplicado por 2*PI y dividido entre el periodo.
	 * @return fase de la senhal.
	 */
	public double calculaFase() {
		int fst_cero = puntoCrucePorCeroAscendente(0);
		double fase = medida(fst_cero).tiempo()*2*Math.PI/calculaPeriodo();
		
		return fase;
	}
	
	/**
	 * Busca el primer punto de cruce por cero con pendiente ascendente en la
	 * senhal a partir de la medida con el indice indicado.
	 * @param indexIni indice de la medida desde la que comenzar la busqueda.
	 * @return el indice de la medida en la que se produce el cruce por cero
	 * o -1 si no lo encuentra.
	 */
	private int puntoCrucePorCeroAscendente(int indexIni) {
		int cero = -1;
		for (int i = indexIni; i < numMedidas(); i++) {
			if (i > 0) {
				if (Math.signum(medida(i - 1).valor()) == -1.0 & Math.signum(medida(i).valor()) == 1.0) {
					cero = i;
					break;
				}
			}
		}


		return cero; // no encontrado
	}

	/**
	 * Realiza el filtrado de las medidas, sustituyendo cada medida por la
	 * media de los valores de las medidas anterior y posterior.
	 * Los valores primero y ultimo se dejarán como estaban.
	 */
	public void filtraSenal() {
		int numMedidas = medidas.length;
		Medida[] filtradas = new Medida[numMedidas];
		filtradas[0] = medidas[0];
		filtradas[numMedidas - 1] = medidas[numMedidas - 1];

		for (int i = 1; i < numMedidas - 1; i++) {
			double valorFiltrado =
					(medidas[i - 1].valor() + medidas[i + 1].valor()) / 2;
			filtradas[i] = new Medida(medidas[i].tiempo(), valorFiltrado);
		}
		medidas = filtradas;
	}

	/**
	 * Realiza el numero de filtrados indicado (llamadas al metodo filtraSenal)
	 * para que el filtrado sea apreciable.
	 * @param numVeces numero de veces que se llama al metodo filtraSenal.
	 */
	public void filtraSenal(int numVeces) {
		for (int i = 0; i < numVeces; i++) {
			filtraSenal();
		}
	}

}
