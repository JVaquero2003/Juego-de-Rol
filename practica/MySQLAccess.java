package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	
	/*
	 * Almacenamos la conexión con nuestra bd en 
	 * un objeto de tipo Connection. La abrimos cuando
	 * queremos empezar a operar con la BD y siempre
	 * debemos cerrarla al finalizar.
	 */
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	/*
	 * Almacena el resultado de las consultas en un dato de 
	 * tipo ResultSet, que tiene sus propios métodos para trabajar
	 * con las tablas y columnas.
	 */
	private ResultSet resultSet = null;
	
	/*
	 * Almacenamos los datos de conexión con nuestra BD.
	 */
	final private String host = "localhost:3306/halo1";
	final private String user = "root";
	final private String passwd = "root";

	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de realizar correctamente la
	 * conexion con la BD. Se llama cada vez que se lee de la BD
	 * o se guardan registros nuevos.
	 */
	public void conectarBD() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://" + host 
					+ "?user=" + user + "&password=" + passwd);
			statement = connect.createStatement();
		} catch (Exception e) {throw e;};
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de cerrar la conexion con la BD.
	 * Se llama cada vez que se termina de leer o de guardar datos
	 * en la misma.
	 */
	public void cerrarBD() {
		try {
			if (resultSet != null) {
				resultSet.close();
			} if (statement != null) {
				statement.close();
			} if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {}
	}
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}