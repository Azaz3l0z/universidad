package tests;

import modelo.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TESTEmpresaCamiones {
	
	@Test
	void cargarPaquete() {
		Gestor gestor = new Gestor();
		Gestor.ResultadoError error;
		String descripcion = "Television 4k";
		int camionCarga = 1;
		
		error = gestor.cargarPaquete(descripcion, camionCarga);
		assertEquals(error, Gestor.ResultadoError.NO_ERROR);
		error = gestor.cargarPaquete(descripcion, -1);
		assertEquals(error, Gestor.ResultadoError.CAMION_NO_EXISTE);
		
	}
	
	@Test
	void entregarPaquete() {
		Gestor gestor = new Gestor();
		Gestor.ResultadoError error;
		String descripcion = "Television 4k";
		int camionCarga = 1;
		gestor.cargarPaquete(descripcion, camionCarga);
		
		error = gestor.entregarPaquete("PAQ1", 1);
		assertEquals(error, Gestor.ResultadoError.NO_ERROR);
		error = gestor.entregarPaquete("PAQ1", 1);
		assertEquals(error, Gestor.ResultadoError.PAQUETE_NO_EN_CAMION);
		error = gestor.entregarPaquete("PAQ1", -1);
		assertEquals(error, Gestor.ResultadoError.CAMION_NO_EXISTE);
	}
	
	@Test
	void buscarPaquete() {
		Gestor gestor = new Gestor();
		Gestor.ResultadoError error;
		String descripcion = "Television 4k";
		int camionCarga = 1;
		gestor.cargarPaquete(descripcion, camionCarga);
		
		int id = gestor.buscarCamionConPaquete("PAQ1");
		System.out.println(id);
		assertEquals(id, camionCarga);
		id = gestor.buscarCamionConPaquete("PAQ2");
		assertEquals(id, -1);
		
	}
	
}
