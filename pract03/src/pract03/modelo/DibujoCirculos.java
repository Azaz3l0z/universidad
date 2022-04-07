package pract03.modelo;

import java.util.*;

/**
 * Dibujo formado por varios circulos que se muestra en una ventana.
 * 
 * @author Metodos de Programacion (UC) y <TODO: nombre alumno>.
 * @version feb-2021
 */
public class DibujoCirculos {
	// ancho de la ventana en la que se mostrara el dibujo.
	private final int anchoVentanaPixels;

	// alto de la ventana en la que se mostrara el dibujo.
	private final int altoVentanaPixels;

	// maximo numero de circulos que puede contener el dibujo
	private static final int MAX_NUM_CIRCULOS = 20;

	// numero de circulos que contiene el dibujo
	private int numCirculos = 0;  // inicialmente vale 0

	// array con los circulos a dibujar. Tiene una capacidad maxima
	// de MAX_NUM_CIRCULOS, pero las posiciones ocupadas son las que
	// estan en el rango [0 .. numCirculos-1]
	private Circulo[] circulos = new Circulo[MAX_NUM_CIRCULOS];

	/**
	 * Construye un dibujo para mostrar en una ventana
	 * con las dimensiones indicadas.
	 * @param anchoVentanaPixels ancho de la ventana en pixels.
	 * @param altoVentanaPixels alto de la ventana en pixels.
	 */
	public DibujoCirculos(int anchoVentanaPixels, int altoVentanaPixels) {
		this.anchoVentanaPixels = anchoVentanaPixels;
		this.altoVentanaPixels = altoVentanaPixels;
	}

	/**
	 * Anhade un circulo al dibujo.
	 * @param circulo circulo a anhadir.
	 * 
	 * XXX por simplicidad no comprueba si se ha llenado el
	 * array (ya veremos a lo largo de la asignatura como se
	 * deben notificar los errores).
	 */
	public void anhadeCirculo(Circulo circulo) {
		circulos[numCirculos] = circulo;
		numCirculos++;
	}


	/**
	 * Retorna una copia de los circulos del dibujo.
	 * @return una copia de los circulos del dibujo.
	 */
	public Circulo[] circulos() {
		return Arrays.copyOf(circulos, numCirculos);
	}

	/**
	 * Mueve los circulos del dibujo.
	 * (Apartados 3 y 4)
	 * @param intervaloTiempo intervalo de tiempo en segundos durante el que se
	 * realiza el movimiento.
	 */
	public void mueveCirculos(double intervaloTiempo) {
		for (int k = 0; k < circulos().length; k++) {
			// Comprobamos si se choca con las paredes
			Circulo c1 = circulos()[k];
			if (c1.centroX()+c1.radio() > anchoVentanaPixels | c1.centroX()-c1.radio() < 0){
				c1.asignaVelocidad(-c1.velocidadX(), c1.velocidadY());
			}
			if (c1.centroY()+c1.radio() > altoVentanaPixels | c1.centroY()-c1.radio() < 0){
				c1.asignaVelocidad(c1.velocidadX(), -c1.velocidadY());
			}
			
			// Comprobamos si se choca con otras bolas
			for (int i = 0; i < circulos().length; i++) {
				Circulo c2 = circulos()[i];
				if (c1 != c2) {
					// Aplicamos un algoritmo Sweep and prune para ser mas eficientes
					if (c1.centroX()+c1.radio() > c2.centroX()-c2.radio() |
						c1.centroX()-c1.radio() < c2.centroX()+c2.radio()) {
						// Calculamos si se estan chocando
						double dist = Math.hypot(c1.centroX()-c2.centroX(), c1.centroY()-c2.centroY());
						if (dist < c1.radio()+c2.radio()) {
							// Calculamos las nuevas velocidades aplicando la conservacion del
							// momento lineal
							double nvX = c2.centroX() - c1.centroX();
	                        double nvY = c2.centroY() - c1.centroY();
	                        double unvX = nvX / Math.sqrt(nvX * nvX + nvY * nvY);
	                        double unvY = nvY / Math.sqrt(nvX * nvX + nvY * nvY);
	                        double utvX = unvY * -1;
	                        double utvY = unvX;

	                        double v1n = unvX * c1.velocidadX() + unvY * c1.velocidadY();
	                        double v1t = utvX * c1.velocidadX() + utvY * c1.velocidadY();
	                        double v2n = unvY * c2.velocidadY() + unvX * c2.velocidadX();
	                        double v2t = utvX * c2.velocidadX() + utvY * c2.velocidadY();

	                        double v1nPrime = (v1n * (1 - 1) + 2. * 1 * v2n) / (1 + 1);
	                        double v2nPrime = (v2n * (1 - 1) + 2. * 1 * v1n) / (1 + 1);

	                        double v1tPrime = v1t;
	                        double v2tPrime = v2t;

	                        double finalXVel = v1nPrime * unvX + v1tPrime * utvX;
	                        double finalYVel = v1nPrime * unvY + v1tPrime * utvY;
	                        
							c1.asignaVelocidad(finalXVel, finalYVel);
							c2.asignaVelocidad(v2nPrime * unvX + v2tPrime * utvX, v2nPrime * unvY + v2tPrime * utvY);
							
							
						}
					}
				}	
			}

			c1.mueve(intervaloTiempo);
		}
	}
}
