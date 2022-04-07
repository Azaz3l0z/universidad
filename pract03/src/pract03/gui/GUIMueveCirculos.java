package pract03.gui;

import pract03.modelo.Circulo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
/**
 * Programa principal que permite dibujar y mover circulos
 * en una ventana.
 * 
 * @author Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version feb-2021
 */
public class GUIMueveCirculos {
	// dimensiones de la ventana
	private static final int ANCHO_VENTANA_PIXELS = 400;
	private static final int ALTO_VENTANA_PIXELS = 500;

	// intervalo de refresco (en segundos)
	private static final int FPS = 30;
	private static final double INTERVALO_REFRESCO = (double)1/FPS;

	/**
	 * Programa principal.
	 * 
	 * @param args argumentos del main (no usados).
	 */
	public static void main(String[] args) {
		// crea la ventana de dibujo.
		VentanaCirculos ventanaCirculos =
				new VentanaCirculos(ANCHO_VENTANA_PIXELS, ALTO_VENTANA_PIXELS);

		// Variables utiles
		int n_circulos = 3;
		int max_r = 40;
		Circulo[] circulos = new Circulo[n_circulos];
		Color[] colores = {Color.black, Color.red, Color.green, Color.blue, Color.pink};

		int randRadius[] = new int[n_circulos];
		double randXs[] = new double[n_circulos];
		double randYs[] = new double[n_circulos];

		// crea un array de radios aleatorios
		for (int k = 0; k < n_circulos; k++) {
			randRadius[k] = ThreadLocalRandom.current().nextInt(20, max_r);
		}

		// crea circulos aleatorios que no se solapan
		for (int k = 0; k < n_circulos; k++) {
			boolean overlap = true;
			double randX = 0;
			double randY = 0;
			while (overlap) {
				overlap = false;
				randX = (double)ThreadLocalRandom.current().nextInt(randRadius[k], 
						ANCHO_VENTANA_PIXELS-(int)randRadius[k]);
				randY = (double)ThreadLocalRandom.current().nextInt(randRadius[k], 
						ANCHO_VENTANA_PIXELS-(int)randRadius[k]);

				for (int i = 0; i < n_circulos; i++) {
					double dist = Math.hypot(randX-randXs[i], randY-randYs[i]);
					if (dist > (double)(randRadius[k] + randRadius[i])) {
						overlap = false;
					} else {
						overlap = true;
						break;
					}
				}
			}
			randXs[k] = randX;
			randYs[k] = randY;	
		}

		for (int k = 0; k < n_circulos; k++) {
			int randColor = ThreadLocalRandom.current().nextInt(0, colores.length);
			double randVelX = (double)ThreadLocalRandom.current().nextInt(-40, 40);
			double randVelY = (double)ThreadLocalRandom.current().nextInt(-40, 40);
			circulos[k] = new Circulo(randRadius[k], randXs[k], randYs[k], colores[randColor]);
			circulos[k].asignaVelocidad(randVelX, randVelY);
		}


		for (Circulo c : circulos) {
			ventanaCirculos.anhadeCirculo(c);
		}
		double duration = 20;
		double t = 0;
		
		// borramos los archivos .png
		for (File file: new File("resources/images").listFiles()) {
			if (!file.isDirectory()) 
		        file.delete();
		}

		while(t < duration) {
			// Movemos los circulos
			ventanaCirculos.redibuja(INTERVALO_REFRESCO);
			t += INTERVALO_REFRESCO;

			// Creamos pequeñas capturas de la imagen y las guardamos para mas tarde hacer un gif
			BufferedImage img = new BufferedImage(ventanaCirculos.getWidth(), 
					ventanaCirculos.getHeight(), BufferedImage.TYPE_INT_RGB);
			ventanaCirculos.paint(img.getGraphics());
			File outputfile = new File("resources/images/"+String.valueOf(t)+".png");

			try {
				ImageIO.write(img, "png", outputfile);
			}catch (Exception e) {

			}			
		}
		// Creamos el gif en el escritorio
		String command = String.join(File.separator, System.getProperty("user.dir"),
				"resources", "python", "gif_maker.sh") 
				+ " " + System.getProperty("user.home") + "/Desktop" + " " + String.valueOf(FPS);
		System.out.println(command);
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.getStackTrace();
		}
		
	}

}
