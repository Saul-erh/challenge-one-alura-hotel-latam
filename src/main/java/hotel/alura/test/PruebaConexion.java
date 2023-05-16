package hotel.alura.test;

import java.sql.Connection;
import java.sql.SQLException;

import hotel.alura.factory.ConnectionFactory;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		
		Connection con = new ConnectionFactory().recuperaConexion();
		/*Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"Reborn1!"); */
		
        System.out.println("Cerrando la conexión");

        con.close();
    }
	
}
