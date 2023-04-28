package practica;

public class Personaje {
	private int id_personaje;
	private String nombre;
	private Avatar avatar;
	private Arma arma;
	private Poder poder;

	public Personaje(String nombre, Avatar avatar, Arma arma, Poder poder) {
		this.nombre = nombre;
		this.avatar = avatar;
		this.arma = arma;
		this.poder = poder;
	}
	public Personaje(int id_personaje, String nombre, Avatar avatar, Arma arma, Poder poder) {
		this.id_personaje = id_personaje;
		this.nombre = nombre;
		this.avatar = avatar;
		this.arma = arma;
		this.poder = poder;
	}

	public int getId_personaje() {
		return id_personaje;
	}

	public void setId_personaje(int id_personaje) {
		this.id_personaje = id_personaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}

	@Override
	public String toString() {
		String explicacion1 = "Tu personaje se llama " + nombre + ", con " + avatar.getVida() + " pt de vida, "
				+ "con un arma " + arma.getNombre() + " sumas " + arma.getDano()
				+ " al daño que te hagan sea positivo o negativo, \n";
		String explicacion2 = "";
		if (poder.getDano_defensa() > 0) {
			explicacion2 = "posees " + poder.getNombre() + ", este poder te aporta " + poder.getDano_defensa()
					+ " de vida en cada ronda.";
		} else {
			explicacion2 = "posees " + poder.getNombre() + ", este poder resta " + poder.getDano_defensa()
					+ " del daño que te hagan.";
		}

		return explicacion1 + explicacion2;
	}
}
