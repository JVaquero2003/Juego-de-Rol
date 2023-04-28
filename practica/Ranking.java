package practica;

public class Ranking {
	private int id_ranking;
	private Personaje personaje;
	private int rondas;

	public Ranking(int id_ranking, Personaje personaje, int rondas) {
		this.id_ranking = id_ranking;
		this.personaje = personaje;
		this.rondas = rondas;
	}

	public int getId_ranking() {
		return id_ranking;
	}

	public void setId_ranking(int id_ranking) {
		this.id_ranking = id_ranking;
	}

	public Personaje getId_personaje() {
		return personaje;
	}

	public void setId_personaje(Personaje id_personaje) {
		this.personaje = id_personaje;
	}

	public int getRondas() {
		return rondas;
	}

	public void setRondas(int rondas) {
		this.rondas = rondas;
	}

	@Override
	public String toString() {
		return "Nombre: " + personaje.getNombre() + ", ronda: " + rondas;
	}

	public int compareTo(Ranking r) {
		if (this.rondas > r.getRondas()) {
			return -1;
		} else if (this.rondas < r.getRondas()) {
			return 1;
		} else {
			return 0;
		}
	}
}
