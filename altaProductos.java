import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class altaProductos extends JFrame {

	private JPanel contentPane;
	private JTextField txtidProducto;
	private JTextField txtProducto;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtidProveedor;
	private JTextField txtExistencias;
	
	public static conexionDb conexion = new conexionDb (); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					altaProductos frame = new altaProductos();
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
	public altaProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Connection db = conexion.conectar(); //hace la conexion a la base de datos
		
		
		JLabel lblNewLabel = new JLabel("Alta de Productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(147, 11, 162, 20);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("id Producto:");
		lblNewLabel_1.setBounds(23, 59, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		txtidProducto = new JTextField();
		txtidProducto.setBounds(127, 56, 86, 20);
		contentPane.add(txtidProducto);
		txtidProducto.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Producto:");
		lblNewLabel_2.setBounds(23, 83, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(127, 80, 86, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Precio de compra:");
		lblNewLabel_3.setBounds(23, 108, 110, 14);
		contentPane.add(lblNewLabel_3);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setBounds(145, 105, 68, 20);
		contentPane.add(txtPrecioCompra);
		txtPrecioCompra.setColumns(10);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Precio de venta:");
		lblNewLabel_4.setBounds(23, 137, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBounds(145, 134, 68, 20);
		contentPane.add(txtPrecioVenta);
		txtPrecioVenta.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad:");
		lblNewLabel_6.setBounds(243, 59, 66, 14);
		contentPane.add(lblNewLabel_6);
		
		txtExistencias = new JTextField();
		txtExistencias.setBounds(314, 56, 86, 20);
		contentPane.add(txtExistencias);
		txtExistencias.setColumns(10);
		
		
		JLabel lblNewLabel_5 = new JLabel("idProveedor:");
		lblNewLabel_5.setBounds(23, 165, 94, 14);
		contentPane.add(lblNewLabel_5);
		
		txtidProveedor = new JTextField();
		txtidProveedor.setBounds(127, 162, 86, 20);
		contentPane.add(txtidProveedor);
		txtidProveedor.setColumns(10);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement s;
				try {
					s = db.createStatement(); //enseguida se hace la sentencia para la base de datos para insertar
					String query = "INSERT INTO Producto (idProducto, producto, precioCompra, precioVenta, existencias, proveedor_idproveedor) VALUES ('"+txtidProducto.getText()+"','"+txtProducto.getText()+"','"+
							txtPrecioCompra.getText()+"','"+txtPrecioVenta.getText()+"','"+ txtExistencias.getText()+"','"+ txtidProveedor.getText()+"')";
							s.executeUpdate(query);
							int respuesta = JOptionPane.showConfirmDialog(null, "Desea agregar otro producto? " , "Se Agrego el producto", JOptionPane.YES_NO_OPTION);
							if (respuesta == 0) {
								txtidProducto.setText(null);
								txtProducto.setText(null);
								txtPrecioCompra.setText(null);
								txtPrecioVenta.setText(null);
								txtExistencias.setText(null);
								txtidProveedor.setText(null);
							} else {
								setVisible(false);
								administrador obj = new administrador();
								obj.setVisible(true);
								
								
							}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		btnGuardar.setBounds(28, 216, 89, 23);
		contentPane.add(btnGuardar);
		
		
		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administrador obj = new administrador();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(311, 216, 89, 23);
		contentPane.add(btnRegresar);
		
		
		
		
		
		
	}
}
