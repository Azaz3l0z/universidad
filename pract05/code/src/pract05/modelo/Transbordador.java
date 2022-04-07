package pract05.modelo;

/**
 * Barco transbordador de vehiculos.
 * 
 * @author Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-21
 */
public class Transbordador {
	// atributos
	private final double pesoMaxSoportado;
	private final int maxCapacidadVehiculos;
	private Vehiculo[] vehiculos;
	private int numVehiculos;
	private double pesoVehiculos;

	// codigos de error retornados por el metodo cargaVehiculo()
	public static final double SUPERA_PESO_MAX = -1;
	public static final double SUPERA_NUM_MAX_VEHICULOS = -2;
	public static final double VEHICULO_YA_EN_TRANSBORDADOR = -3;

	/**
	 * Construye un transbordador que es capaz de transportar el peso maximo y el
	 * numero de vehiculos indicado.
	 * El transbordador comienza vacio.
	 * @param pesoMaxSoportado peso maximo soportado por el transbordador
	 * @param maxCapacidadVehiculos maximo numero de vehiculos que es capaz de
	 * transportar el transbordador
	 */
	public Transbordador(double pesoMaxSoportado, int maxCapacidadVehiculos) {
		this.pesoMaxSoportado = pesoMaxSoportado;
		this.maxCapacidadVehiculos = maxCapacidadVehiculos;
		this.vehiculos = new Vehiculo[maxCapacidadVehiculos];
	}

	/**
	 * Carga un vehiculo en el transbordador (siempre que no se supere el
	 * peso maximo o el numero maximo de vehiculos).
	 * @param vehiculo vehiculo a cargar
	 * @return precio a cobrar al vehiculo o SUPERA_PESO_MAX si se supera el
	 * peso maximo, SUPERA_NUM_MAX_VEHICULOS si se supera el numero maximo de
	 * vehiculos o VEHICULO_YA_EN_TRANSBORDADOR si ya hay en el transbordador
	 * otro vehiculo con la misma matricula que el que se pretende anhadir.
	 */
	public double cargaVehiculo(Vehiculo vehiculo) {
		boolean matriculaRepetida = (buscaVehiculo(vehiculo.matricula()) == null) ? false : true;
		if (vehiculo.peso() + pesoVehiculos > pesoMaxSoportado) {
			return SUPERA_PESO_MAX;

		} else if (numVehiculos >= maxCapacidadVehiculos) {
			return SUPERA_NUM_MAX_VEHICULOS;
			
		} else if (matriculaRepetida) {
			return VEHICULO_YA_EN_TRANSBORDADOR;

		} else {
			vehiculos[numVehiculos] = vehiculo;
			numVehiculos += 1;
			pesoVehiculos += vehiculo.peso();
			return vehiculo.numOcupantes() * 1.2 + vehiculo.peso() * 0.003;
		}
	}

	/**
	 * Busca un vehiculo con el numero de ocupantes indicado y con un peso igual
	 * o superior al peso minimo indicado.
	 * @param numOcupantes numero de ocupantes del vehiculo buscado
	 * @param pesoMinimo peso minimo del vehiculo buscado
	 * @return un vehiculo cargado en el transbordador que tenga el numero de ocupantes
	 * indicado y un peso igual o mayor que el peso minimo indicado. Retorna null si no
	 * hay ningun vehiculo que cumpla las condiciones indicadas
	 */
	public Vehiculo buscaVehiculoConCaracteristicas(int numOcupantes, double pesoMinimo) {
		for (int k = 0; k < numVehiculos; k++) {
			if (numOcupantes == vehiculos[k].numOcupantes() & 
					vehiculos[k].peso() >= pesoMinimo) {
				return vehiculos[k];
			}
		}
		return null;
	}

	/**
	 * Vacia el transbordador de los vehiculos que transportaba.
	 */
	public void vaciaTransbordador() {
		for (int k = 0; k < numVehiculos; k++) {
			vehiculos[k] = null;
		}
		pesoVehiculos = 0;
		numVehiculos = 0;
	}

	/**
	 * Busca el vehiculo con la matricula indicada.
	 * @param matricula matricula del vehiculo a buscar.
	 * @return vehiculo con la matricula indicada o null en el caso de que
	 * no haya ningun vehiculo con esa matricula.
	 */
	private Vehiculo buscaVehiculo(String matricula) {
		for (int k = 0; k < numVehiculos; k++) {
			if (vehiculos[k].matricula().equals(matricula)) {
				return vehiculos[k];
			}
		}

		return null;
	}
}
