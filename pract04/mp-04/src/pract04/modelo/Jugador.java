package pract04.modelo;


/**
 * Jugadores del equipo de baloncesto con sus estadísticas en el partido:
 * puntos y asistencias.
 * 
 * @author Metodos de Programacion (UC) y Yago Hernandez garcia
 * @version mar-21
 */
public class Jugador {
	private final int dorsal;
	private int puntos;
	private int asistencias;

	/**
	 * Crea un jugador con el dorsal indicado.
	 * El jugador comienza con 0 puntos y 0 asistencias.
	 * @param dorsal dorsal del jugador
	 */
	public Jugador(int dorsal) {
		this.dorsal = dorsal;
	}

	/**
	 * Suma los puntos indicados al jugador.
	 * @param puntos puntos anotados.
	 */
	public void sumaPuntos(int puntos) {
		this.puntos += puntos;
	}

	/**
	 * Suma una asistencia al jugador.
	 */
	public void sumaAsistencia() {
		this.asistencias += 1;
	}

	/**
	 * Retorna los puntos anotados por el jugador.
	 * @return puntos anotados
	 */
	public int puntos() {
		return puntos;
	}

	/**
	 * Retorna las asistencias realizadas por el jugador.
	 * @return asistencias realizadas.
	 */
	public int asistencias() {
		return asistencias;
	}

	/**
	 * Retorna la valoracion del jugador.
	 * @return valoracion del jugador.
	 */
	public double valoracion() {
		double valoracion = puntos() + asistencias()*1.5;
		return valoracion;
	}

	/**
	 * Retorna el dorsal del jugador.
	 * @return dorsal del jugador.
	 */	
	public int dorsal() {
		return dorsal;
	}

}
