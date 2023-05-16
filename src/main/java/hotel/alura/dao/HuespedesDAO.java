package hotel.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import hotel.alura.modelo.Huespedes;
import hotel.alura.modelo.Reservas;

public class HuespedesDAO {

final private Connection con;
	
	public HuespedesDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huespedes huespedes)  {

		try  {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO Huespedes " + "(Nombre, Apellido, Fecha_de_Nacimiento, Nacionalidad, Telefono, id_Reserva) " 
			+ "VALUES ( ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(huespedes, statement);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	private void ejecutaRegistro(Huespedes huespedes, PreparedStatement statement)
			throws SQLException {

		statement.setString(1, huespedes.getNombre());
		statement.setString(2, huespedes.getApellido());
		statement.setDate(3, new java.sql.Date(huespedes.getFechaNacimiento().getTime()));
		statement.setString(4, huespedes.getNacionalidad());
		statement.setString(5, huespedes.getTelefono());
		statement.setInt(6, huespedes.getIdReserva());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				huespedes.setId(resultSet.getInt(1));
				System.out.println(String.format("fue insertado el huesped %s", huespedes));
			}
		}

	}
	
	
	public int modificar(Huespedes huespedes) {

		try  {
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET " + " NOMBRE = ? "
					+ ", APELLIDO = ? " + ", FECHA_DE_NACIMIENTO = ? " + ", NACIONALIDAD = ? " + ", TELEFONO = ? " 
					+ " WHERE ID = ?");

			try (statement) {
				
				statement.setString(1, huespedes.getNombre());
				statement.setString(2, huespedes.getApellido());
				statement.setDate(3, new java.sql.Date(huespedes.getFechaNacimiento().getTime()));
				statement.setString(4, huespedes.getNacionalidad());
				statement.setString(5, huespedes.getTelefono());
				statement.setInt(6, huespedes.getId());

				statement.execute();

				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
	}
	
	public int eliminar(Integer id) {
		

		try  {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				System.out.println(updateCount);
				return updateCount;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
				}

		}
	
public List<Huespedes> listar() {
		
		List<Huespedes> resultado = new ArrayList<>();
		
		try  {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA  FROM HUESPEDES");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try(resultSet){
					while (resultSet.next()) {
						Huespedes fila = new Huespedes(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_DE_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));
						

						resultado.add(fila);
					}
				}
			}
					return resultado;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
		}


public List<Huespedes> listar(String seleccionApellido) {
	
	List<Huespedes> resultado = new ArrayList<>();
	
	try  {
		var querySelect = "SELECT ID, NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA "
				+ " FROM HUESPEDES"
				+ " WHERE APELLIDO = ?";
		System.out.println(querySelect);
		final PreparedStatement statement = con
				.prepareStatement(querySelect);

		try (statement) {
			statement.setString(1, seleccionApellido);
			statement.execute();

			final ResultSet resultSet = statement.getResultSet();

			try(resultSet){
				while (resultSet.next()) {
					Huespedes fila = new Huespedes(resultSet.getInt("ID"),
							resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"),
							resultSet.getDate("FECHA_DE_NACIMIENTO"),
							resultSet.getString("NACIONALIDAD"),
							resultSet.getString("TELEFONO"),
							resultSet.getInt("ID_RESERVA"));
					

					resultado.add(fila); 
				}
			}
		}
				return resultado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
}


public List<Huespedes> listar(int tipoDeDato, String elementoTabla, String dato) {
	
List<Huespedes> resultado = new ArrayList<>();

try  {
	var querySelect = "SELECT ID, NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA "
			+ " FROM HUESPEDES"
			+ " WHERE " + elementoTabla + " = ?";
	System.out.println(querySelect);
	final PreparedStatement statement = con
			.prepareStatement(querySelect);

	try (statement) {
		switch(tipoDeDato) {
		case 1: int Id = Integer.valueOf(dato);
		statement.setInt(1, Id);
		break;
		case 2: SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date fecha;
		try {
			fecha = formato.parse(dato);
			statement.setDate(1, new java.sql.Date(fecha.getTime()) );
			System.out.println(formato.format(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		break;
		case 3: statement.setString(1, dato);
		break;
		default: int id = Integer.valueOf(dato);
		statement.setInt(1, id);
		break;
		}
		statement.execute();

		final ResultSet resultSet = statement.getResultSet();

		try(resultSet){
			while (resultSet.next()) {
				Huespedes fila = new Huespedes(resultSet.getInt("ID"),
						resultSet.getString("NOMBRE"),
						resultSet.getString("APELLIDO"),
						resultSet.getDate("FECHA_DE_NACIMIENTO"),
						resultSet.getString("NACIONALIDAD"),
						resultSet.getString("TELEFONO"),
						resultSet.getInt("ID_RESERVA"));
				

				resultado.add(fila); 
			}
		}
	}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}


}
