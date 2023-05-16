package hotel.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.alura.modelo.Huespedes;
import hotel.alura.modelo.Usuarios;

public class UsuariosDAO {

	final private Connection con;

	public UsuariosDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Usuarios usuarios)  {

		try  {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO usuarios " + "(Usuario, Contrasena) " 
			+ "VALUES ( ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(usuarios, statement);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	private void ejecutaRegistro(Usuarios usuarios, PreparedStatement statement)
			throws SQLException {

		statement.setString(1, usuarios.getUsuario());
		statement.setString(2, usuarios.getContrasena());
		

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				usuarios.setId(resultSet.getInt(1));
				System.out.println(String.format("fue insertado el Usuario %s", usuarios));
			}
		}

	}
	
	public boolean filtra(String Usuario, String Contrasena) {
		
		boolean resulta = true;
		List<Usuarios> resultado = new ArrayList<>();
		
		try  {
			var querySelect = "SELECT ID, USUARIO, CONTRASENA "
					+ " FROM USUARIOS"
					+ " WHERE USUARIO = ? AND CONTRASENA = ? ";
			System.out.println(querySelect);
			final PreparedStatement statement = con
					.prepareStatement(querySelect);

			try (statement) {
				statement.setString(1, Usuario);
				statement.setString(2, Contrasena);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try(resultSet){
					while (resultSet.next()) {
						Usuarios fila = new Usuarios(resultSet.getInt("ID"),
								resultSet.getString("USUARIO"),
								resultSet.getString("CONTRASENA"));
						

						resultado.add(fila); 
					}
				}
			}
				
			if(resultado.isEmpty()) {
				resulta = false;
			}
					return resulta;
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
	}
	
}
