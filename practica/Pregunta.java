package practica;

public class Pregunta {
	private int id_pregunta;
	private String pregunta;
	private String desencadenante1;
	private String desencadenante2;
	private int respuesta1;
	private int respuesta2;
	
	public Pregunta (int id_pregunta, String pregunta, String desencadenante1, String desencadenante2,
			int respuesta1, int respuesta2) {
		this.id_pregunta = id_pregunta;
		this.pregunta = pregunta;
		this.desencadenante1 = desencadenante1;
		this.desencadenante2 = desencadenante2;
		this.respuesta1 = respuesta1;
		this.respuesta2 = respuesta2;
	}
	public Pregunta (String pregunta, String desencadenante1, String desencadenante2,
			int respuesta1, int respuesta2) {
		this.pregunta = pregunta;
		this.desencadenante1 = desencadenante1;
		this.desencadenante2 = desencadenante2;
		this.respuesta1 = respuesta1;
		this.respuesta2 = respuesta2;
	}

	public int getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(int id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getDesencadenante1() {
		return desencadenante1;
	}

	public void setDesencadenante1(String desencadenante1) {
		this.desencadenante1 = desencadenante1;
	}

	public String getDesencadenante2() {
		return desencadenante2;
	}

	public void setDesencadenante2(String desencadenante2) {
		this.desencadenante2 = desencadenante2;
	}

	public int getRespuesta1() {
		return respuesta1;
	}

	public void setRespuesta1(int respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public int getRespuesta2() {
		return respuesta2;
	}

	public void setRespuesta2(int respuesta2) {
		this.respuesta2 = respuesta2;
	}
	
}
