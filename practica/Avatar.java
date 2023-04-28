package practica;

public class Avatar {
		private int id_avatar;
		private String nombre;
		private int vida;
		
		public Avatar(int id_avatar, String nombre, int vida) {
			this.id_avatar = id_avatar;
			this.nombre = nombre;
			this.vida = vida;
		}
		public Avatar (String nombre, int vida) {
			this.nombre = nombre;
			this.vida = vida;
		}
		public int getId_avatar() {
			return id_avatar;
		}
		public void setId_avatar(int id_avatar) {
			this.id_avatar = id_avatar;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getVida() {
			return vida;
		}
		public void setVida(int vida) {
			this.vida = vida;
		}
		@Override
		public String toString() {
			return "Nombre=" + nombre + ", Vida=" + vida;
		}
		
}
