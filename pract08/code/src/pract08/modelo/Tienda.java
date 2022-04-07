package pract08.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tienda {
	public enum ResultadoError { NO_ERROR, VENDEDOR_YA_EXISTENTE,
		VENDEDOR_NO_EXISTENTE, DNI_AGENTE_INCORRECTO, 
		REFERENCIA_PISO_INCORRECTA, PISO_YA_ASIGNADO }
	
	private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	
	public ResultadoError anhadeVendedor (Vendedor vendedor) {
		Vendedor vendedorEnLista = buscaVendedor(vendedor.nombre());
		if (vendedorEnLista != null) {
			return ResultadoError.VENDEDOR_YA_EXISTENTE;
		}
		vendedores.add(vendedor);
		return ResultadoError.NO_ERROR;
	}
	
	public ResultadoError nuevaVenta (String nombre, double importe) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			return ResultadoError.VENDEDOR_NO_EXISTENTE;
		}
		vendedor.anhadeComision(importe);
		
		return ResultadoError.NO_ERROR;
	}
	
	public int ranking (String nombre) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor != null) {
			vendedores.sort(Comparator.comparingDouble(v1 -> v1.comisiones()));
			Collections.reverse(vendedores);
			return vendedores.indexOf(vendedor) + 1;
		}
		return -1;
	}
	
	public ResultadoError eliminaVendedor (String nombre) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			return ResultadoError.VENDEDOR_NO_EXISTENTE;
		}
		vendedores.remove(vendedor);
		return ResultadoError.NO_ERROR;
	}
	
	public boolean errorToBoolean (ResultadoError error) {
		if (error == ResultadoError.NO_ERROR) {
			return false;
		}
		return true;
	}
	
	public Vendedor buscaVendedor(String nombre) {
		for (Vendedor v : vendedores) {
			if (v.nombre().equals(nombre)) {
				return v;
			}
		}
		return null;
	}
}
