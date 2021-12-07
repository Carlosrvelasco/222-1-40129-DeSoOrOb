import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class cuentasUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtContrasena;
	private JTextField txtidUsuario;
	private JTextField txtTipoUsuario;
	
	public static conexionDb conexion = new conexionDb ();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cuentasUsuarios frame = new cuentasUsuarios();
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
	public cuentasUsuarios() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cuentas de Usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(137, 11, 194, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Baja de Usuario");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(265, 49, 124, 14);
		contentPane.add(lblNewLabel_5);
		
		JComboBox BajaUsuario = new JComboBox();
		BajaUsuario.setBounds(265, 73, 121, 22);
		contentPane.add(BajaUsuario);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuarioSelecionado =String.valueOf(BajaUsuario.getSelectedItem());
				System.out.println(usuarioSelecionado);
				String sentenciaidUsuario = "select idUsuario from Usuario where nombre = '"+ usuarioSelecionado + "';";
				String idUsuario = conexion.obteneridUsuario(db, sentenciaidUsuario);
				String sentenciaBaja = "DELETE FROM Usuario WHERE (`idUsuario` = '"+ idUsuario + "');";
				Boolean banderaDeBorrado = conexion.bajaDeRegistro(db, sentenciaBaja);
				if (banderaDeBorrado) {
					JOptionPane.showMessageDialog(null, "Se elimino el usuario exitosamente");
					setVisible(false);
					administrador obj = new administrador(); 
					obj.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
				}
			}
		});
		
		btnEliminar.setBounds(293, 124, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administrador obj = new administrador();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(293, 215, 89, 23);
		contentPane.add(btnRegresar);
		
		JLabel lblNewLabel_1 = new JLabel("Alta de Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(27, 49, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(88, 105, 98, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(88, 136, 98, 20);
		contentPane.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		JLabel idUsuario = new JLabel("id Usuario:");
		idUsuario.setBounds(10, 77, 79, 14);
		contentPane.add(idUsuario);
		
		txtidUsuario = new JTextField();
		txtidUsuario.setText("");
		txtidUsuario.setBounds(115, 74, 71, 20);
		contentPane.add(txtidUsuario);
		txtidUsuario.setColumns(10);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setBounds(115, 164, 71, 20);
		contentPane.add(txtTipoUsuario);
		txtTipoUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(10, 108, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_3.setBounds(10, 139, 79, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo de usuario:");
		lblNewLabel_4.setBounds(10, 167, 104, 14);
		contentPane.add(lblNewLabel_4);
		
		
		
	
		
		String sentenciaUsuarios = "select nombre from Usuario";
		ArrayList<String> listaUsuarios = new ArrayList<String>();
		listaUsuarios = conexionDb.llenarCombosUsuario(db,sentenciaUsuarios); 
		System.out.println(listaUsuarios);
		for (int i = 0; i < listaUsuarios.size(); i++) {
			BajaUsuario.addItem(listaUsuarios.get(i));
		
		}
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement s;
				try {
					s = db.createStatement();
					
					String query = "INSERT INTO Usuario (idUsuario, contraseña, tipoUsuario, nombre) VALUES ('"+txtidUsuario.getText()+"','"+txtContrasena.getText()+"','"+
							txtTipoUsuario.getText()+"','"+txtNombre.getText()+"')";
							s.executeUpdate(query);
							int respuesta = JOptionPane.showConfirmDialog(null, "Desea agregar otro usuario? " , "Se Agrego el usuario", JOptionPane.YES_NO_OPTION);
							if (respuesta == 0) {
								txtidUsuario.setText(null);
								txtContrasena.setText(null);
								txtTipoUsuario.setText(null);
								txtNombre.setText(null);
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
		
		btnGuardar.setBounds(65, 215, 89, 23);
		contentPane.add(btnGuardar);
		
		
		
		
	}
}
