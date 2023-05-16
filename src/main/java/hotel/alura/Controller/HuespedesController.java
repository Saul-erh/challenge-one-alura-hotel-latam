package hotel.alura.Controller;

import java.util.List;

import hotel.alura.dao.HuespedesDAO;
import hotel.alura.factory.ConnectionFactory;
import hotel.alura.modelo.Huespedes;
import hotel.alura.modelo.Reservas;

public class HuespedesController {

private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		this.huespedesDAO =  new HuespedesDAO(new ConnectionFactory().recuperaConexion());	
	}

	public void guardar(Huespedes huespedes) {
		
		huespedesDAO.guardar(huespedes);
	
	}
	
	public int modificar(Huespedes huespedes) {

		return huespedesDAO.modificar(huespedes);	

	}
	
	public int eliminar(Integer id) {
		
		return huespedesDAO.eliminar(id); 	

	}
	
	public List<Huespedes> listar(){

		return  huespedesDAO.listar();
		
	}
	
	public List<Huespedes> listar(String seleccionApellido){

		return  huespedesDAO.listar(seleccionApellido);
		
	}	
	
	public List<Huespedes> listar(int tipoDeDato, String elementoTabla, String dato){

		return  huespedesDAO.listar(tipoDeDato, elementoTabla, dato);
		
	}
	
}
