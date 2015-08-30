package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	String sqlDriver = "com.mysql.jdbc.Driver";
	String host = "localhost";
	String port = "3306";
	String user = "root";
	String pass = "";
	String db = "jAjedrez";

	private Connection conn;
	private int cantConexiones = 0;
	
	// REDEFINIMOS EL CONSTRUCTOR PARA QUE NADIE PUEDA CONSTRUIR OTRO
	private ConexionDB(){
		try{
			Class.forName(sqlDriver);
			conn = null;
			cantConexiones = 0;
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	private static ConexionDB instancia;
	
	public static ConexionDB getInstancia(){
		
		if(instancia == null){
			instancia = new ConexionDB();
		}
		
		return instancia;
	}
	
	public Connection getConexion(){
	
			try{
				if(conn == null || conn.isClosed()){
					conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass);
					cantConexiones ++;
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			}
			
			return conn;
	
	}
	
	public void releaseConexion(){
		
		try{
			cantConexiones --;
			if(cantConexiones == 0){
				conn.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
}
