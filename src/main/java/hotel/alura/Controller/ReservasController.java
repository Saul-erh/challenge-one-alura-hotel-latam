package hotel.alura.Controller;

import java.util.List;

import hotel.alura.dao.ReservasDAO;
import hotel.alura.factory.ConnectionFactory;
import hotel.alura.modelo.Reservas;

public class ReservasController {

private ReservasDAO reservasDAO;
	
	public ReservasController() {
		this.reservasDAO =  new  ReservasDAO(new ConnectionFactory().recuperaConexion());	
	}

	public void guardar(Reservas reservas) {
		
		reservasDAO.guardar(reservas);
	
	}
	
	public int modificar(Reservas reservas) {

		return reservasDAO.modificar(reservas);	

	}
	
	public int eliminar(Integer id) {
		
		return reservasDAO.eliminar(id); 	

	}
	
	public List<Reservas> listar(){

		return  reservasDAO.listar();
		
	}
	
	public List<Reservas> listar(Integer seleccionId){

		return  reservasDAO.listar(seleccionId);
		
	}
	
	public List<Reservas> listar(int tipoDeDato, String elementoTabla, String dato){

		return  reservasDAO.listar(tipoDeDato, elementoTabla, dato);
		
	}

}
