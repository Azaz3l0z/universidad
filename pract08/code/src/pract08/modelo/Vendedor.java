package pract08.modelo;

public class Vendedor {
	private final String nombre;
	private final double comision = 0.05;
	private double comisiones;	
	
	public Vendedor(String nombre) {
		this.nombre = nombre;
	}
	
	public String nombre() {
		return nombre;
	}
	
	public double comisiones() {
		return comisiones;
	}
	
	public void anhadeComision(double venta) {
		this.comisiones += venta*comision;
	}
}
