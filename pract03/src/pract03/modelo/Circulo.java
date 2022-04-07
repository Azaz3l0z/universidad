package pract03.modelo;
import java.awt.Color;

/**
 * Circulo con las coordenadas de su centro y su radio.
 * 
 * @author Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version feb-2021
 */
public class Circulo {
	// radio del circulo.
	private final int radio;
	
	// coordenada x del centro del circulo.
	private double centroX;
	
	// coordenada y del centro del circulo.
	private double centroY;
	
	// color del circulo
	private final Color color;
	
	// velocidades del circulo
	private double velocidadX;
	private double velocidadY;
	
	/**
	 * Construye un circulo.
	 * @param radio radio del circulo.
	 * @param centroX coordenada x del centro del circulo.
	 * @param centroY coordenada y del centro del circulo.
	 */
	public Circulo(int radio, double centroX, double centroY, Color color) {
		this.radio = radio;
		this.centroX = centroX;
		this.centroY = centroY;
		this.color = color;
	}

	/**
	 * Retorna el radio del circulo.
	 * @return el radio del circulo.
	 */
	public int radio() {
		return radio;
	}
	
	/**
	 * Retorna la coordenada x del centro del circulo.
	 * @return la coordenada x del centro del circulo.
	 */
	public double centroX() {
		return centroX;
	}

	/**
	 * Retorna la coordenada y del centro del circulo.
	 * @return la coordenada y del centro del circulo.
	 */
	public double centroY() {
		return centroY;
	}
	
	public Color color() {
		return color;
	}
	
	public double velocidadX() {
		return velocidadX;
	}
	
	public double velocidadY() {
		return velocidadY;
	}
	
	/**
	 * Asigna la velocidad en X e Y al circulo
	 */
	public void asignaVelocidad(double velocidadX, double velocidadY) {
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
	}
	
	public void mueve(double time) {
		this.centroX += this.velocidadX*time;
		this.centroY += this.velocidadY*time;
	}
}
