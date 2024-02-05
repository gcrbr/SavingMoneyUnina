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
import it.unina.maven.SavingMoneyUnina.entities.Carta;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class InformazioniConto extends JFrame {
	private NavigationController n_controller = new NavigationController();
	private DataController d_controller = new DataController();
	
	private final JFrame caller;
	private final ContoCorrente cc;
	
	public InformazioniConto(final JFrame caller, final ContoCorrente cc) {
		this.caller = caller;
		this.cc = cc;
		setResizable(false);
		setTitle("Informazioni conto");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setSize(600, 560);
		setLocationRelativeTo(null);
		this.load();
	}
	
	public void load() {
		Carta c = cc.getCarta();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(17, 40, 210, 50);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel(d_controller.formatMoney(cc.getSaldo()));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblNewLabel.setBounds(23, 6, 181, 38);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(35, 21, 40));
		panel_1.setBounds(17, 119, 210, 209);
		getContentPane().add(panel_1);
		
		JLabel lblNumcarta = new JLabel(c.getNumero());
		lblNumcarta.setForeground(Color.WHITE);
		lblNumcarta.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNumcarta.setBounds(16, 17, 181, 24);
		panel_1.add(lblNumcarta);
		
		JLabel lblScad = new JLabel(c.getScadenza().toString());
		lblScad.setForeground(Color.WHITE);
		lblScad.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblScad.setBounds(16, 56, 181, 24);
		panel_1.add(lblScad);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Numero");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(16, 6, 105, 20);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Scadenza");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(16, 46, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Codice");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(16, 84, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblCod = new JLabel(c.getCvv());
		lblCod.setForeground(Color.WHITE);
		lblCod.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCod.setBounds(16, 98, 181, 24);
		panel_1.add(lblCod);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Tipologia");
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(16, 122, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblTipo = new JLabel(c.getTipo());
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblTipo.setBounds(16, 135, 181, 24);
		panel_1.add(lblTipo);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel(c.getTipo().equals("credito") ? "Plafond": "Limite spesa");
		lblNewLabel_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_2.setBounds(16, 165, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblPla = new JLabel(d_controller.formatMoney(c.getTipo().equals("credito") ? c.getPlafond() : c.getLimitespesa()).toString());
		lblPla.setForeground(Color.WHITE);
		lblPla.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblPla.setBounds(16, 179, 181, 24);
		panel_1.add(lblPla);
		
		ArrayList<Transazione> listaTransazioni = cc.getTransazioni();
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(243, 40, 339, 480);
		getContentPane().add(panel_4);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(35, 21, 40));
		panel_2_1.setPreferredSize(new Dimension(500, 73*listaTransazioni.size()));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(6, 6, 337, 390);
		panel_4.add(scrollPane_1);
		
		for(int i=0; i<listaTransazioni.size(); ++i) {
			final Transazione t = listaTransazioni.get(i);

			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+73*i, 350, 63);
			panel_2_1.add(panel_3_1);
			
			JLabel lblNewLabel_1_2_3 = new JLabel((t.getTipo().equals("entrata") ? "+" : "-") + " " + d_controller.formatMoney(t.getValore()));
			lblNewLabel_1_2_3.setForeground(Color.WHITE);
			lblNewLabel_1_2_3.setFont(new Font("Helvetica", Font.BOLD, 14));
			lblNewLabel_1_2_3.setBackground(Color.WHITE);
			lblNewLabel_1_2_3.setBounds(6, 6, 131, 13);
			panel_3_1.add(lblNewLabel_1_2_3);
			
			JLabel lblNewLabel_1_2_1 = new JLabel(t.getDescrizione());
			lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
			lblNewLabel_1_2_1.setBackground(Color.WHITE);
			lblNewLabel_1_2_1.setBounds(6, 24, 350, 13);
			panel_3_1.add(lblNewLabel_1_2_1);
			
			JLabel lblNewLabel_1_2_4 = new JLabel(d_controller.dateToString(t.getData()));
			lblNewLabel_1_2_4.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2_4.setFont(new Font("Helvetica", Font.ITALIC, 13));
			lblNewLabel_1_2_4.setBackground(Color.WHITE);
			lblNewLabel_1_2_4.setBounds(6, 44, 350, 13);
			panel_3_1.add(lblNewLabel_1_2_4);
			
			JButton btnDelTransazione = new JButton("-");
			btnDelTransazione.setOpaque(true);
			btnDelTransazione.setForeground(Color.WHITE);
			btnDelTransazione.setFont(new Font("Helvetica", Font.BOLD, 16));
			btnDelTransazione.setBorderPainted(false);
			btnDelTransazione.setBackground(new Color(35, 21, 40));
			btnDelTransazione.setBounds(255, 6, 50, 52);
			btnDelTransazione.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int reply = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler rimuovere questa transazione?", "Avviso", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							cc.removeTransazione(t);
							getContentPane().removeAll();
							load();
							revalidate();
							repaint();
						}
					}catch(SQLException e1) {
						n_controller.showAlert("Si è verificato un errore interno: " + e1.getLocalizedMessage());
					}catch(Exception e2) {
						n_controller.showAlert("Si è verificato un errore: " + e2.getLocalizedMessage());
					}
				}
			});
			panel_3_1.add(btnDelTransazione);
		}
		
		JButton btnInserisciTransazione = new JButton("+ Inserisci transazione");
		btnInserisciTransazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n_controller.showNuovaTransazione(InformazioniConto.this, cc);
			}
		});
		btnInserisciTransazione.setOpaque(true);
		btnInserisciTransazione.setForeground(Color.WHITE);
		btnInserisciTransazione.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnInserisciTransazione.setBorderPainted(false);
		btnInserisciTransazione.setBackground(new Color(53, 45, 72));
		btnInserisciTransazione.setBounds(9, 400, 324, 28);
		panel_4.add(btnInserisciTransazione);
		
		JButton btnRicercaAvanzata = new JButton("Ricerca avanzata");
		btnRicercaAvanzata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n_controller.showRicercaAvanzata(cc.getUtente());
			}
		});
		btnRicercaAvanzata.setOpaque(true);
		btnRicercaAvanzata.setForeground(Color.WHITE);
		btnRicercaAvanzata.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnRicercaAvanzata.setBorderPainted(false);
		btnRicercaAvanzata.setBackground(new Color(53, 45, 72));
		btnRicercaAvanzata.setBounds(9, 436, 324, 28);
		panel_4.add(btnRicercaAvanzata);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(35, 21, 40));
		panel_1_1.setBounds(17, 362, 210, 67);
		getContentPane().add(panel_1_1);
		
		JLabel lbIban = new JLabel(cc.getIban());
		lbIban.setForeground(Color.WHITE);
		lbIban.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lbIban.setBounds(16, 33, 181, 24);
		panel_1_1.add(lbIban);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("IBAN");
		lblNewLabel_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_2.setBounds(16, 16, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1 = new JLabel("SALDO");
		lblNewLabel_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(19, 21, 61, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ELENCO DELLE TRANSAZIONI");
		lblNewLabel_1_2.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(243, 21, 201, 16);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("DATI DELLA CARTA");
		lblNewLabel_1_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(17, 102, 190, 16);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("DATI DEL CONTO");
		lblNewLabel_1_1_2.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_1_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(17, 346, 190, 16);
		getContentPane().add(lblNewLabel_1_1_2);
	}
}
