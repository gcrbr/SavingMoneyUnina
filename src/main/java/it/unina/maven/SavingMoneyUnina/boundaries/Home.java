package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller = new Controller();

	public Home(final Utente utente) {
		setResizable(false);
		setTitle("Pagina Principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabel = new JLabel("Bentornato " + utente.getPersona().getNomeIntero());
		JLabel.setForeground(new Color(172, 163, 175));
		JLabel.setFont(new Font("Helvetica", Font.BOLD, 17));
		JLabel.setBounds(17, 17, 258, 20);
		contentPane.add(JLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(17, 49, 210, 115);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Saldo totale");
		lblNewLabel_1.setBounds(18, 2, 105, 46);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(controller.formatMoney(utente.getSaldoTotaleConti()));
		lblNewLabel.setBounds(18, 34, 181, 46);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JButton btnVediReportMensile = new JButton("Vedi report mensile");
		btnVediReportMensile.setBounds(6, 81, 193, 28);
		panel.add(btnVediReportMensile);
		btnVediReportMensile.setOpaque(true);
		btnVediReportMensile.setForeground(Color.WHITE);
		btnVediReportMensile.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnVediReportMensile.setBorderPainted(false);
		btnVediReportMensile.setBackground(new Color(53, 45, 72));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(35, 21, 40));
		panel_1.setBounds(460, 49, 210, 458);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("I tuoi conti");
		lblNewLabel_1_1.setBounds(18, 20, 61, 13);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_1_1);
		
		ArrayList<ContoCorrente> cs = utente.getContigestiti();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(35, 21, 40));
		panel_2.setLayout(null);
		panel_2.setPreferredSize(new Dimension(500, 53*cs.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel_2);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 48, 198, 365);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		panel_1.add(scrollPane);
		
		JButton btnAggiungiConto = new JButton("+ Aggiungi conto");
		btnAggiungiConto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNuovoConto(Home.this, utente);
			}
		});
		btnAggiungiConto.setOpaque(true);
		btnAggiungiConto.setForeground(Color.WHITE);
		btnAggiungiConto.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnAggiungiConto.setBorderPainted(false);
		btnAggiungiConto.setBackground(new Color(53, 45, 72));
		btnAggiungiConto.setBounds(9, 421, 193, 28);
		panel_1.add(btnAggiungiConto);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(239, 49, 210, 458);
		contentPane.add(panel_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("I tuoi portafogli");
		lblNewLabel_1_3.setBounds(19, 6, 105, 46);
		panel_4.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Helvetica", Font.PLAIN, 13));

		ArrayList<Portafogli> ps = utente.getPortafogli();

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(35, 21, 40));
		panel_2_1.setPreferredSize(new Dimension(500, 53*ps.size()));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 48, 198, 365);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		panel_4.add(scrollPane_1);
		
		JButton Accedi = new JButton("+ Crea portafogli");
		Accedi.setOpaque(true);
		Accedi.setForeground(Color.WHITE);
		Accedi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		Accedi.setBorderPainted(false);
		Accedi.setBackground(new Color(53, 45, 72));
		Accedi.setBounds(9, 421, 193, 28);
		panel_4.add(Accedi);
		
		for(int i=0; i<ps.size(); ++i) {
			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+53*i, 192, 43);
			panel_2_1.add(panel_3_1);
			
			JLabel lblNewLabel_1_2_3 = new JLabel(ps.get(i).getNome());
			lblNewLabel_1_2_3.setForeground(Color.WHITE);
			lblNewLabel_1_2_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
			lblNewLabel_1_2_3.setBackground(Color.WHITE);
			lblNewLabel_1_2_3.setBounds(6, 6, 131, 13);
			panel_3_1.add(lblNewLabel_1_2_3);
		}
		
		for(int i=0; i<cs.size(); ++i) {
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(53, 45, 72));
			panel_3.setBounds(2, 6+53*i, 192, 43);
			panel_2.add(panel_3);
			panel_3.setLayout(null);
			
			final ContoCorrente cx = cs.get(i);
			
			MouseAdapter info = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.showInformazioniConto(Home.this, cx);
				}
			};
			
			panel_3.addMouseListener(info);
			
			JLabel lblNewLabel_1_2 = new JLabel(cx.getIban());
			lblNewLabel_1_2.setBounds(6, 6, 131, 13);
			lblNewLabel_1_2.setBackground(new Color(255, 255, 255));
			lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
			panel_3.add(lblNewLabel_1_2);
			lblNewLabel_1_2.addMouseListener(info);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("Carta " + cx.getCarta().getNumero());
			lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
			lblNewLabel_1_2_1.setBackground(Color.WHITE);
			lblNewLabel_1_2_1.setBounds(6, 24, 131, 13);
			panel_3.add(lblNewLabel_1_2_1);
			lblNewLabel_1_2_1.addMouseListener(info);
		}
	}
}
