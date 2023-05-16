package hotel.alura.Controller;

import hotel.alura.dao.UsuariosDAO;
import hotel.alura.factory.ConnectionFactory;
import hotel.alura.modelo.Usuarios;

public class UsuariosController {

	private UsuariosDAO usuariosDAO;

	public UsuariosController() {
		this.usuariosDAO = new  UsuariosDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Usuarios usuarios) {
		
		usuariosDAO.guardar(usuarios);
	
	}
	
	public boolean filtra(String Usuario, String Contrasena) {
		
		return usuariosDAO.filtra(Usuario, Contrasena);
	}
	
}
