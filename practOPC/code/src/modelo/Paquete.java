package modelo;

/**
 * Paquete transportado por el camion
 * @author Yago Hernandez
 *
 */
public class Paquete {
	// Atributos del paquete
	private final String codigo;
	private static int ultimoCodigo;
	private static final String PREFIJO_PAQUETE = "PAQ";
	
	private String descripcion;
	
	/**
	 * Crea un paquete con la descripcion que se de	y le asigna un
	 * codigo
	 * @param descripcion Descripcion de los contenidos del paquete
	 */
	public Paquete(String descripcion) {
		ultimoCodigo++;
		this.codigo = PREFIJO_PAQUETE + ultimoCodigo;
		this.descripcion = descripcion;
	}
	
	/**
	 * Retorna el codigo del paquete
	 * @return el codigo del paquete
	 */
	public String codigo() {
		return codigo;
	}
	
	/**
	 * Retorna la descripcion del paquete
	 * @return la descripcion del paquete
	 */
	public String descripcion() {
		return descripcion;
	}
}
