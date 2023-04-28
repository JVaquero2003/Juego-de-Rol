package practica;

public class Poder {
	private int id_poder;
	private String nombre;
	private int dano_defensa;
	
	public Poder (int id_poder, String nombre, int dano_defensa) {
		this.id_poder = id_poder;
		this.nombre = nombre;
		this.dano_defensa = dano_defensa;
	}
	
	public Poder (String nombre, int dano_defensa) {
		this.nombre = nombre;
		this.dano_defensa = dano_defensa;
	}

	public int getId_poder() {
		return id_poder;
	}

	public void setId_poder(int id_poder) {
		this.id_poder = id_poder;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDano_defensa() {
		return dano_defensa;
	}

	public void setDano_defensa(int dano_defensa) {
		this.dano_defensa = dano_defensa;
	}

	@Override
	public String toString() {
		return "Nombre= " + nombre + ", Daño o Defensa= " + dano_defensa;
	}
	
}
