package it.unina.maven.SavingMoneyUnina.boundaries;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.control.LoginController;
import it.unina.maven.SavingMoneyUnina.control.NavigationController;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	ConnectionDatabase c = new ConnectionDatabase();
	LoginController l_controller = new LoginController();
	NavigationController n_controller = new NavigationController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("SavingMoneyUnina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(590, 308);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton Accedi = new JButton("Accedi");
		Accedi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		Accedi.setBounds(291, 192, 258, 30);
		Accedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(!email.getText().equals("") && password.getPassword().length != 0){
					Utente utenteLoggato;
					try {
						utenteLoggato = l_controller.checkLoginCredentials(email.getText(), password.getText());
						if(utenteLoggato != null){
							setVisible(false);
							n_controller.showHomePage(utenteLoggato);
						}
						else {
							n_controller.showAlert("Credenziali errate");
						}
					} catch (SQLException e1) {
						n_controller.showAlert("Si è verificato un errore: " + e1.getLocalizedMessage());
					}
					
				}
				else {
					n_controller.showAlert("I campi email o password sono vuoti");
				}
				
			}
		});
		contentPane.setLayout(null);
		Accedi.setBackground(new Color(53, 45, 72));
		Accedi.setForeground(new Color(255, 255, 255));
		Accedi.setOpaque(true);
		Accedi.setBorderPainted(false);
		
		email = new JTextField();
		email.setFont(new Font("Helvetica", Font.PLAIN, 13));
		email.setBackground(new Color(255, 255, 255));
		email.setBounds(291, 68, 258, 27);
		contentPane.add(email);
		email.setColumns(10);
		email.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		
		password = new JPasswordField();
		password.setFont(new Font("Helvetica", Font.PLAIN, 13));
		password.setBackground(new Color(255, 255, 255));
		password.setBounds(291, 140, 258, 27);
		contentPane.add(password);
		contentPane.add(Accedi);
		password.setColumns(10);
		password.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));

		
		lblNewLabel_1 = new JLabel("EMAIL");
		lblNewLabel_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(291, 50, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setForeground(new Color(172, 163, 175));
		lblNewLabel_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(291, 121, 76, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/logo.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(44, 42, 206, 191);
		contentPane.add(lblNewLabel);
	}
}
