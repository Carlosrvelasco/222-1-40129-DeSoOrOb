import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ventas extends JFrame {

	private JPanel contentPane;
	private JTextField txtCosto;
	private JTextField txtSubTotal;
	public double precioVenta;
	public static conexionDb conexion = new conexionDb ();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventas frame = new ventas();
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
	public ventas() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Costo:");
		lblNewLabel_3.setBounds(21, 139, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		
		txtCosto = new JTextField();
		txtCosto.setForeground(Color.BLACK);
		txtCosto.setEditable(false);
		txtCosto.setBounds(91, 136, 86, 20);
		contentPane.add(txtCosto);
		txtCosto.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(185, 11, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(21, 41, 46, 14);
		contentPane.add(lblCliente);
		
		JComboBox comboCliente = new JComboBox();
		comboCliente.setBounds(91, 37, 118, 22);
		contentPane.add(comboCliente);
		
		String sentenciaCliente = "select nombre from Cliente";
		ArrayList<String> listaCliente = new ArrayList<String>();
		listaCliente = conexionDb.llenarCombosClientes(db,sentenciaCliente); 
		System.out.println(listaCliente);
		for (int i = 0; i < listaCliente.size(); i++) {
			comboCliente.addItem(listaCliente.get(i));
		}
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(21, 82, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox CantidadCombo = new JComboBox();
		CantidadCombo.setBounds(91, 108, 118, 22);
		contentPane.add(CantidadCombo);
		
		
		JComboBox productos = new JComboBox();
		productos.setBounds(91, 78, 118, 22);
		contentPane.add(productos);
		
		String sentencia = "select producto from Producto";
		
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosProducto(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			productos.addItem(lista.get(i));
		}
		productos.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				CantidadCombo.removeAllItems();
				String sentenciaExt = "select existencias from Producto where Producto = '" +String.valueOf(productos.getSelectedItem())+"';";;
				String Cantidad = new String();
				Cantidad = conexionDb.obtenerExistencias(db,sentenciaExt); 
				System.out.println(Cantidad);
				for (int i = 1; i <= Integer.parseInt(Cantidad); i++) {
					String tempCantidad = Integer.toString(i); 
					CantidadCombo.addItem(tempCantidad);
				System.out.println(i);
				}
				String venta = "select precioVenta from Producto where Producto = '" +String.valueOf(productos.getSelectedItem())+"';";;
				
				precioVenta = conexionDb.llenarCombosPrecioVenta(db,venta); 
				txtCosto.setText(Double.toString(precioVenta));
				
			}
		});
		CantidadCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				double cantidadSeleccionada = CantidadCombo.getSelectedItem()!= null && String.valueOf(CantidadCombo.getSelectedItem()).length()>0? Double.parseDouble(String.valueOf(CantidadCombo.getSelectedItem())): 0.0;
				double SubTotal = precioVenta*cantidadSeleccionada;
				txtSubTotal.setText(Double.toString(SubTotal));
				System.out.println(String.valueOf(cantidadSeleccionada));
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad:");
		lblNewLabel_2.setBounds(21, 112, 70, 14);
		contentPane.add(lblNewLabel_2);
		
			

		
		JLabel lblNewLabel_4 = new JLabel("Costo total:");
		lblNewLabel_4.setBounds(21, 164, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setBounds(91, 161, 86, 20);
		contentPane.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Gracias por su compra! ");
				setVisible(false);
				 menuVentas obj = new menuVentas();
				obj.setVisible(true);
			}
		});
		btnPagar.setBounds(300, 189, 89, 23);
		contentPane.add(btnPagar);
		
		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menuVentas obj = new menuVentas();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(300, 227, 89, 23);
		contentPane.add(btnRegresar);		
		
		JCheckBox chckbxEnviarDomicilio = new JCheckBox("Enviar a domicilio");
		chckbxEnviarDomicilio.setBounds(21, 215, 156, 23);
		contentPane.add(chckbxEnviarDomicilio);
		
		
		
		
	}
}

