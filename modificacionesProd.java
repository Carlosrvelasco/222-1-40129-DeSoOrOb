import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class modificacionesProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtidProducto;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtExistencias;
	public static conexionDb conexion = new conexionDb ();
	private JTextField txtidProveedor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modificacionesProd frame = new modificacionesProd();
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
	public modificacionesProd() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modificaciones de Productos");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(102, 11, 243, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id Producto:");
		lblNewLabel_1.setBounds(26, 57, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Producto:");
		lblNewLabel_2.setBounds(26, 85, 59, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Precio de compra:");
		lblNewLabel_3.setBounds(26, 111, 110, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio de venta:");
		lblNewLabel_4.setBounds(26, 136, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		txtidProducto = new JTextField();
		txtidProducto.setBounds(139, 54, 66, 20);
		contentPane.add(txtidProducto);
		txtidProducto.setColumns(10);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setBounds(139, 111, 66, 20);
		contentPane.add(txtPrecioCompra);
		txtPrecioCompra.setColumns(10);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBounds(139, 136, 66, 20);
		contentPane.add(txtPrecioVenta);
		txtPrecioVenta.setColumns(10);
		
		JComboBox comboBoxProducto = new JComboBox();
		comboBoxProducto.setBounds(139, 81, 101, 22);
		contentPane.add(comboBoxProducto);
		
		String sentencia = "select producto from Producto";
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosProducto(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			comboBoxProducto.addItem(lista.get(i));
		}

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administrador obj = new administrador();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(26, 211, 89, 23);
		contentPane.add(btnRegresar);
		
		txtExistencias = new JTextField();
		txtExistencias.setBounds(139, 161, 66, 20);
		contentPane.add(txtExistencias);
		txtExistencias.setColumns(10);
		
		JLabel lblExistencias = new JLabel("Existencias:");
		lblExistencias.setBounds(26, 164, 89, 14);
		contentPane.add(lblExistencias);
		
		JLabel lblProveedor = new JLabel("id Proveedor:");
		lblProveedor.setBounds(251, 57, 83, 14);
		contentPane.add(lblProveedor);
		
		txtidProveedor = new JTextField();
		txtidProveedor.setBounds(337, 54, 59, 20);
		contentPane.add(txtidProveedor);
		txtidProveedor.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sentenciaModificado = "UPDATE Producto SET idProducto ='"+txtidProducto.getText()+"', precioCompra = '"+txtPrecioCompra.getText()+"', precioVenta = '"+txtPrecioVenta.getText()+"', existencias = '"+txtExistencias.getText()+"', proveedor_idproveedor = '"+txtidProveedor.getText()+"' WHERE producto = '"+String.valueOf(comboBoxProducto.getSelectedItem())+"';";
				Boolean banderaDeActualizacion = conexion.actualizacionDeRegistro(db, sentenciaModificado);
				if (banderaDeActualizacion) {
					JOptionPane.showMessageDialog(null, "Se actualizo el producto exitosamente");
					setVisible(false);
					administrador obj = new administrador();
					obj.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo modificar producto");
				}
			}
		});
		
		btnGuardar.setBounds(307, 211, 89, 23);
		contentPane.add(btnGuardar);
		
		
}
}