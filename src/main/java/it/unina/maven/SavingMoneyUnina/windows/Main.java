package it.unina.maven.SavingMoneyUnina.windows;
import java.util.*;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
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
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
		setTitle("SavingMoneyUnina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 253);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton Accedi = new JButton("Accedi");
		Accedi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		Accedi.setBounds(20, 163, 258, 35);
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
		Accedi.setBackground(new Color(53, 45, 72));
		Accedi.setForeground(new Color(255, 255, 255));
		Accedi.setOpaque(true);
		Accedi.setBorderPainted(false);
		
		email = new JTextField();
		email.setFont(new Font("Helvetica", Font.PLAIN, 13));
		email.setBackground(new Color(255, 255, 255));
		email.setBounds(20, 39, 258, 27);
		contentPane.add(email);
		email.setColumns(10);
		email.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		
		password = new JPasswordField();
		password.setFont(new Font("Helvetica", Font.PLAIN, 13));
		password.setBackground(new Color(255, 255, 255));
		password.setBounds(20, 111, 258, 27);
		contentPane.add(password);
		contentPane.add(Accedi);
		password.setColumns(10);
		password.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));

		
		lblNewLabel_1 = new JLabel("EMAIL");
		lblNewLabel_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(20, 21, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setForeground(new Color(172, 163, 175));
		lblNewLabel_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(20, 92, 76, 16);
		contentPane.add(lblNewLabel_2);
	}
}
