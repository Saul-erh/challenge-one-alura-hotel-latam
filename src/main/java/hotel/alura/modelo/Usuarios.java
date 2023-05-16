package hotel.alura.modelo;

public class Usuarios {

	private Integer id;
	private String Usuario;
	private String Contrasena;
	
	public Usuarios(String usuario, String contrasena) {
		this.Usuario = usuario;
		this.Contrasena = contrasena;
	}

	public Usuarios(Integer id, String usuario, String contrasena) {
		
		this.id = id;
		this.Usuario = usuario;
		this.Contrasena = contrasena;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	
	@Override
	public String toString() {
		
		return String.format("id: %s, Usuario: %s, Contrasena: %s",
				this.id,
				this.Usuario,
				this.Contrasena);
	}
	
	
	
}
