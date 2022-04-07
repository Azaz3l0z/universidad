package pract06.modelo;

/**
 * Compra realizada en una cadena de tiendas.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class Compra {
	private String producto;
	private double importe;

	/**
	 * Crea una compra.
	 * @param producto Nombre del producto
	 * @param importe Valor del importe del producto
	 */
	public Compra(String producto, double importe) {
		this.producto = producto;
		this.importe = importe;
	}
	
	/**
	 * Retorna el nombre del producto para determinada compra.
	 * @return el nombre del producto para determinada compra
	 */
	public String producto() {
		return producto;
	}
	
	/**
	 * Retorna el importe del producto para determinada compra.
	 * @return el importe del producto para determinada compra
	 */
	public double importe() {
		return importe;
	}
}
