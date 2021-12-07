import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class menuVentas extends JFrame {

	private JPanel contentPane;

	public static conexionDb conexion = new conexionDb ();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuVentas frame = new menuVentas();
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
	public menuVentas() {
		Connection db = conexion.conectar(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ventas");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(183, 26, 93, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Hacer una venta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ventas obj = new ventas();
				obj.setVisible(true);
			}
		});
		btnNewButton.setBounds(149, 93, 132, 23);
		contentPane.add(btnNewButton);
		/*String sentencia = "select idTicket from Ventas";
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarComboTicket(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			comboBoxTickets.addItem(lista.get(i));
		}*/
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				usuario obj = new usuario();
				obj.setVisible(true);
			}
		});
		btnRegresar.setBounds(295, 200, 89, 23);
		contentPane.add(btnRegresar);
		
		/*String sentencia = "select idTicket from Ventas";
		ArrayList<String> lista = new ArrayList<String>();
		lista = conexionDb.llenarCombosTickets(db,sentencia); 
		System.out.println(lista);
		for (int i = 0; i < lista.size(); i++) {
			comboBoxTickets.addItem(lista.get(i));*/
		
		
		
		
	
	}
}
