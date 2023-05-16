package hotel.alura.modelo;

import java.util.Date;

public class Huespedes {

	private Integer id;
	private String Nombre;
	private String Apellido;
	private Date FechaNacimiento;
	private String Nacionalidad;
	private String Telefono;
	private Integer idReserva;
	
	public Huespedes(String Nombre, String Apellido, Date FechaNacimiento, String Nacionalidad, String Telefono, int idReserva) {
		
		this.Nombre = Nombre;
		this.Apellido = Apellido;
		this.FechaNacimiento = FechaNacimiento;
		this.Nacionalidad = Nacionalidad;
		this.Telefono = Telefono;
		this.idReserva = idReserva;
		
	}

	public Huespedes(int id, String Nombre, String Apellido, Date FechaNacimiento, String Nacionalidad, String Telefono, Integer idReserva) {
		
		this.id = id;
		this.Nombre = Nombre;
		this.Apellido = Apellido;
		this.FechaNacimiento = FechaNacimiento;
		this.Nacionalidad = Nacionalidad;
		this.Telefono = Telefono;
		this.idReserva = idReserva;
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	@Override
	public String toString() {
		
		return String.format("id: %s, Nombre: %s, Apellido: %s, FechaNacimiento: %s, Nacionalidad: %s, Telefono: %s, idReserva: %s",
				this.id,
				this.Nombre,
				this.Apellido,
				this.FechaNacimiento,
				this.Nacionalidad,
				this.Telefono,
				this.idReserva);
	}
}
