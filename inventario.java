import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class inventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static conexionDb conexion = new conexionDb ();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventario frame = new inventario();
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
	public inventario() {
		Connection db = conexion.conectar(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventario");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(173, 11, 106, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administrador obj = new administrador();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(320, 227, 89, 23);
		contentPane.add(btnRegresar);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(20, 53, 71, 14);
		contentPane.add(lblProducto);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setBounds(10, 74, 414, 142);
		contentPane.add(table);
		
		
		JComboBox<String> Productos = new JComboBox<String>();
		Productos.setBounds(86, 49, 149, 22);
		contentPane.add(Productos);
		String sentencia = "select producto from Producto";
		
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosProducto(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			Productos.addItem(lista.get(i));
		}
		Productos.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String sentenciaInventario = "select idProducto, producto, precioCompra, precioVenta, existencias, proveedor_idproveedor from Producto where producto ='"+ String.valueOf(Productos.getSelectedItem()) + "';";
				DefaultTableModel Model = new DefaultTableModel();
				Model = conexionDb.llenarInventario(db,sentenciaInventario); 
				table.setModel(Model);
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 74, 414, 142);
		contentPane.add(scrollPane);
	}	
}
	