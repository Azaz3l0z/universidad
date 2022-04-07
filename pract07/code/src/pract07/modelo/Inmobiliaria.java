package pract07.modelo;

import java.util.ArrayList;

/**
 * Inmobiliaria que gestiona una bolsa de pisos en venta y 
 * cuenta con una plantilla de agentes inmobiliarios.
 * 
 * @author  Metodos de Programacion (UC) y Yago Hernandez Garcia
 * @version mar-22
 */
public class Inmobiliaria {
	private ArrayList<Piso> pisos = new ArrayList<Piso>();
	private ArrayList<Agente> agentes = new ArrayList<Agente>();

	/**
	 * Resultado de error de los metodos.
	 */
	public enum ResultadoError { NO_ERROR, DNI_AGENTE_YA_EXISTENTE,
		REFERENCIA_PISO_YA_EXISTENTE, DNI_AGENTE_INCORRECTO, 
		REFERENCIA_PISO_INCORRECTA, PISO_YA_ASIGNADO }	

	/**
	 * Anhade un nuevo agente que comienza a trabajar para la inmobiliaria.
	 * @param agente Nuevo agente
	 * @return NO_ERROR si no se ha producido ningun error y se ha podido
	 * anhadir el agente o
	 * DNI_AGENTE_YA_EXISTENTE si se trata de anhadir un agente con
	 * el mismo DNI que otro ya registrado.
	 */
	public ResultadoError anhadeAgente(Agente agente) {
		Agente agenteEnLista = buscaAgente(agente.dni());
		if (agenteEnLista == null) {
			agentes.add(agente);
			return ResultadoError.NO_ERROR;
		} else {
			return ResultadoError.DNI_AGENTE_YA_EXISTENTE;
		}
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
		Piso pisoEnLista = buscaPiso(piso.referencia());
		if (pisoEnLista == null) {
			pisos.add(piso);
			return ResultadoError.NO_ERROR;
		} else {
			return ResultadoError.REFERENCIA_PISO_YA_EXISTENTE;
		}
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
		Agente agente = buscaAgente(dni);
		Piso piso = buscaPiso(ref);

		if (agente == null) {
			return ResultadoError.DNI_AGENTE_INCORRECTO;
		} else if (piso == null) {
			return ResultadoError.REFERENCIA_PISO_INCORRECTA;
		} else if (piso.agente() != null) {
			return ResultadoError.PISO_YA_ASIGNADO;
		}

		piso.asignaAgente(agente);		
		return ResultadoError.NO_ERROR;
	}


	/**
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
		} else if (piso.agente() == null) {
			return null;
		}

		pisos.remove(piso);
		Agente agente = piso.agente();
		agente.anhadeVenta();
		return agente;
	}

	/**
	 * Retorna un agente dado su DNI.
	 * @param dni DNI del agente buscado
	 * @return El agente cuyo DNI se pasa como parametro
	 *         null si no existe ningun agente con dicho DNI
	 */
	public Agente buscaAgente(String dni) {
		for (Agente a : agentes) {
			if (dni.equals(a.dni())) {
				return a;			
			}
		}
		return null;
	}

	/**
	 * Retorna un piso dado su referencia.
	 * @param referencia del piso buscado
	 * @return El piso cuya referencia se pasa como parametro
	 *         null si no existe ningun piso con dicha referencia
	 */
	public Piso buscaPiso(String referencia) {
		for (Piso p : pisos) {
			if (referencia.equals(p.referencia())) {
				return p;			
			}
		}
		return null;
	}

}
