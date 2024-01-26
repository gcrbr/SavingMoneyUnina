package it.unina.maven.SavingMoneyUnina.windows;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

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
		setResizable(false);
		setTitle("SavingMoneyUnina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton Accedi = new JButton("ACCEDI");
		Accedi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		Accedi.setBounds(291, 192, 258, 30);
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