package it.unina.maven.SavingMoneyUnina.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PaginaPrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroCarta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaPrincipale frame = new PaginaPrincipale();
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
	public PaginaPrincipale() {
		setTitle("Pagina Principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabel = new JLabel("Numero Carta");
		JLabel.setBounds(150, 29, 98, 20);
		contentPane.add(JLabel);
		
		JButton bottoneAggiungi = new JButton("Aggiungi");
		bottoneAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroCarta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Il numero carta è vuoto!");
				}
			}
		});
		bottoneAggiungi.setBounds(150, 110, 98, 27);
		contentPane.add(bottoneAggiungi);
		
		numeroCarta = new JTextField();
		numeroCarta.setBounds(150, 59, 96, 19);
		contentPane.add(numeroCarta);
		numeroCarta.setColumns(10);
	}
}
