package examen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test de la clase Transbordador.
 * 
 * @author  Metodos de Programacion (UC)
 * @version oct-21
 */
class PruebaHotelSimple {

	public String[] creaDnis(int n) {
		String dni = "ABC";
		String[] dnis = new String[n];
		for (int k = 0; k < n; k++) {
			dnis[k] = dni+k;
		}

		return dnis;

	}

	@Test
	void profesorTest() {
		Hotel hotel = new Hotel();
		String[] dnis = creaDnis(3);
		int numHabitacion = 0;
		int numEstancias = 5;
		int estanciasHuesped;

		for (int k = 0; k < numEstancias; k++) {
			numHabitacion = hotel.alojaHuespedes(dnis[0], dnis[1]);
			hotel.finalizaAlojamiento(numHabitacion);
		}

		// Muestra los datos de la estancia
		Estancia estancia = hotel.estanciaEnHabitacion(numHabitacion, 0);
		System.out.println("Huespedes de la estancia:" + estancia.dniHuesped1() +
				" y " + estancia.dniHuesped2());

		// Muestra el numero de veces que se han alojado los huespedes
		estanciasHuesped = hotel.numEstanciasDeHuesped(dnis[0]);
		assertEquals(numEstancias, estanciasHuesped);
		estanciasHuesped = hotel.numEstanciasDeHuesped(dnis[1]);
		assertEquals(numEstancias, estanciasHuesped);
		estanciasHuesped = hotel.numEstanciasDeHuesped(dnis[2]);	
		assertEquals(0, estanciasHuesped);	
	}

	@Test
	void hotelCompletoTest() {
		Hotel hotel = new Hotel();
		String[] dnis = creaDnis(17);
		int numHabitacion;
		for (int k = 0; k < dnis.length/2; k++) {
			numHabitacion = hotel.alojaHuespedes(dnis[k], dnis[2*k]);
		}

		numHabitacion = hotel.alojaHuespedes(dnis[dnis.length-2], dnis[dnis.length-1]);
		assertEquals(numHabitacion, -1);
	}

	@Test
	void estanciaErrorTest() {
		Hotel hotel = new Hotel();
		String[] dnis = creaDnis(2);

		int numHabitacion = 0;
		for (int k = 0; k < dnis.length/2; k++) {
			numHabitacion = hotel.alojaHuespedes(dnis[k], dnis[2*k]);
		}
		hotel.finalizaAlojamiento(numHabitacion);
		Estancia estancia = hotel.estanciaEnHabitacion(numHabitacion, 10);
		assertEquals(estancia, null);
	}
	
	@Test
	void finalizaAlojamient() {
		Hotel hotel = new Hotel();
		String[] dnis = creaDnis(2);

		int numHabitacion = 0;
		int finAlojamiento = 0;
		for (int k = 0; k < dnis.length/2; k++) {
			numHabitacion = hotel.alojaHuespedes(dnis[k], dnis[2*k]);
		}
		
		// No error
		finAlojamiento = hotel.finalizaAlojamiento(numHabitacion);
		assertEquals(finAlojamiento, 0);
		// Habitacion no ocupada
		finAlojamiento = hotel.finalizaAlojamiento(3);
		assertEquals(finAlojamiento, -3);
		// Habitacion no existente
		finAlojamiento = hotel.finalizaAlojamiento(30);
		assertEquals(finAlojamiento, -2);
		
	}

	@Test
	void tiempoEstanciasTest() {
		Hotel hotel = new Hotel();
		String[] dnis = creaDnis(2);

		int numHabitacion = 0;
		long minutos = 30; // Tomaremos 1 milisegundo como 1 minuto como prueba
		for (int k = 0; k < dnis.length/2; k++) {
			numHabitacion = hotel.alojaHuespedes(dnis[k], dnis[2*k]);
		}

		try {
			Thread.sleep(minutos);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		hotel.finalizaAlojamiento(numHabitacion);

		Estancia estancia = hotel.estanciaEnHabitacion(numHabitacion, 0);
		System.out.println("Los huespedes estuvieron " + String.valueOf(estancia.tiempo() / 60) + "h.");


	}





}