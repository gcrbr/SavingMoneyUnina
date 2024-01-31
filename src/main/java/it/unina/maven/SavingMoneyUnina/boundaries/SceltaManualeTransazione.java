package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;
import it.unina.maven.SavingMoneyUnina.entities.Utente;
import javax.swing.ScrollPaneConstants;

public class SceltaManualeTransazione extends JFrame {
	private final JPanel panel = new JPanel();
	private Controller controller = new Controller();
	
	public SceltaManualeTransazione(final Utente u, final Portafogli p) {
		setResizable(false);
		setTitle("Scegli una transazione da aggiungere");
		getContentPane().setBackground(new Color(28, 21, 40));
		panel.setBackground(new Color(28, 21, 40));
		setBounds(100, 100, 338, 443);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(null);
		
		
		ArrayList<ContoCorrente> elencoConti = u.getContigestiti();
		
		panel.setPreferredSize(new Dimension(500, 1000));
		
		for(int i=0;i<elencoConti.size();++i) {
			ContoCorrente conto = elencoConti.get(i);	
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(0,0,0));
			panel_1.setBounds(0, 43, 338, 43);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1_3 = new JLabel(conto.getIban());
			lblNewLabel_1_3.setBounds(17, 13+10+(conto.getTransazioni().size()*31), 237, 21);
			lblNewLabel_1_3.setForeground(Color.WHITE);
			lblNewLabel_1_3.setFont(new Font("Helvetica", Font.BOLD, 14));
			panel_1.add(lblNewLabel_1_3);
			

			
			/*for(int j=0;j<conto.getTransazioni().size();++j) {
				Transazione transazione = conto.getTransazioni().get(j);
				
				JLabel lblNewLabel_1_3_1 = new JLabel((transazione.getTipo().equals("entrata") ? "+" : "-") + " " + controller.formatMoney(transazione.getValore()));
				lblNewLabel_1_3_1.setForeground(Color.WHITE);
				lblNewLabel_1_3_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
				lblNewLabel_1_3_1.setBounds(11, 5+31*j, 68, 21);
				panel_1_1.add(lblNewLabel_1_3_1);
				
				JLabel lblNewLabel_1_3_1_1 = new JLabel(transazione.getDescrizione());
				lblNewLabel_1_3_1_1.setForeground(Color.WHITE);
				lblNewLabel_1_3_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
				lblNewLabel_1_3_1_1.setBounds(11, 24, 243, 21);
				panel_1_1.add(lblNewLabel_1_3_1_1);
				
				JButton btnInserisciTransazione = new JButton("+");
				btnInserisciTransazione.setOpaque(true);
				btnInserisciTransazione.setForeground(Color.WHITE);
				btnInserisciTransazione.setFont(new Font("Helvetica", Font.PLAIN, 14));
				btnInserisciTransazione.setBorderPainted(false);
				btnInserisciTransazione.setBackground(new Color(53, 45, 72));
				btnInserisciTransazione.setBounds(237, 5, 59, 40);
				panel_1_1.add(btnInserisciTransazione);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
			}*/
		}
	}
}
