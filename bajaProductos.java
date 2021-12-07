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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class bajaProductos extends JFrame {

	private JPanel contentPane;

	
	public static conexionDb conexion = new conexionDb ();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bajaProductos frame = new bajaProductos();
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
	public bajaProductos() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Baja de productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(142, 25, 165, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(24, 110, 59, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox productos = new JComboBox();
		productos.setBounds(93, 106, 146, 22);
		contentPane.add(productos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productoselecionada =String.valueOf(productos.getSelectedItem());
				System.out.println(productoselecionada);
				String sentenciaidProducto = "select idProducto from Producto where producto = '"+ productoselecionada + "';";
				String idProducto = conexion.obteneridProducto(db, sentenciaidProducto);
				String sentenciaBaja = "DELETE FROM Producto WHERE (`idProducto` = '"+ idProducto + "');";
				Boolean banderaDeBorrado = conexion.bajaDeRegistro(db, sentenciaBaja);
				if (banderaDeBorrado) {
					JOptionPane.showMessageDialog(null, "Se elimino el producto exitosamente");
					setVisible(false);
					administrador obj = new administrador(); 
					obj.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto");
				}
			}
		});
		btnEliminar.setBounds(24, 207, 89, 23);
		contentPane.add(btnEliminar);
		
		String sentencia = "select producto from Producto";
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosProducto(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			productos.addItem(lista.get(i));
			
		
				
		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administrador obj = new administrador();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(311, 191, 89, 23);
		contentPane.add(btnRegresar);
		
		
	}
	}
	}
