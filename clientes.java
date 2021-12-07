import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class clientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtidCliente;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDomicilio;
	private JTable table_1;

	public static conexionDb conexion = new conexionDb ();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientes frame = new clientes();
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
	public clientes() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 301);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(234, 11, 87, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Telefono:");
		lblNewLabel_4.setBounds(10, 116, 69, 14);
		contentPane.add(lblNewLabel_4);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(89, 113, 86, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		
		JLabel lblNewLabel_5 = new JLabel("Domicilio:");
		lblNewLabel_5.setBounds(10, 141, 69, 14);
		contentPane.add(lblNewLabel_5);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(89, 138, 86, 20);
		contentPane.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		
		
		JLabel LabelClientes = new JLabel("Clientes:");
		LabelClientes.setBounds(300, 64, 69, 14);
		contentPane.add(LabelClientes);
		
		JLabel lblNewLabel_2 = new JLabel("id cliente:");
		lblNewLabel_2.setBounds(10, 64, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		txtidCliente = new JTextField();
		txtidCliente.setBounds(89, 61, 86, 20);
		contentPane.add(txtidCliente);
		txtidCliente.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setBounds(10, 91, 69, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(89, 88, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
	
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement s;
				try {
					s = db.createStatement();
					String query = "INSERT INTO Cliente (idCliente, nombre, telefono, domicilio) VALUES ('"+txtidCliente.getText()+"','"+txtNombre.getText()+"','"+
							txtTelefono.getText()+"','"+txtDomicilio.getText()+"')";
							s.executeUpdate(query);
							int respuesta = JOptionPane.showConfirmDialog(null, "Desea agregar otro cliente? " , "Se guardo satisfactoriamente", JOptionPane.YES_NO_OPTION);
							if (respuesta == 0) {
								txtidCliente.setText(null);
								txtNombre.setText(null);
								txtTelefono.setText(null);
								txtDomicilio.setText(null);
							} else {
								setVisible(false);
								usuario obj = new usuario();
								obj.setVisible(true);
							}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
		btnGuardar.setBounds(10, 173, 89, 23);
		contentPane.add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 156, 350, 94);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JComboBox comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(368, 60, 131, 22);
		contentPane.add(comboBoxClientes);
		
		String sentencia = "select nombre from Cliente";
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosClientes(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			comboBoxClientes.addItem(lista.get(i));
		}
		comboBoxClientes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String sentenciaClientes = "select idCliente, nombre, telefono, domicilio from Cliente where nombre ='"+ String.valueOf(comboBoxClientes.getSelectedItem()) + "';";
				DefaultTableModel Model = new DefaultTableModel();
				Model = conexionDb.llenarClientes(db,sentenciaClientes); 
				table_1.setModel(Model);
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clienteselecionada =String.valueOf(comboBoxClientes.getSelectedItem());
				System.out.println(clienteselecionada);
				String sentenciaidCliente = "select idCliente from cliente where nombre = '"+ clienteselecionada + "';";
				String idCliente = conexion.obteneridCliente(db, sentenciaidCliente);
				String sentenciaBaja = "DELETE FROM Cliente WHERE (`idCliente` = '"+ idCliente + "');";
				Boolean banderaDeBorrado = conexion.bajaDeRegistro(db, sentenciaBaja);
				if (banderaDeBorrado) {
					JOptionPane.showMessageDialog(null, "Se elimino el cliente exitosamente"); 
					setVisible(false);
					usuario obj = new usuario();
					obj.setVisible(true); 
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente");
				}
			}
		});
		
		btnEliminar.setBounds(410, 107, 89, 23);
		contentPane.add(btnEliminar);
		
	
		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				usuario obj = new usuario();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(10, 227, 89, 23);
		contentPane.add(btnRegresar);
		
	
		
		
		
		
	}
}

