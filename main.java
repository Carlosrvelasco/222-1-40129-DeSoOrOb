import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	
	public static conexionDb conexion = new conexionDb ();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(110, 84, 86, 20);
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel txtUsuario = new JLabel("Usuario");
		txtUsuario.setBounds(27, 87, 46, 14);
		panel.add(txtUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(26, 124, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel titulo = new JLabel("Bienvenido a Ferreteria Vecor");
		titulo.setForeground(new Color(0, 0, 255));
		titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		titulo.setBounds(88, 11, 272, 25);
		panel.add(titulo);
		
		JButton btnIngresar = new JButton("Ingresar");
		Connection db = conexion.conectar(); 
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
						String dbContrasena = ""; 
						Statement s = db.createStatement();
						String dbrole = "";
						String query =  "Select * from usuario where nombre='" + textUsuario.getText() + "' AND contraseña='" + String.valueOf(passwordField.getPassword()) + "'";
						System.out.println(query);
						ResultSet rs = s.executeQuery(query);
						while(rs.next()) {
							dbContrasena =  rs.getString("contraseña");
							dbrole = rs.getString("tipoUsuario");
						}
					    if(dbContrasena.equals(String.valueOf(passwordField.getPassword()))) {
					    	System.out.println("Welcome " + textUsuario.getText() );
					  
					    		if(String.valueOf(dbrole).equals("2")) {
					    	  	setVisible(false);
					    		usuario windowUsuario = new usuario();
					    		windowUsuario.setVisible(true);
					    		
					    	} if(String.valueOf(dbrole).equals("1")) {
					    		setVisible(false);
					    		administrador windowAdministrador = new administrador();  //aqui se instancia al menu de administrador
					    		windowAdministrador.setVisible(true);
					    	}
					    } else {
					    	JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos" , "Error", JOptionPane.ERROR_MESSAGE, null);
					    }
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnIngresar.setBounds(58, 195, 89, 23);
		panel.add(btnIngresar);
		
		
		
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(259, 195, 89, 23);
		panel.add(btnSalir);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 121, 86, 20);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Vecor");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 38));
		lblNewLabel.setBounds(270, 91, 132, 36);
		panel.add(lblNewLabel);
	}
}
