package hotel.alura.modelo;

import java.util.Date;

//import java.sql.Date;


public class Reservas {

	private Integer id;
	private Date FechaEntrada;
	private Date FechaSalida;
	private String Valor;
	private String FormaPago;
	
	public Reservas(Date FechaEntrada, Date FechaSalida, String Valor, String FormaPago) {
		
		this.FechaEntrada = FechaEntrada;
		this.FechaSalida = FechaSalida;
		this.Valor = Valor;
		this.FormaPago = FormaPago;
		
	}

	public Reservas(int id, Date FechaEntrada, Date FechaSalida, String Valor, String FormaPago) {
		
		this.id = id;
		this.FechaEntrada = FechaEntrada;
		this.FechaSalida = FechaSalida;
		this.Valor = Valor;
		this.FormaPago = FormaPago;
		
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return FechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		FechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return FechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		FechaSalida = fechaSalida;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public String getFormaPago() {
		return FormaPago;
	}

	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}

	@Override
	public String toString() {
		
		return String.format("id: %s, FechaEntrada: %s, FechaSalida: %s, Valor: %s, forma de pago: %s",
				this.id,
				this.FechaEntrada,
				this.FechaSalida,
				this.Valor,
				this.FormaPago);
	}
	
}
