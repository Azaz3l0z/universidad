package pract04.modelo;

/**
 * Equipo de baloncesto.
 * 
 * @author Metodos de Programacion (UC) y Yago Hernandez garcia
 * @version sep-21
 *
 */
public class Equipo {
	private Jugador[] jugadores;
	/**
	 * Construye un equipo. Los jugadores comienzan con 0 puntos y
	 * 0 asistencias.
	 */
	public Equipo() {
		this.jugadores = new Jugador[15];
		for (int k = 0; k < jugadores.length; k++) {
			this.jugadores[k] = new Jugador(k);
		}
	}
	
	/**
	 * Suma puntos anotados al jugador con el dorsal indicado.
	 * @param dorsal dorsal del jugador que ha anotado los puntos.
	 * @param puntos numero de puntos anotados.
	 * @return verdadero si el dorsal es correcto (es un numero entre 0 y el
	 * numero de jugadores del equipo menos 1) y falso en caso contrario.
	 */
	public boolean sumaPuntosAnotados(int dorsal, int puntos) {
		if (dorsal < 0 | dorsal > jugadores.length - 1) {
			return false;
		} else {
			jugadores[dorsal].sumaPuntos(puntos);
			return true;
		}
	}
	
	/**
	 * Suma una asistencia al jugador con el dorsal indicado.
	 * @param dorsal dorsal del jugador al que sumar la asistencia.
	 * @return verdadero si el dorsal es correcto (es un numero entre 0 y el
	 * numero de jugadores del equipo menos 1) y falso en caso contrario.
	 */
	public boolean sumaAsistencia(int dorsal) {
		if (dorsal < 0 | dorsal > jugadores.length - 1) {
			return false;
		} else {
			jugadores[dorsal].sumaAsistencia();
			return true;
		}
	}
	
	/**
	 * Retorna los puntos anotados por el equipo.
	 * @return puntos anotados por el equipo.
	 */
	public int puntos() {
		int puntos_equipo = 0;
		for (Jugador jugador : jugadores) {
			puntos_equipo += jugador.puntos();
		}
		return puntos_equipo;
	}
	
	/**
	 * Busca el mejor jugador (el de mayor valoracion).
	 * @return mejor jugador del equipo.
	 */
	public Jugador mejorJugador() {
		int dorsal_mejor_jugador = 0;
		for (int k = 0; k < jugadores.length; k++) {
			if (jugadores[k].valoracion() > jugadores[dorsal_mejor_jugador].valoracion()) {
				dorsal_mejor_jugador = k;
			}
		}
		return jugadores[dorsal_mejor_jugador];
	}
	
}
