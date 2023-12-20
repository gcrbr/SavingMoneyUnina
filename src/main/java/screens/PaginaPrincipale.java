package screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PaginaPrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroCarta;

	/**
	 * Create the frame.
	 */
	public PaginaPrincipale() {
		setTitle("Pagina Principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 349);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabel = new JLabel("NUMERO CARTA");
		JLabel.setForeground(new Color(172, 163, 175));
		JLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		JLabel.setBounds(188, 69, 258, 20);
		contentPane.add(JLabel);
		
		JButton bottoneAggiungi = new JButton("AGGIUNGI");
		bottoneAggiungi.setFont(new Font("Dialog", Font.PLAIN, 14));
		bottoneAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroCarta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Il numero carta è vuoto!");
				}
			}
		});
		bottoneAggiungi.setBackground(new Color(53, 45, 72));
		bottoneAggiungi.setForeground(new Color(255, 255, 255));
		bottoneAggiungi.setOpaque(true);
		bottoneAggiungi.setBorderPainted(false);
		bottoneAggiungi.setBounds(189, 153, 257, 27);
		contentPane.add(bottoneAggiungi);
		
		numeroCarta = new JTextField();
		numeroCarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		numeroCarta.setBackground(new Color(255, 255, 255));
		numeroCarta.setBounds(187, 99, 258, 27);
		contentPane.add(numeroCarta);
		numeroCarta.setColumns(10);
	}
}
