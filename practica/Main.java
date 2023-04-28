package practica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * He creado todos estos objetos estaticos porque permite el acceso a métodos y variables de clase 
 * sin la necesidad de instanciar un objeto de dicha clase, 
 * permitiendo la inicialización de forma cómoda y durante la carga de clase.
 * @author Jose Alfonso Vaquero Padilla
 *
 */
public class Main {
	static Scanner entrada = new Scanner(System.in);
	static MySQLAccess conexion = new MySQLAccess();
	static ArrayList<Avatar> avatares = new ArrayList<Avatar>();
	static ArrayList<Arma> armas = new ArrayList<Arma>();
	static ArrayList<Poder> poderes = new ArrayList<Poder>();
	static ArrayList<Personaje> personajes = new ArrayList<Personaje>();
	static ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	static ArrayList<Ranking> ranking = new ArrayList<Ranking>();
	
	/**
	 * Pre: ---
	 * Post: Este metodo principal permite que el usuario elija entre
	 * las cuatro opciones del programa.
	 */
	public static void main(String[] args) {
		System.out.println("¡Bienvenido a Halo!");
		while (true) {
			mostrarMenu();
			obtenerDatosBD();
			try {
				int respuesta = entrada.nextInt();
				if (respuesta == 1) {
					mostrarRanking();
				} else if (respuesta == 2) {
					nuevasIdeas();
				} else if (respuesta == 3) {
					jugar();
				} else if (respuesta == 4) {
					break;
				} else {
					System.out.println("Introduce una opcion correcta, ¡por favor!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra al usuario las opciones a elegir.
	 */
	private static void mostrarMenu() {
		System.out.println("\nElige una de las siguientes opciones:");
		System.out.println("Ver ranking --> 1");
		System.out.println("Insertar nuevas ideas --> 2");
		System.out.println("Jugar --> 3");
		System.out.println("Salir del juego --> 4\n");
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo obtiene de la BD todos los datos necesarios
	 * para poder jugar o ver el ranking.
	 */
	private static void obtenerDatosBD() {		
		try {
			obtenerAvatares();
			obtenerArmas();
			obtenerPoderes();
			obtenerPersonaje();
			obtenerPreguntas();
			obtenerRanking();
		} catch (Exception e) {}	
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Avatares guardados en la BD, usar
	 * los datos para crear objetos de tipo Avatar y guardarlos en un vector.
	 */
	private static void obtenerAvatares() throws Exception {
		avatares.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from avatar");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int id_avatar = rs.getInt(1);
			String nombre = rs.getString(2);
			int vida = rs.getInt(3);
			avatares.add(new Avatar(id_avatar, nombre, vida));
		}
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer las Armas guardadas en la BD, usar
	 * los datos para crear objetos de tipo Arma y guardarlos en un vector.
	 */
	private static void obtenerArmas() throws Exception {
		armas.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from arma");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int id_arma = rs.getInt(1);
			String nombre = rs.getString(2);
			int dano = rs.getInt(3);
			armas.add(new Arma(id_arma, nombre, dano));
		}
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Poderes guardados en la BD, usar
	 * los datos para crear objetos de tipo Poder y guardarlos en un vector.
	 */
	private static void obtenerPoderes() throws Exception {
		poderes.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from poder");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int id_poder = rs.getInt(1);
			String nombre = rs.getString(2);
			int dano_defensa = rs.getInt(3);
			poderes.add(new Poder(id_poder, nombre, dano_defensa));
		}
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer los Characteres guardados en la BD, usar
	 * los datos para crear objetos de tipo Character y guardarlos en un vector.
	 */
	private static void obtenerPersonaje() throws Exception {
		personajes.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from personaje");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int id_personaje = rs.getInt(1);
			String nombre = rs.getString(2);
			int id_avatar = rs.getInt(3);
			int id_arma = rs.getInt(4);
			int id_poder = rs.getInt(5);
			Avatar avatar = findAvatarById(id_avatar);
			Arma arma = findArmaById(id_arma);
			Poder poder = findPoderById(id_poder);
			Personaje personaje = new Personaje(id_personaje, nombre, avatar, arma, poder);
			personajes.add(personaje);		
		}
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Avatar en un vector
	 * segun su identificador pasado por parametro. 
	 */
	//vector: conjunto de datos de un mismo tipo.
	private static Avatar findAvatarById(int id_avatar) {		
		for(int i = 0; i<avatares.size(); i++) {
			if(avatares.get(i).getId_avatar() == id_avatar) {
				return avatares.get(i);
			}
		}
		return null;		
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Arma en un vector
	 * segun su identificador pasado por parametro. 
	 */
	//vector: conjunto de datos de un mismo tipo.
	private static Arma findArmaById(int id_arma) {
		for(int i = 0; i < avatares.size(); i++) {
			if(armas.get(i).getId_arma() == id_arma) {
				return armas.get(i);
			}
		}	
		return null;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Poder en un vector
	 * segun su identificador pasado por parametro. 
	 */
	//vector: conjunto de datos de un mismo tipo.
	private static Poder findPoderById(int id_poder) {
		for(int i = 0; i < poderes.size(); i++) {
			if(poderes.get(i).getId_poder() == id_poder) {
				return poderes.get(i);
			}
		}	
		return null;	
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer las Preguntas guardadas en la BD, usar
	 * los datos para crear objetos de tipo Pregunta y guardarlos en un vector.
	 */
	//vector: conjunto de datos de un mismo tipo.
	private static void obtenerPreguntas() throws Exception {
		preguntas.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from pregunta");
		ResultSet l = preparedStatement.executeQuery();
		while (l.next()) {
			int id_pregunta = l.getInt(1);
			String pregunta = l.getString(2);
			String desencadenante1 = l.getString(3);
			String desencadenante2 = l.getString(4);
			int respuesta1 = l.getInt(5);
			int respuesta2 = l.getInt(6);
			Pregunta pregunta1 = new Pregunta(id_pregunta, pregunta, desencadenante1, desencadenante2, 
					respuesta1, respuesta2);
			preguntas.add(pregunta1);
		}
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo sirve para leer el Ranking guardado en la BD, usar
	 * los datos para crear objetos de tipo Ranking y guardarlos en un vector.
	 */
	//vector: conjunto de datos de un mismo tipo.
	private static void obtenerRanking() throws Exception {
		ranking.clear();
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect().prepareStatement
				("select * from ranking order by rondas desc limit 10");
		ResultSet l = preparedStatement.executeQuery();
		while (l.next()) {
			int id_ranking = l.getInt(1);
			int id_personaje = l.getInt(2);
			int rondas = l.getInt(3);
			/*
			 * Dentro de cada objeto de tipo Ranking se almacenara el objeto
			 * de tipo Personaje que haya obtenido dicho record. Para ello
			 * lo buscamos segun su identificador.
			 */
			Personaje personaje = findPersonajeById(id_personaje);
			ranking.add(new Ranking(id_ranking, personaje, rondas));
		}
		conexion.cerrarBD();	
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo encuentra un objeto de tipo Personaje en un vector
	 * segun su identificador pasado por parametro. 
	 */
	private static Personaje findPersonajeById(int id_personaje) {
		for(int i = 0; i<personajes.size(); i++) {
			if(personajes.get(i).getId_personaje() == id_personaje) {
				return personajes.get(i);
			}
		}	
		return null;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo muestra por pantalla como maximo los 10 mejores
	 * registros del ranking del juego. 
	 */
	private static void mostrarRanking() {	
		for(int i = 0; i < ranking.size(); i++) {
			System.out.println("Posicion " + (i + 1) + " - " + ranking.get(i).toString());
		}
	}

	/**
	 * Pre: ---
	 * Post: Este metodo pregunta al usuario que nueva idea quiere introducir
	 * y llama a los metodos correspondientes para recoger los datos y luego
	 * almacenarlos correctamente en la BD.
	 */
	private static void nuevasIdeas() {
		System.out.println("Insertar Avatar --> 1");
		System.out.println("Insertar Arma --> 2");
		System.out.println("Insertar Poder --> 3");
		System.out.println("Insertar Pregunta --> 4");
		try {
			int respuesta = entrada.nextInt();
			if (respuesta == 1) {
				Avatar avatar = datosAvatar();
				insertarAvatar(avatar);
				System.out.println("El avatar " + avatar.getNombre() + " ha sido insertado.");
			} else if (respuesta == 2) {
				Arma arma = datosArma();
				insertarArma(arma);
				System.out.println("El arma " + arma.getNombre() + " ha sido insertada.");
			} else if (respuesta == 3) {
				Poder poder = datosPoder();
				insertarPoder(poder);
				System.out.println("El poder " + poder.getNombre() + " ha sido insertado.");
			} else if (respuesta == 4) {
				Pregunta pregunta = pedirDatosPregunta();
				insertarPregunta(pregunta);
				System.out.println("La pregunta con texto: '" + pregunta.getPregunta() + "' ha sido insertada.");
			}
			else {
				System.out.println("¡Introduce una opcion correcta, ¡por favor!");
			}
		} catch (InputMismatchException e) {
			System.out.println("Introduce un numero correcto, ¡por favor!");
		} catch (Exception e) { e.printStackTrace();}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Avatar.
	 */
	private static Avatar datosAvatar() {
		entrada.nextLine();
		System.out.println("Introduce el nombre del avatar:");
		String nombre = entrada.nextLine();
		System.out.println("Introduce la vida del avatar:");
		while (true) {
			String vida = entrada.nextLine();
			if (isInteger(vida) && Integer.parseInt (vida) > 0) {
				return new Avatar (nombre, Integer.parseInt(vida));
			} else {
				System.out.println("Introduce un numero correcto, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Avatar y lo almacena en la BD.
	 */
	private static void insertarAvatar(Avatar avatar) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into avatar(nombre, vida) values (?, ?)");
		preparedStatement.setString(1, avatar.getNombre());
		preparedStatement.setInt(2, avatar.getVida());
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Arma.
	 */
	private static Arma datosArma() {
		entrada.nextLine();
		System.out.println("Introduce el nombre del arma:");
		String nombre = entrada.nextLine();
		System.out.println("Introduce el daño del arma (numero entero entre 0 y 100):");
		while (true) {
			String dano = entrada.nextLine();
			if (isInteger(dano) && Integer.parseInt (dano) > 0 && Integer.parseInt(dano) <= 100) {
				return new Arma (nombre,Integer.parseInt(dano));
			} else {
				System.out.println("Introduce un numero correcto, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo comprueba si la palabra pasada como parametro
	 * contiene solo numeros o no.
	 */
	private static boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    } catch(NumberFormatException e){
	        return false;
	    }
	}

	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Arma y lo almacena en la BD.
	 */
	private static void insertarArma(Arma arma) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into arma(nombre, dano) values (?, ?)");
		preparedStatement.setString(1, arma.getNombre());
		preparedStatement.setInt(2, arma.getDano());
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Poder.
	 */
	private static Poder datosPoder() {
		entrada.nextLine();
		System.out.println("Introduce el nombre del poder:");
		String nombre = entrada.nextLine();
		System.out.println(
				"Introduce el daño (numero entero positivo entre 0 y 200) " 
						+ "o defensa (numero entero negativo hasta -50) del poder:");
		while (true) {
			String dano_defensa = entrada.nextLine();
			if (isInteger(dano_defensa) && Integer.parseInt (dano_defensa) > -50
					&& Integer.parseInt(dano_defensa) < 50) {
				return new Poder (nombre, Integer.parseInt(dano_defensa));
			} else {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}

	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Poder y lo almacena en la BD.
	 */
	private static void insertarPoder(Poder poder) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into poder(nombre, dano_defensa) values (?, ?)");
		preparedStatement.setString(1, poder.getNombre());
		preparedStatement.setInt(2, poder.getDano_defensa());
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de pedir al usuario los atributos necesarios
	 * para poder formar un objeto de tipo Pregunta.
	 */
	private static Pregunta pedirDatosPregunta() {
		entrada.nextLine();
		System.out.println("Introduce el texto de la pregunta: ");
		String pregunta = entrada.nextLine();
		System.out.println("Introduce el texto de la consecuencia 1: ");
		String desencadenante1 = entrada.nextLine();
		System.out.println("Introduce el texto de la consecuencia 2: ");
		String desencadenante2 = entrada.nextLine();
		System.out.println("Introduce el valor de la consecuencia 1: ");
		int respuesta1 = 0;
		boolean respuesta2 = false;
		while (!respuesta2) {
			try {
				respuesta1 = entrada.nextInt();
				respuesta2 = true;
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
		entrada.nextLine();
		System.out.println("Introduce el valor de la consecuencia 2: ");
		while (true) {
			try {
				int desencadenante3 = entrada.nextInt();
				return new Pregunta(pregunta, desencadenante1, desencadenante2, 
						respuesta1, desencadenante3);
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un objeto de tipo Pregunta y lo almacena en la BD.
	 */
	private static void insertarPregunta(Pregunta pregunta) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into pregunta(" + "pregunta, " + "desencadenante1, "
						+ "desencadenante2, respuesta1, " + "respuesta2) ");
		preparedStatement.setString(1, pregunta.getPregunta());
		preparedStatement.setString(2, pregunta.getDesencadenante1());
		preparedStatement.setString(3, pregunta.getDesencadenante2());
		preparedStatement.setInt(4, pregunta.getRespuesta1());
		preparedStatement.setInt(5, pregunta.getRespuesta2());
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se ejecutado cuando el Usuario quiere jugar y se encarga
	 * de iniciar la partida ya sea con un Personaje previamente creado o con la
	 * creacion de uno nuevo. 
	 */
	private static void jugar(){
		if(!personajes.isEmpty()) {
			System.out.println("¿Quieres utilizar un personaje existente? si / no");
			entrada.nextLine();
			while(true) {	
				String respuesta = entrada.nextLine();
				if(respuesta.equalsIgnoreCase("si")) {
					iniciarPartida(elegirPersonaje());
					return;				
				} else if (respuesta.equalsIgnoreCase("no")) {
					break;
				} else {
					System.out.println("Introduce una respuesta correcta");
				}
			}
		}
		iniciarPartida(crearPersonaje());
	}

	/**
	 * Pre: --- 
	 * Post: Este metodo deja escoger al usuario un personaje previamente creado
	 * para jugar su partida.
	 */
	private static Personaje elegirPersonaje() {
		System.out.println("Introduce el numero del personaje que quieras utilizar");
		for(int i = 0; i < personajes.size(); i++) {
			System.out.println("["+i+"] " + personajes.get(i).getNombre());
		}
		int respuesta = entrada.nextInt();
		return personajes.get(respuesta);
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de crear un nuevo objeto de tipo Personaje,
	 * guardarlo correctamente en la BD y de devolverlo para poder iniciar la 
	 * partida utilizandolo.  
	 */
	private static Personaje crearPersonaje() {
		System.out.println("Introduce el nombre de tu nuevo personaje:");
		String nombre = entrada.nextLine();
		System.out.println("Elige el avatar de tu nuevo personaje:");
		for(int i = 0; i < avatares.size(); i++) {
			System.out.println("["+i+"] " + avatares.get(i).toString());
		}
		int respuestaAvatar = entrada.nextInt();
		Avatar avatar = avatares.get(respuestaAvatar);
		System.out.println("Elige el arma de tu nuevo personaje:");
		for(int i = 0; i < armas.size(); i++) {
			System.out.println("["+i+"] " + armas.get(i).toString());
		}
		int respuestaArma = entrada.nextInt();
		Arma arma = armas.get(respuestaArma);
		System.out.println("Elige el poder de tu nuevo personaje:");
		for(int i = 0; i < poderes.size(); i++) {
			System.out.println("["+i+"] " + poderes.get(i).toString());
		}
		int respuestaPoder = entrada.nextInt();
		Poder poder = poderes.get(respuestaPoder);
		Personaje nuevoPersonaje = new Personaje(nombre, avatar, arma, poder);
		try {
			int idBaseDatos = guardarPersonaje(nuevoPersonaje);
			nuevoPersonaje.setId_personaje(idBaseDatos);
		} catch (Exception e) {}
		return nuevoPersonaje;
	}

	/**
	 * Pre: --- 
	 * Post: Este metodo se encarga de guardar un objeto de tipo Personaje 
	 * en la BD.
	 */
	private static int guardarPersonaje(Personaje nuevoPersonaje) throws Exception {
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into halo1.personaje(nombre, id_avatar, "
									+ "id_arma, id_poder) values (?, ?, ?, ?)", 
						Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, nuevoPersonaje.getNombre());
		preparedStatement.setInt(2, nuevoPersonaje.getAvatar().getId_avatar());
		preparedStatement.setInt(3, nuevoPersonaje.getArma().getId_arma());
		preparedStatement.setInt(4, nuevoPersonaje.getPoder().getId_poder());
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
	    rs.next();
	    int id = rs.getInt(1);
		System.out.println("Personaje guardado");
		conexion.cerrarBD();
		return id;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo recibe un Personaje e inicia
	 * una partida con el. 
	 */
	private static void iniciarPartida(Personaje personaje) {
		//Sirve para desordenar las preguntas y que salgan aleatoriamente
		Collections.shuffle(preguntas);
		System.out.println(personaje.toString());
		Arma arma = personaje.getArma();
		Poder poder = personaje.getPoder();
		int nPregunta = 0;
		while(true) {
			Pregunta pregunta = preguntas.get(nPregunta);
			boolean poderCurativo = personaje.getPoder().getDano_defensa() > 0 ? true : false;
			boolean poderDefensivo = personaje.getPoder().getDano_defensa() < 0 ? true : false;
			System.out.println(pregunta.getPregunta());
			int respuesta = entrada.nextInt();
			int valorTotal = 0;
			if (respuesta == 1 || respuesta == 2) {	
				valorTotal = calcularValorConsecuencia(respuesta, arma, poder, pregunta, poderDefensivo);
				personaje.getAvatar().setVida(personaje.getAvatar().getVida() + valorTotal);
				nPregunta++;
				if (poderCurativo) {
					personaje.getAvatar().setVida(personaje.getAvatar().getVida() + poder.getDano_defensa());
					System.out.println("Tu poder curativo te restaura " + poder.getDano_defensa() + " puntos de vida.");
				}			
				System.out.println("Vida actual: " + personaje.getAvatar().getVida());
			} else {
				System.out.println("¡Introduce una opcion correcta, ¡por favor!");
			}if(personaje.getAvatar().getVida() <= 0) {
				System.out.println("HAS PERDIDO");
				gameOver(personaje, nPregunta);
				break;
			}if(nPregunta == preguntas.size()) {
				System.out.println("HAS GANADO");
				gameOver(personaje, nPregunta);
				break;
			}	
		}	
	}
	/**
	 * Pre: ---
	 * Post: Este metodo calcula el valor real de la consecuencia teniendo
	 * en cuenta la actuacion del arma y del poder que tenga un Personaje.
	 */
	//El Math.abs: devuelve el valor absoluto de un argumento dado.
	private static int calcularValorConsecuencia(int respuesta, Arma arma, Poder poder, 
			Pregunta pregunta, boolean poderDefensivo) {
		int valorTotal = 0;
		int valorConsecuencia = 0;	
		if (respuesta == 1) {
			System.out.println("Consecuencia: " + pregunta.getDesencadenante1());
			valorConsecuencia = pregunta.getRespuesta1();
		} else {
			System.out.println("Consecuencia: " + pregunta.getDesencadenante2());
			valorConsecuencia = pregunta.getRespuesta2();
		}
		boolean pierdesVida = valorConsecuencia < 0 ? true : false;	
		if (pierdesVida) { 
			System.out.println("Esta accion te quita " + Math.abs(valorConsecuencia) + " puntos de vida.");
			if (poderDefensivo) {
				valorTotal = valorConsecuencia + arma.getDano() - poder.getDano_defensa();
				System.out.println("Gracias a tu arma y tu poder solo te quitan " + Math.abs(valorTotal) + " puntos de vida.");
			} else {
				valorTotal = valorConsecuencia + arma.getDano();
				System.out.println("Gracias a tu arma solo te quitan " + Math.abs(valorTotal) + " puntos de vida.");
			}
		} else {
			valorTotal = valorConsecuencia;
			System.out.println("Esta accion te suma " + valorTotal + " puntos de vida.");
		}
		return valorTotal;
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se ejecuta una vez terminada la partida y 
	 * pregunta al usuario si quiere guardar su resultado en el ranking.
	 */
	private static void gameOver(Personaje personaje, int rondas) {
		System.out.println("GUARDAR ESTADO EN RANKING\n SI NO");
		entrada.nextLine();
		while(true) {
			String opcion = entrada.nextLine();
			if(opcion.equalsIgnoreCase("si")) {
				try {
					guardarRanking(personaje, rondas);
				} catch(Exception e) {}
				break;
			} else if (opcion.equalsIgnoreCase("no")) {
				break;
			} else {
				System.out.println("Introduce una opcion correcta, ¡por favor!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de guardar en la BD un resultado
	 * de una partida. 
	 */
	private static void guardarRanking(Personaje personaje, int rondas) throws Exception {	
		conexion.conectarBD();
		PreparedStatement preparedStatement = conexion.getConnect()
				.prepareStatement("insert into ranking(id_personaje, rondas) values (?, ?)");
		preparedStatement.setInt(1, personaje.getId_personaje());
		preparedStatement.setInt(2, rondas);
		preparedStatement.executeUpdate();
		conexion.cerrarBD();
		System.out.println("Registro guardado en el ranking");	
	}
}