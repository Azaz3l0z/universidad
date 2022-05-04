package pract09.modelo;

import java.util.ArrayList;

/**
 * Inmobiliaria que gestiona una bolsa de pisos en venta y 
 * cuenta con una plantilla de agentes inmobiliarios.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */
public class Inmobiliaria {

	private ArrayList<Piso> bolsaPisos = new ArrayList<Piso>();
	private ArrayList<Agente> agentes = new ArrayList<Agente>();
	
	/**
	 * Resultado de error de los metodos.
	 * TODO: este enumerado desaparcece en la version modificada
	 */
	public enum ResultadoError { NO_ERROR, DNI_AGENTE_YA_EXISTENTE,
		REFERENCIA_PISO_YA_EXISTENTE, DNI_AGENTE_INCORRECTO, 
		REFERENCIA_PISO_INCORRECTA, PISO_YA_ASIGNADO }	

	/**
	 * Lanzada cuando la referencia no corresponde a ninguno de los pisos de la
	 * inmobiliaria.
	 */
	@SuppressWarnings("serial")
	public static class ReferenciaPisoIncorrecta extends RuntimeException {
	}
	
	/**
	 * Lanzada cuando se trata de vender un piso no asignado.
	 */
	@SuppressWarnings("serial")
	public static class PisoNoAsignado extends RuntimeException {
    }
	
	// TODO: crear otras excepciones:
	// DniAgenteYaExistente
	// ReferenciaPisoYaExistente
	// DniAgenteIncorrecto
	// PisoYaAsignado
	
	// TODO: modifica los metodos anhadeAgente, anhadePiso y asignaPisoAgente
	// para que utilicen excepciones como mecanismo de notificacion de errores
	// (Ver metodo vendePiso2 y ejemplo de la diapositiva 27 de los apuntes de
	// teoria)
	
	/**
	 * Anhade un nuevo agente que comienza a trabajar para la inmobiliaria.
	 * @param agente Nuevo agente
	 * @return NO_ERROR si no se ha producido ningun error y se ha podido
	 * anhadir el agente o
	 * DNI_AGENTE_YA_EXISTENTE si se trata de anhadir un agente con
	 * el mismo DNI que otro ya registrado.
	 */
	public ResultadoError anhadeAgente(Agente agente) {
		if (buscaAgente(agente.dni()) != null) {
			return ResultadoError.DNI_AGENTE_YA_EXISTENTE;
		}

		agentes.add(agente);
		return ResultadoError.NO_ERROR;
	}

	/**
	 * Anhade un nuevo piso a ser gestionado por la inmobiliaria.
	 * @param piso piso a anhadir a la inmobiliaria.
	 * @return NO_ERROR si no se ha producido ningun error y se ha podido
	 * anhadir el piso o
	 * REFERENCIA_PISO_YA_EXISTENTE si se trata de anhadir un piso con
	 * la misma referencia que otro ya registrado.
	 */
	public ResultadoError anhadePiso(Piso piso) {
		if (buscaPiso(piso.referencia()) != null) {
			return ResultadoError.REFERENCIA_PISO_YA_EXISTENTE;
		}
		bolsaPisos.add(piso);
		return ResultadoError.NO_ERROR;
	}
	
	/**
	 * Asigna el piso cuya referencia se pasa como parametro al agente cuyo DNI 
	 * se pasa tambien como parametro.
	 * @param dni DNI del agente a quien se quiere asignar el piso.
	 * @param ref Referencia del piso que se quiere asignar.
	 * @return NO_ERROR si no se ha producido ningun error o
	 * REFERENCIA_PISO_INCORRECTA si no existe ningun piso con la
	 * referencia indicada.
	 * PISO_YA_ASIGNADO si el piso ya esta asignado a otro agente.
	 * DNI_AGENTE_INCORRECTO si no existe ningun agente con el DNI
	 * indicado.
	 */
	public ResultadoError asignaPisoAgente(String dni, String ref) {
		Piso piso = buscaPiso(ref);
		if (piso == null) {
			return ResultadoError.REFERENCIA_PISO_INCORRECTA;
		}		
		if (piso.estaAsignado()) {
			return ResultadoError.PISO_YA_ASIGNADO;
		}
		Agente agente = buscaAgente(dni);
		if (agente == null) {
			return ResultadoError.DNI_AGENTE_INCORRECTO;
		}
		
		piso.asignaAgente(agente);
		return ResultadoError.NO_ERROR;
	}

	/**
	 * TODO: este metodo se sustituye por vendePiso2 (ver mas abajo)
	 * Actualiza la informacion por la venta de un piso. 
	 * El piso es eliminado de la lista de pisos a la venta y 
	 * se le suma la venta al agente.
	 * @param ref Referencia del piso que se ha vendido
	 * @return agente que estaba asignado al piso o null si no existe piso con
	 * la referencia o si el piso no esta asignado a ningun agente.
	 */
	public Agente vendePiso(String ref) {
		Piso piso = buscaPiso(ref);
		if (piso == null) {
			return null;
		}		
		if (!piso.estaAsignado()) {
			return null;
		}
		Agente agenteAsignado = piso.agenteAsignado();
		agenteAsignado.anhadeVenta();
		bolsaPisos.remove(piso);
		return agenteAsignado;
	}
	
	/**
	 * Actualiza la informacion por la venta de un piso. 
	 * El piso es eliminado de la lista de pisos a la venta y 
	 * se le suma la venta al agente.
	 * @param ref Referencia del piso que se ha vendido
	 * @return agente que estaba asignado al piso.
	 * @throws ReferenciaPisoIncorrecta si no existe piso con la referencia
	 * indicada.
	 * @throws PisoNoAsignado si el piso no esta asignado a ningun agente.
	 */
	public Agente vendePiso2(String ref) 
			throws ReferenciaPisoIncorrecta, PisoNoAsignado {
		Piso piso = buscaPiso(ref);
		if (piso == null) {
			throw new ReferenciaPisoIncorrecta();
		}		
		if (!piso.estaAsignado()) {
			throw new PisoNoAsignado();
		}
		Agente agenteAsignado = piso.agenteAsignado();
		agenteAsignado.anhadeVenta();
		bolsaPisos.remove(piso);
		return agenteAsignado;
	}
	
	/**
	 * Retorna un agente dado su DNI.
	 * @param dni DNI del agente buscado
	 * @return El agente cuyo DNI se pasa como parametro
	 *         null si no existe ningun agente con dicho DNI
	 */
	public Agente buscaAgente(String dni) {
		for (Agente a : agentes) {
			if (a.dni().equals(dni)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Retorna un piso de la bolsa de pisos dada su referencia.
	 * @param ref Referencia del piso buscado
	 * @return El piso cuya referencia se pasa como parametro
	 *         null si no existe ningun piso con dicha referencia
	 */
	private Piso buscaPiso(String ref) {
		for (Piso p : bolsaPisos) {
			if (p.referencia().equals(ref)) {
				return p;
			}
		}
		return null;
	}
}
