package it.unina.maven.SavingMoneyUnina.windows;
import java.util.*;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Accedi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 251, 193);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton Accedi = new JButton("Accedi");
		Accedi.setBounds(21, 124, 212, 21);
		Accedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(!email.getText().equals("") && password.getPassword().length != 0){
					PaginaPrincipale p = new PaginaPrincipale();
					if(email.getText().equals("salvatore") && Arrays.equals("1234".toCharArray(), password.getPassword())){
						setVisible(false);
						p.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Email e Password sbagliati");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Email e Password vuoti");
				}
				
			}
			
			
		});
		contentPane.setLayout(null);
		
		email = new JTextField();
		email.setBounds(21, 37, 212, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBackground(new Color(255, 255, 255));
		password.setBounds(21, 93, 212, 19);
		contentPane.add(password);
		contentPane.add(Accedi);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(21, 17, 96, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Giancarlo");
		lblNewLabel_1.setBounds(21, 68, 96, 13);
		contentPane.add(lblNewLabel_1);
	}
}
