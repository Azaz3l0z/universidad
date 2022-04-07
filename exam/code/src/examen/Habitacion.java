package examen;

import java.util.ArrayList;

/**
 * Habitacion del hotel.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class Habitacion {
	private final int numero;
	private boolean libre;
	private ArrayList<Estancia> registro;
	
	/**
	 * Construye la habitacion.
	 * @param numero numero de la habitacion.
	 */
	public Habitacion(int numero) {
		this.numero = numero;
		this.libre = true;
		this.registro = new ArrayList<Estancia>();
	}

	/**
	 * Retorna el numero de la habitacion.
	 * @return el numero de la habitacion.
	 */
	public int numero() {
		return numero;
	}
	
	/**
	 * Retorna true si la habitacion esta libre o false si no.
	 * @return true si la habitacion esta libre o false si no.
	 */
	public boolean libre() {
		return libre;
	}
	
	/**
	 * Retorna el tamanho del registro de estancias.
	 * @return el tamanho del registro de estancias.
	 */
	public int sizeRegistro() {
		return registro.size();
	}

	/**
	 * Retorna la estancia en la posicion n del registro de estancias.
	 * @return la estancia en la posicion n del registro de estancias.
	 */
	public Estancia estancia(int n_registro){
		return registro.get(n_registro);
	}
	
	/**
	 * Inicia la una nueva estancia para dos DNIs y ocupa la habitacion
	 */
	public void iniciaEstancia(String dni1, String dni2) {
		registro.add(new Estancia(dni1, dni2));
		this.libre = false;
	}
	
	/**
	 *  Finaliza la estancia quee este activa en ese momento y fija
	 *  el tiempo total de la estancia (anhadido personal).
	 */
	public void finalizaEstancia() {
		this.libre = true;
		registro.get(registro.size() - 1).fijarTiempo();
	}
}
