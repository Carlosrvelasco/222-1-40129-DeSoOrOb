import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class administrador extends JFrame {

	private JPanel contentPane;
	protected Object createadministrador; //esto es para que los menus puedan regresar a la pagina de administrador
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					administrador frame = new administrador();
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
	public administrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrador");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(164, 11, 127, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnAltas = new JButton("Alta de productos");
		btnAltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				altaProductos obj = new altaProductos();
				obj.setVisible(true);
			}
		});
		btnAltas.setBounds(153, 60, 138, 23);
		contentPane.add(btnAltas);
		
		JButton btnBajas = new JButton("Baja de productos");
		btnBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				bajaProductos obj = new bajaProductos();
				obj.setVisible(true);
			}
		});
		btnBajas.setBounds(153, 94, 138, 23);
		contentPane.add(btnBajas);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				inventario obj = new inventario();
				obj.setVisible(true);
			}
		});
		btnInventario.setBounds(153, 128, 138, 23);
		contentPane.add(btnInventario);
		
		JButton btnModificaciones = new JButton("Modificaciones");
		btnModificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				modificacionesProd obj = new modificacionesProd();
				obj.setVisible(true);
			}
		});
		btnModificaciones.setBounds(153, 162, 138, 23);
		contentPane.add(btnModificaciones);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main obj = new main();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(37, 227, 89, 23);
		contentPane.add(btnRegresar);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cuentasUsuarios obj = new cuentasUsuarios();
				obj.setVisible(true);
			}
		});
		btnUsuarios.setBounds(153, 196, 138, 23);
		contentPane.add(btnUsuarios);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(318, 227, 89, 23);
		contentPane.add(btnSalir);
	}
		
		public void createadministrador() { //esto es para que los menus puedan regresar a la pagina de administrador
			// TODO Auto-generated method stub
	}

}
