package pract06.modelo;

import java.util.ArrayList;

/**
 * Tarjeta de descuento.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */

public class Tarjeta {
	private static int ultimoCodigo;
	private int puntos;
	private ArrayList<Compra> compras = new ArrayList<Compra>();
	private String dni;
	private String codigo;

	/**
	 * Construye una tarjeta.
	 * @param dni DNI de la tarjeta a crear.
	 */
	public Tarjeta(String dni) {
		this.dni = dni;
		this.codigo = "TAR" + String.valueOf(ultimoCodigo);
		ultimoCodigo++;
	}

	/**
	 * Retorna el DNI del propietario de la tarjeta.
	 * @return el DNI del propietario de la tarjeta.
	 */
	public String dni() {
		return dni;
	}

	/**
	 * Retorna el codigo de la tarjeta.
	 * @return el codigo de la tarjeta.
	 */
	public String codigo() {
		return codigo;
	}

	/**
	 * Retorna los puntos de descuento acumulados.
	 * @return los puntos de descuento acumulados.
	 */
	public int puntosAcumulados() {
		return puntos;
	}

	/**
	 * Registra una compra en la tarjeta acumulando los puntos que
	 * correspondan en funcion de su importe.
	 * @param compra compra a registrar.
	 */
	public void registraCompra(Compra compra) {
		this.compras.add(compra);
		// Anhade los puntos correspondientes haciendo uso de la transformacion de
		// double a int usando (int), que redondea hacia abajo de forma natural
		this.puntos += (int) compra.importe() / 10;
	}

	/**
	 * Retorna la compra que ocupa en el historico la posicion indicada.
	 * @param posCompra posicion en el historico de la compra buscada.
	 * @return la compra buscada o null si la posicion no corresponde a ninguna
	 * compra
	 */
	public Compra compraEnPos(int posCompra) {
		if (posCompra < 0 | posCompra >= compras.size()) {
			return null;
		} else {
			Compra compra = compras.get(posCompra);
			return compra;
		}
	}


}
