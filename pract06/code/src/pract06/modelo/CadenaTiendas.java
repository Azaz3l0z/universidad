package pract06.modelo;

import java.util.ArrayList;

/**
 * Cadena de tiendas con tarjetas de descuento.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class CadenaTiendas {
	private ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

	/**
	 * Anhade una tarjeta.
	 * @param tarjeta tarjeta a anhadir.
	 */
	public void anhadeTarjeta(Tarjeta tarjeta) {
		tarjetas.add(tarjeta);		
	}

	/**
	 * Registra una compra para la tarjeta con el codigo indicado.
	 * @param codTarjeta codigo de la tarjeta a la que asignar la compra.
	 * @param compra compra realizada.
	 * @return true si la compra ha sido asignada con exito y false si no
	 * existe ninguna tarjeta con ese codigo.
	 */
	public boolean registraCompra(String codTarjeta, Compra compra) {
		Tarjeta tarjeta = buscaTarjeta(codTarjeta);
		if (tarjeta == null) {
			return false;
		} else {
			tarjeta.registraCompra(compra);
			return true;
		}
	}

	/**
	 * Busca la tarjeta con el codigo indicado.
	 * @param codTarjeta codigo de la tarjeta buscada.
	 * @return la tarjeta con el codigo indicado o null si no existe ninguna
	 * tarjeta con ese codigo.
	 */
	public Tarjeta buscaTarjeta(String codTarjeta) {
		// Integer index = busquedaBinaria(tarjetas, codTarjeta);
		//
		// La linea anterior es una implementacion que he hecho de forma personal
		// del algoritmo de busqueda binaria para optimizar la velocidad de busqueda
		// de las tarjetas (ya que por definicion estan ordenadas)
		Integer index = null;
		
		for (int k = 0; k < tarjetas.size(); k++) {
			if (codTarjeta.equals(tarjetas.get(k).codigo())) {
				index = k;
			}
		}

		if (index == null) {
			return null;
		} else {
			return tarjetas.get(index);
		}

	}

	// Metodo de buqueda Binaria para optimizar la velocidad del programa
	// referencia: https://www.geeksforgeeks.org/binary-search-a-string/
	/**
	 * Realiza una busqueda binaria para todas las tarjetas en una lista de tarjetas.
	 * @param tarjetas lista de tarjetas
	 * @param x tarjeta a encontrar
	 * @return indice de la tarjeta si la encuentra, null si no.
	 */
	static Integer busquedaBinaria(ArrayList<Tarjeta> tarjetas, String x) {
		int inicio = 0;
		int fin = tarjetas.size() - 1;

		while (inicio <= fin) {
			// Punto medio
			int puntoMedio = inicio + (fin - inicio) / 2;

			// Usamos el metodo compareTo para ver si la tarjeta que buscamos esta
			// antes del punto medio o no
			int resultado = x.compareTo(tarjetas.get(puntoMedio).codigo());

			// Si x esta en el punto medio
			if (resultado == 0) {
				return puntoMedio;
			} else if (resultado > 0) {    // Si x es mayor, ignoramos la mitad izquierda
				inicio = puntoMedio + 1;
			} else { 					   // Si x es menor, ignoramos la mitad derecha
				fin = puntoMedio - 1;
			}
		}

		return null;
	}

	/**
	 * Retorna la compra que ocupa la posicion indicada en la tarjeta
	 * correspondiente al codigo pasado como parametro.
	 * @param codTarjeta codigo de la tarjeta en la que buscar la compra.
	 * @param posCompra posicion de la compra en el historico de compras de
	 * la tarjeta.
	 * @return la compra buscada o null si no existe ninguna tarjeta con el codigo
	 * indicado o si la posicion no corresponde a ninguna compra.
	 */
	public Compra buscaCompraDeTarjeta(String codTarjeta, int posCompra) {
		Tarjeta tarjeta = buscaTarjeta(codTarjeta);

		if (tarjeta == null) {
			return null;
		} else if (tarjeta.compraEnPos(posCompra) == null) {
			return null;
		} else {
			return tarjeta.compraEnPos(posCompra);
		}
	}

}
