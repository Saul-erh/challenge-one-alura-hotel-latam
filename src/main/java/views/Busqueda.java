package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import hotel.alura.Controller.HuespedesController;
import hotel.alura.Controller.ReservasController;
import hotel.alura.modelo.Huespedes;
import hotel.alura.modelo.Reservas;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private JLabel lblBuscar;
	private JLabel lblEditar;
	private JLabel lblEliminar;
	private ReservasController reservasController;
	private HuespedesController huespedesController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		this.reservasController = new ReservasController();
	    this.huespedesController = new HuespedesController();
	        
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));		
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargarTabla(true); 
		cargarTabla(false);   
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
				
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
				
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199)); 
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtBuscar.getText() != null) {
					int tab = panel.getSelectedIndex();
					System.out.println(tab);
					
					if(tab == 0 && esNumero(txtBuscar.getText())) {
						filtrarTabla(true); // true para buscar en tabla reservas
					}else if(tab == 1 && esApellido(txtBuscar.getText())) {
						filtrarTabla(false); // false para buscar en tabla huespedes
					}else {
						JOptionPane.showMessageDialog(null, "El campo introducido es invalido!!");
					}
					
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnbuscar.setBackground(Color.blue);
				
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnbuscar.setBackground(new Color(12, 138, 199));
				 
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tab = panel.getSelectedIndex();
				
				if(tieneFilaElegida(tbReservas) && tieneFilaElegida(tbHuespedes)) {
					JOptionPane.showMessageDialog(null, "Seleccione Elemento a Editar");
					
				} else if(tieneFilaElegida(tbReservas) || tab == 1) {
					quitaSeleccionTabla(tbReservas);
					modificar(tbHuespedes, modeloHuesped, false);
				} else{
					quitaSeleccionTabla(tbHuespedes);
					modificar(tbReservas, modelo, true);
				}		
	           
			}
			
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnEditar.setBackground(Color.green);
				
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnEditar.setBackground(new Color(12, 138, 199));
			     
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tab = panel.getSelectedIndex();
				
				if(tieneFilaElegida(tbReservas) || tab == 1) {
					eliminar(tbHuespedes, modeloHuesped, false);
				}else {
					String id = modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
					boolean existe = buscarEnTablaPorElemento(false, 1, "ID_RESERVA", id);
                	if(existe) {
                		JOptionPane.showMessageDialog(null, "Es necesario eliminar primero al huesped con  Numero de "
                				+ "Reserva: " + id);
                		quitaSeleccionTabla(tbReservas);                         
                	}else {
                		eliminar(tbReservas, modelo, true);
                	}
					
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnEliminar.setBackground(Color.red);
				
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnEliminar.setBackground(new Color(12, 138, 199));
			     
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		
		
	}
	
	private boolean esNumero(String cadena) {
		return cadena != null && cadena.matches("[0-9]+");
	}
	
	private boolean esApellido(String cadena) {
		return cadena != null && cadena.matches("[a-z A-Z]+");
	}
	
	private void quitaSeleccionTabla(JTable tabla){
		tabla.clearSelection();
	}
	
	private boolean buscarEnTablaPorElemento(boolean controlador, int tipoDeDato, String elementoTabla, String dato) {
		
		
		if(controlador) {
			var reserva = this.reservasController.listar(tipoDeDato, elementoTabla, dato);
			if(reserva.isEmpty()) {
				return false;
				}
			System.out.println(reserva);
			
		}else {
			var huesped = this.huespedesController.listar(tipoDeDato, elementoTabla, dato);
			if(huesped.isEmpty()) {
				return false;
				}
			System.out.println(huesped);
			
		}
		
		return true;
	}
	
	private void cargarTabla(boolean tablaModificada) {
		if(tablaModificada) {
			var	reserva = this.reservasController.listar();
			reserva.forEach(reservas -> modelo.addRow(new Object[] { 
	        		  reservas.getId(), 
	        		  reservas.getFechaEntrada(),
	        		  reservas.getFechaSalida(),
	        		  reservas.getValor(), 
	        		  reservas.getFormaPago() })); 
		}else {
			var huesped = this.huespedesController.listar();
			
			huesped.forEach(huespedes -> modeloHuesped.addRow(new Object[] { 
	          		  huespedes.getId(), 
	          		  huespedes.getNombre(),
	          		  huespedes.getApellido(),
	          		  huespedes.getFechaNacimiento(),
	          		  huespedes.getNacionalidad(),
	          		  huespedes.getTelefono(), 
	          		  huespedes.getIdReserva() }));
		}
	}
	
	
	private void filtrarTabla(boolean tablaFiltrada) {
	    
		
		if(tablaFiltrada) {
		var	reserva = this.reservasController.listar(Integer.valueOf(txtBuscar.getText()));     
		
			if(reserva.isEmpty()) {
			 JOptionPane.showMessageDialog(this, "El Id que buscas no existe!!");
			} else {
			limpiarTabla(true);
			reserva.forEach(reservas -> modelo.addRow(new Object[] { 
		          	reservas.getId(), 
		          	reservas.getFechaEntrada(),
		          	reservas.getFechaSalida(),
		          	reservas.getValor(), 
		          	reservas.getFormaPago() }));  
				}
		}else {
			var	huesped = this.huespedesController.listar(txtBuscar.getText());     
			
			if(huesped.isEmpty()) {
			 JOptionPane.showMessageDialog(this, "El Apellido que buscas no existe!!");
			} else {
			limpiarTabla(false);
			huesped.forEach(huespedes -> modeloHuesped.addRow(new Object[] { 
	          		  huespedes.getId(), 
	          		  huespedes.getNombre(),
	          		  huespedes.getApellido(),
	          		  huespedes.getFechaNacimiento(),
	          		  huespedes.getNacionalidad(),
	          		  huespedes.getTelefono(), 
	          		  huespedes.getIdReserva() })); 
				}		
			
		}
		
    }
	
	
	private void limpiarTabla(boolean tablaModificada) {
		if(tablaModificada) { // True para reservas 
			modelo.getDataVector().clear();
		}else {
			modeloHuesped.getDataVector().clear();
		}
        
    }
	
	 private void eliminar(JTable tabla, DefaultTableModel model, boolean controlador ) {
		 
	        if (tieneFilaElegida(tabla)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(model.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(model.getValueAt(tabla.getSelectedRow(), 0).toString());

	                    int cantidadEliminada;
	                    boolean tablaModificada;
	                    
	                    if(controlador) {
	                    	
	                    	cantidadEliminada = this.reservasController.eliminar(id);
		                    tablaModificada = true;	
	                    	
	                    }else {
	                    	
	                    	cantidadEliminada = this.huespedesController.eliminar(id);
	                    	tablaModificada = false;
	                    }

	                    model.removeRow(tabla.getSelectedRow());
	                    
	                    limpiarTabla(tablaModificada);
	                    cargarTabla(tablaModificada);

	                    JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }

	 private boolean tieneFilaElegida(JTable tabla) {
	        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	    }
	 
	 private void modificar(JTable tabla, DefaultTableModel model, boolean controlador) {
	        if (tieneFilaElegida(tabla)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }
	        
	        boolean tablaModificada ;
	        
	        if(controlador) {
	        	ModificaParaReservas(tabla, model);
	        	tablaModificada = true;
	        } else {
	        	ModificaParaHuespedes(tabla, model);
	        	tablaModificada = false;
	        }
	        
	        limpiarTabla(tablaModificada);
            cargarTabla(tablaModificada);
	        
	    }
	   
	  private void  ModificaParaReservas(JTable tabla, DefaultTableModel model) {
		  
		  Optional.ofNullable(model.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
          .ifPresentOrElse(fila -> { 
              Integer id = Integer.valueOf(model.getValueAt(tabla.getSelectedRow(), 0).toString());
              java.util.Date FechaEntrada = DameFecha(String.valueOf(model.getValueAt(tabla.getSelectedRow(), 1)));
              java.util.Date FechaSalida = DameFecha(String.valueOf(model.getValueAt(tabla.getSelectedRow(), 2)));
              String Valor = (String) model.getValueAt(tabla.getSelectedRow(), 3);
              String FormaPago = (String) model.getValueAt(tabla.getSelectedRow(), 4);
 
  				var reservas = new Reservas(id, FechaEntrada, FechaSalida, Valor, FormaPago);
  				
  				System.out.println(reservas);   
                int filasModificadas = this.reservasController.modificar(reservas);
                
                JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito! ", filasModificadas));
          }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		  
		  
	  }
	  
	  
private void  ModificaParaHuespedes(JTable tabla, DefaultTableModel model) {
		  
		  Optional.ofNullable(model.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
          .ifPresentOrElse(fila -> { 
              Integer id = Integer.valueOf(model.getValueAt(tabla.getSelectedRow(), 0).toString());
              String Nombre = (String) model.getValueAt(tabla.getSelectedRow(), 1);
              String Apellido = (String) model.getValueAt(tabla.getSelectedRow(), 2);
              java.util.Date FechaNacimiento = DameFecha(String.valueOf(model.getValueAt(tabla.getSelectedRow(), 3)));
              String Nacionalidad = (String) model.getValueAt(tabla.getSelectedRow(), 4);
              String Telefono = (String) model.getValueAt(tabla.getSelectedRow(), 5);
              Integer idReserva = Integer.valueOf(model.getValueAt(tabla.getSelectedRow(), 6).toString());
              
              var huespedes = new Huespedes(id, Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, idReserva);
              System.out.println(huespedes);      
               int filasModificadas = this.huespedesController.modificar(huespedes);
		   
              JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito! ", filasModificadas));
          }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		  	  
	  }

private java.util.Date DameFecha(String fecha) {
	
	  SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 

	  java.util.Date date;
	  java.util.Date dateDefault = new java.util.Date();
	  try {
		date = formato.parse(fecha);
		return date;
	  } catch (ParseException e) {
		
		e.printStackTrace();
	  }
	
	  return dateDefault;
}
	 
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
}
