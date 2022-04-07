package pract01.modelo;

import pract01.modelo.Cliente.TipoTarifa;

/**
 * Distribuidora electrica.
 * @author Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version feb-2022
 */
public class DistribuidoraElectrica {
	// Clientes de la distribuidora
	private Cliente[] clientes;

	/**
	 * Construye una distribuidora.
	 */
	public DistribuidoraElectrica() {
		// Crea los clientes. En una aplicacion real los datos de los clientes
		// se obtendrian de un fichero o de una base de datos.
		// Por simplicidad suponemos que la distribuidora tiene solo 3 clientes
		clientes = new Cliente[3];
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(i, TipoTarifa.TARIFA_T1);
		}
	}

	/**
	 * Retorna el consumo del cliente con el id proporcionado correspondiente a
	 * la hora y el dia indicados.
	 * @param id identificador del cliente
	 * @param hora hora del dia en el rango 0..23
	 * @param dia dia del mes en el rango 1..30
	 * @return el consumo correspondiente a la hora y el dia indicados o NaN si
	 * se ha producido un error.
	 */
	public float consumoCliente(int id, int dia, int hora) {
		Cliente cliente = buscaCliente(id);
		if (cliente == null) {
			return Float.NaN; // error
		}
		
		return cliente.consumo(dia, hora);
	}

	/**
	 * Regista el consumo correspondiente a la hora y el dia indicados al
	 * cliente con el id proporcionado.
	 * @param id identificador del cliente
	 * @param hora hora del dia en el rango 0..23
	 * @param dia dia del mes en el rango 1..30
	 * @param consumo kWh consumidos en la hora y el dia indicados.
	 * @return 0 si se ha podido registrar el dato, 1 en caso de error.
	 */
	public int registraConsumoCliente(int id, int dia, int hora,
			float consumo) {
		Cliente cliente = buscaCliente(id);
		if (cliente == null) {
			return -1; // error
		}
		
		cliente.registraConsumo(dia, hora, consumo);
		return 0;
	}	

	/**
	 * Retorna el importe mensual en euros que debe abonar el cliente con el id
	 * proporcionado con la tarifa que tiene asignada.
	 * @param id identificador del cliente.
	 * @return el importe mensual en euros que debe abonar el cliente o NaN si
	 * se ha producido un error.
	 */
	public float obtenImporteMensual(int id) {
		Cliente cliente = buscaCliente(id);
		if (cliente == null) {
			return Float.NaN; // error
		}
		
		return cliente.obtenImporte();
	}

	/**
	 * Retorna la tarifa mas ventajosa para el cliente con el id indicado. Esto
	 * es, retorna la tarifa con la que el cliente abonaría un precio menor.
	 * @param id identificador del cliente
	 * @return la tarifa mas ventajosa para el cliente o null si se ha
	 * producido algun error.
	 */
	public Cliente.TipoTarifa obtenMejorTarifa(int id) {
		Cliente cliente = buscaCliente(id);
		if (cliente == null) {
			return null; // error
		}
		
		// Creamos variables utiles para almacenar la informacion original del cliente
		float importe_tarifa_original = cliente.obtenImporte();
		TipoTarifa tarifa_original = cliente.tarifa();
		
		// Obtenemos la tarifa complementaria a la actual y se la asignamos al cliente
		TipoTarifa tarifa_complementaria = 
				(tarifa_original == TipoTarifa.TARIFA_T1)? TipoTarifa.TARIFA_T2 : TipoTarifa.TARIFA_T1;
		cliente.asignaTarifa(tarifa_complementaria);
		
		// Calculamos la mejor tarifa comparando los importes mediante un operador terniario
		TipoTarifa mejor_tarifa = 
				(cliente.obtenImporte() > importe_tarifa_original)? tarifa_original : tarifa_complementaria;
		
		// Devolvemos al cliente a su tarifa original
		cliente.asignaTarifa(tarifa_original);
		
		return mejor_tarifa;
	}
	
	/**
	 * Retorna el cliente con el id indicado.
	 * @param id identificador del cliente busccado.
	 * @return el cliente con el id indicado o null si el id no corresponde
	 * a ningun cliente.
	 */
	private Cliente buscaCliente(int id) {
		if (id < 0 || id >= clientes.length) {
			return null;
		}
		return clientes[id];
	}

}
