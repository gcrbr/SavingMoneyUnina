package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import it.unina.maven.SavingMoneyUnina.control.DataController;
import it.unina.maven.SavingMoneyUnina.control.NavigationController;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class SceltaManualeTransazione extends JFrame {
	private NavigationController n_controller = new NavigationController();
	private DataController d_controller = new DataController();
	
	public SceltaManualeTransazione(final JFrame caller, final Utente u, final Portafogli p) {
		setResizable(false);
		setTitle("Scegli una transazione da aggiungere");
		getContentPane().setBackground(new Color(28, 21, 40));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(28, 21, 40));
		setSize(338, 443);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		panel.setLayout(null);
		
		ArrayList<ContoCorrente> conti = u.getContigestiti();
		
		int h = 0;
		for(ContoCorrente c : conti) {
			h += 20 + (73+15)*c.getTransazioni().size();
		}
		
		panel.setPreferredSize(new Dimension(500, h));
		
		int startH = 0;
		for(int i=0;i<conti.size();++i) {
			ContoCorrente c = conti.get(i);
			ArrayList<Transazione> transazioni = c.getTransazioni();
			
			if(i>0) {
				startH += 6 + 12 + (73+15)*conti.get(i-1).getTransazioni().size();
			}
			
			JPanel bloccoConto = new JPanel();
			bloccoConto.setBackground(new Color(35, 21, 40));
			bloccoConto.setBounds(6, (i==0) ? 6 : startH, 326, 10+(73+15)*transazioni.size());
			panel.add(bloccoConto);
			bloccoConto.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Conto " + c.getIban());
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Helvetica", Font.BOLD, 14));
			lblNewLabel_1.setBounds(6, 6, 304, 25);
			bloccoConto.add(lblNewLabel_1);
			
			for(int j=0;j<transazioni.size();++j) {
				final Transazione t = transazioni.get(j);
				
				JPanel bloccoTransazione = new JPanel();
				bloccoTransazione.setBackground(new Color(28, 21, 40));
				bloccoTransazione.setBounds(6, 37+83*j, 314, 73);
				bloccoConto.add(bloccoTransazione);
				bloccoTransazione.setLayout(null);
				
				JLabel lblNewLabel_1_1 = new JLabel((t.getTipo().equals("entrata") ? "+" : "-") + " " + d_controller.formatMoney(t.getValore()));
				lblNewLabel_1_1.setForeground(Color.WHITE);
				lblNewLabel_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
				lblNewLabel_1_1.setBounds(16, 6, 300, 25);
				bloccoTransazione.add(lblNewLabel_1_1);
				
				JLabel lblNewLabel_1_1_1 = new JLabel(t.getDescrizione());
				lblNewLabel_1_1_1.setForeground(Color.WHITE);
				lblNewLabel_1_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
				lblNewLabel_1_1_1.setBounds(16, 22, 292, 25);
				bloccoTransazione.add(lblNewLabel_1_1_1);
				
				JLabel lblNewLabel_1_2_4 = new JLabel(d_controller.dateToString(t.getData()));
				lblNewLabel_1_2_4.setForeground(new Color(255, 255, 255));
				lblNewLabel_1_2_4.setFont(new Font("Helvetica", Font.ITALIC, 13));
				lblNewLabel_1_2_4.setBackground(Color.WHITE);
				lblNewLabel_1_2_4.setBounds(16, 44, 350, 13);
				bloccoTransazione.add(lblNewLabel_1_2_4);
				
				JButton btnInserisciTransazione = new JButton("+");
				btnInserisciTransazione.setOpaque(true);
				btnInserisciTransazione.setForeground(Color.WHITE);
				btnInserisciTransazione.setFont(new Font("Helvetica", Font.BOLD, 20));
				btnInserisciTransazione.setBorderPainted(false);
				btnInserisciTransazione.setBackground(new Color(53, 45, 72));
				btnInserisciTransazione.setBounds(242, 6, 66, 52);
				bloccoTransazione.add(btnInserisciTransazione);
				
				btnInserisciTransazione.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							p.addTransazione(t);
							
							((InformazioniPortafogli)caller).getContentPane().removeAll();
							((InformazioniPortafogli)caller).load();
							((InformazioniPortafogli)caller).revalidate();
							((InformazioniPortafogli)caller).repaint();
							
							n_controller.showAlert("Transazione aggiunta con successo");
							setVisible(false);
						}catch(SQLException e1) {
							n_controller.showAlert("Si è verificato un errore interno: " + e1.getLocalizedMessage());
						}catch(Exception e2) {
							n_controller.showAlert("Si è verificato un errore: " + e2.getLocalizedMessage());
						}
					}
				});
			}
		}
		
		getContentPane().add(scrollPane);
		
	}
}
