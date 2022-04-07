package pract01.modelo;

/**
 * Cliente de una distribuidora electrica.
 * @author Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version feb-2022
 */
public class Cliente {
	// constantes
	private static final int DIAS = 30;
	private static final int HORAS = 24;
	private static final float EUROS_POR_KWH_VALLE_T1 = 0.07F; 
	private static final float EUROS_POR_KWH_PUNTA_T1 = 0.14F;
	private static final float EUROS_POR_KWH_T2 = 0.1F;
	private static final int HORA_INI_PUNTA = 11;
	private static final int HORA_END_PUNTA = 21;

	/**
	 *  Tipos de tarifas.
	 */
	public static enum TipoTarifa { TARIFA_T1, TARIFA_T2 }

	// identificador del cliente
	private final int id;
	// consumos del cliente
	private float[][] consumos = new float[DIAS][HORAS];
	// tarifa del cliente
	private TipoTarifa tarifa;

	/**
	 * Construye un cliente con el identificador indicado.
	 * Inicialmente el cliente tiene sus consumos a 0.
	 * @param id identificador del cliente a crear.
	 * @param tarifa tarifa del cliente. 
	 */
	public Cliente(int id, TipoTarifa tarifa) {
		this.id = id;
		this.tarifa = tarifa;
	}

	/**
	 * Retorna el identificador del cliente.
	 * @return el identificador del cliente.
	 */
	public int id() {
		return id;
	}

	/**
	 * Retorna la tarifa del cliente.
	 * @return la tarifa del cliente.
	 */
	public TipoTarifa tarifa() {
		return tarifa;
	}
	
	/**
	 * Cambia la tarifa del cliente.
	 * @param tarifa nueva tarifa asignada.
	 */
	public void asignaTarifa(TipoTarifa tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * Retorna el consumo del cliente correspondiente a la hora y el dia
	 * indicados.
	 * @param hora hora del dia en el rango 0..23
	 * @param dia dia del mes en el rango 1..30
	 * @return el consumo correspondiente a la hora y el dia indicados.
	 */
	public float consumo(int dia, int hora) {
		return consumos[dia - 1][hora];
	}

	/**
	 * Registra al cliente el consumo correspondiente a la hora y el dia
	 * indicados.
	 * @param hora hora del dia en el rango 0..23
	 * @param dia dia del mes en el rango 1..30
	 * @param consumo kWh consumidos en la hora y el dia indicados.
	 */
	public void registraConsumo(int dia, int hora, float consumo) {
		consumos[dia - 1][hora] = consumo;
	}	

	/**
	 * Retorna el importe en euros que debe abonar un cliente con la tarifa
	 * que tiene asignada.
	 * @return el importe en euros que debe abonar un cliente con la tarifa
	 * indicada.
	 */
	public float obtenImporte() {
		float importe_total = 0.0f;
		
		for (int i = 0; i < consumos.length; i++) {
			for (int k = 0; k < consumos[i].length; k++) {
				if (consumos[i][k] != 0) {
					switch (tarifa()) {
						case TARIFA_T1:
							if (k >= HORA_INI_PUNTA && k <= HORA_END_PUNTA) {
								importe_total += consumos[i][k]*EUROS_POR_KWH_PUNTA_T1;
							} else {
								importe_total += consumos[i][k]*EUROS_POR_KWH_VALLE_T1;
							}
							break;
						case TARIFA_T2:
							importe_total += consumos[i][k]*EUROS_POR_KWH_T2;
							break;
					}
				}
			}
		}
		return importe_total;
	}

}
