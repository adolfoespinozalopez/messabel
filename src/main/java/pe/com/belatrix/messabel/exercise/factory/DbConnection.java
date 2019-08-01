package pe.com.belatrix.messabel.exercise.factory;

import java.sql.*;

public class DbConnection {

	private static String DBMS = "mysql";
	private static String SERVER = "localhost";
	private static String PORT = "3306";
	private static String DATABASE = "test";
	private static String USER = "root";
	private static String PASSWORD = "root";
	
	
	/***Permite Cargar en Memoria los Drivers */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Obtiene una conexión a la Base de Datos.
	 * @return
	 */
	public Connection getConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:"+DBMS+"://"+SERVER+":"+PORT+"/"+DATABASE,USER,PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return connection;
	}
}
