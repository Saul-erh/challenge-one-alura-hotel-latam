package hotel.alura.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import hotel.alura.modelo.Reservas;

public class ReservasDAO {

final private Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reservas reservas)  {

		try  {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO Reservas " + "(FechaEntrada, FechaSalida, Valor, FormaPago) " 
			+ "VALUES ( ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reservas, statement);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	private void ejecutaRegistro(Reservas reservas, PreparedStatement statement)
			throws SQLException {

		statement.setDate(1, new java.sql.Date(reservas.getFechaEntrada().getTime()));
		statement.setDate(2,  new java.sql.Date(reservas.getFechaSalida().getTime()));
		statement.setString(3, reservas.getValor());
		statement.setString(4, reservas.getFormaPago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				reservas.setId(resultSet.getInt(1));
				System.out.println(String.format("fue insertado la reserva %s", reservas));
			}
		}

	}
	
	public int modificar(Reservas reservas) {

		try  {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " + " FECHAENTRADA = ? "
					+ ", FECHASALIDA = ? " + ", VALOR = ? " + ", FORMAPAGO = ? " + " WHERE ID = ?");

			try (statement) {
				
				statement.setDate(1, new java.sql.Date(reservas.getFechaEntrada().getTime()));
				statement.setDate(2,  new java.sql.Date(reservas.getFechaSalida().getTime()));
				statement.setString(3, reservas.getValor());
				statement.setString(4, reservas.getFormaPago());
				statement.setInt(5, reservas.getId());

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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
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

	
public List<Reservas> listar() {
		
		List<Reservas> resultado = new ArrayList<>();
		
		try  {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO  FROM RESERVAS");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try(resultSet){
					while (resultSet.next()) {
						Reservas fila = new Reservas(resultSet.getInt("ID"),
								resultSet.getDate("FECHAENTRADA"),
								resultSet.getDate("FECHASALIDA"),
								resultSet.getString("VALOR"),
								resultSet.getString("FORMAPAGO"));
						

						resultado.add(fila);
					}
				}
			}
					return resultado;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
		}

public List<Reservas> listar(Integer seleccionId) {
	
	List<Reservas> resultado = new ArrayList<>();
	
	try  {
		var querySelect = "SELECT ID, FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO "
				+ " FROM RESERVAS"
				+ " WHERE ID = ?";
		System.out.println(querySelect);
		final PreparedStatement statement = con
				.prepareStatement(querySelect);

		try (statement) {
			statement.setInt(1, seleccionId);
			statement.execute();

			final ResultSet resultSet = statement.getResultSet();

			try(resultSet){
				while (resultSet.next()) {
					Reservas fila = new Reservas(resultSet.getInt("ID"),
							resultSet.getDate("FECHAENTRADA"),
							resultSet.getDate("FECHASALIDA"),
							resultSet.getString("VALOR"),
							resultSet.getString("FORMAPAGO") );
					

					resultado.add(fila); 
				}
			}
		}
				return resultado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}


	public List<Reservas> listar(int tipoDeDato, String elementoTabla, String dato) {
	
	List<Reservas> resultado = new ArrayList<>();
	
	try  {
		var querySelect = "SELECT ID, FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO "
				+ " FROM RESERVAS"
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
					Reservas fila = new Reservas(resultSet.getInt("ID"),
							resultSet.getDate("FECHAENTRADA"),
							resultSet.getDate("FECHASALIDA"),
							resultSet.getString("VALOR"),
							resultSet.getString("FORMAPAGO") );
					

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
