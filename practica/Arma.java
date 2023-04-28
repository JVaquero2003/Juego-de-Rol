package practica;

public class Arma {
	private int id_arma;
	private String nombre;
	private int dano;

	public Arma (int id_arma, String nombre, int dano) {
		this.id_arma = id_arma;
		this.nombre = nombre;
		this.dano = dano;
	}
	
	public Arma (String nombre, int dano) {
		this.nombre = nombre;
		this.dano = dano;
	}

	public int getId_arma() {
		return id_arma;
	}

	public void setId_arma(int id_arma) {
		this.id_arma = id_arma;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	@Override
	public String toString() {
		return "Nombre= " + nombre + ", Daño= " + dano;
	}
	
}
