package examen;

/**
 * Hotel con un numero fijo de habitaciones.
 * 
 * @author  Metodos de Programacion (UC)y Yago Hernandez Garcia
 * @version mar-22
 */
public class Hotel {
	// atributos
	private final int numero_habitaciones = 8;
	private Habitacion[] habitaciones;

	// codigos de error
	public static final int NO_ERROR = 0;
	public static final int ERROR_HOTEL_COMPLETO = -1;
	public static final int ERROR_NUM_HABITACION_INCORRECTO = -2;
	public static final int ERROR_HABITACION_NO_OCUPADA = -3;

	/**
	 * Construye el hotel.
	 */
	public Hotel() {
		this.habitaciones = new Habitacion[numero_habitaciones];
		for (int k = 0; k < numero_habitaciones; k++) {
			this.habitaciones[k] = new Habitacion(k);
		}
	}

	/**
	 * Aloja los huespedes con los DNIs indicados en una de las habitaciones
	 * libres del hotel.
	 * @param dniHuesped1 DNI de uno de los huespedes.
	 * @param dniHuesped2 DNI de otro de los huespedes.
	 * @return el numero de la habitacion asignada o ERROR_HOTEL_COMPLETO si
	 * todas las habitaciones del hotel estan ocupadas.
	 */
	public int alojaHuespedes(String dniHuesped1, String dniHuesped2) {
		for (Habitacion hab : habitaciones) {
			if (hab.libre()) {
				hab.iniciaEstancia(dniHuesped1, dniHuesped2);
				return hab.numero();
			}
		}

		return ERROR_HOTEL_COMPLETO;
	}

	/**
	 * Finaliza el alojamiento en la habitacion indicada. La habitacion pasa a
	 * estar libre.
	 * @param numHabitacion numero de la habitacion que finaliza el alojamiento.
	 * @return NO_ERROR si la operacion se ha realizado con exito,
	 * ERROR_NUM_HABITACION_INCORRECTO si el numero no corresponde a ninguna de
	 * las habitaciones del hotel o ERROR_HABITACION_NO_OCUPADA si la habitacion
	 * indicada no se encuentra ocupada en este momento.
	 */
	public int finalizaAlojamiento(int numHabitacion) {
		try {
			Habitacion hab = habitaciones[numHabitacion];
			if (!hab.libre()) {
				hab.finalizaEstancia();
				return NO_ERROR;
			} else {
				return ERROR_HABITACION_NO_OCUPADA;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return ERROR_NUM_HABITACION_INCORRECTO;
		}
	}

	/**
	 * Retorna la estancia que ocupa la posicion indicada en el historico de
	 * estancias de la habitacion.
	 * @param numHabitacion numero de la habitacion en la que buscar la estancia
	 * @param posEstacia posicion de la estancia en el historico de estancias de
	 * la habitacion.
	 * @return la estancia buscada o null si no existe ninguna habitacion con el
	 * numero indicado o si la posicion no corresponde a ninguna estancia.
	 */
	public Estancia estanciaEnHabitacion(int numHabitacion, int posEstancia) {
te		try {
			Estancia estancia = habitaciones[numHabitacion].estancia(posEstancia);
			return estancia;
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (java.lang.IndexOutOfBoundsException e) {
			return null;
		}
		
	}

	/**
	 * Retorna el numero de estancias realizadas por un huesped en el hotel.
	 * @param dniHuesped DNI del huesped del que se quiere conocer el numero
	 * de estancias.
	 * @return el numero de estancias realizadas por un huesped en el hotel.
	 */
	public int numEstanciasDeHuesped(String dniHuesped) {
		int n_estancias = 0;
		// Recorre todas las habitaciones
		for (Habitacion hab : habitaciones) {
			// Recorre cada registro de cada habitacion
			for (int k = 0; k < hab.sizeRegistro(); k++) {
				for (String dni : hab.estancia(k).dnis()) {
					// Si encuentra el dni suma 1 
					if (dni.equals(dniHuesped)) {
						n_estancias += 1;
					}
				}
			}
		}
		return n_estancias;
	}
}
