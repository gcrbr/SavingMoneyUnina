package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.Carta;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class InformazioniConto extends JFrame {
	private Controller controller = new Controller();
	public InformazioniConto(final JFrame caller, final ContoCorrente cc) {
		setResizable(false);
		Carta c = cc.getCarta();
		setTitle("Informazioni conto");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setBounds(100, 100, 473, 560);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(17, 19, 210, 85);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Saldo");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(18, 2, 105, 46);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(controller.formatMoney(cc.getSaldo()));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblNewLabel.setBounds(18, 34, 181, 46);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(35, 21, 40));
		panel_1.setBounds(17, 119, 210, 181);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Dati carta");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(16, 16, 105, 20);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNumcarta = new JLabel(c.getNumero());
		lblNumcarta.setForeground(Color.WHITE);
		lblNumcarta.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNumcarta.setBounds(102, 36, 181, 24);
		panel_1.add(lblNumcarta);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Numero");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(16, 38, 105, 20);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblScad = new JLabel(c.getScadenza().toString());
		lblScad.setForeground(Color.WHITE);
		lblScad.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblScad.setBounds(102, 62, 181, 24);
		panel_1.add(lblScad);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Scadenza");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(16, 64, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Codice");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(16, 88, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblCod = new JLabel(c.getCvv());
		lblCod.setForeground(Color.WHITE);
		lblCod.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCod.setBounds(102, 86, 181, 24);
		panel_1.add(lblCod);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Tipologia");
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(16, 116, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblTipo = new JLabel(c.getTipo());
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblTipo.setBounds(102, 114, 181, 24);
		panel_1.add(lblTipo);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel(c.getTipo().equals("credito") ? "Plafond": "Limite spesa");
		lblNewLabel_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_2.setBounds(16, 143, 105, 20);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblPla = new JLabel(controller.formatMoney(c.getTipo().equals("credito") ? c.getPlafond() : c.getLimitespesa()).toString());
		lblPla.setForeground(Color.WHITE);
		lblPla.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblPla.setBounds(102, 141, 181, 24);
		panel_1.add(lblPla);
		
		ArrayList<Transazione> listaTransazioni = cc.getTransazioni();
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(243, 19, 210, 501);
		getContentPane().add(panel_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("Elenco delle transazioni");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(19, 6, 156, 46);
		panel_4.add(lblNewLabel_1_3);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(35, 21, 40));
		panel_2_1.setPreferredSize(new Dimension(500, 53*listaTransazioni.size()));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(6, 48, 198, 365);
		panel_4.add(scrollPane_1);
		
		for(int i=0; i<listaTransazioni.size(); ++i) {
			Transazione t = listaTransazioni.get(i);
			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+53*i, 192, 43);
			panel_2_1.add(panel_3_1);
			
			JLabel lblNewLabel_1_2_3 = new JLabel((t.getTipo().equals("entrata") ? "+" : "-") + " " + controller.formatMoney(t.getValore()));
			lblNewLabel_1_2_3.setForeground(Color.WHITE);
			lblNewLabel_1_2_3.setFont(new Font("Helvetica", Font.BOLD, 14));
			lblNewLabel_1_2_3.setBackground(Color.WHITE);
			lblNewLabel_1_2_3.setBounds(6, 6, 131, 13);
			panel_3_1.add(lblNewLabel_1_2_3);
			
			JLabel lblNewLabel_1_2_1 = new JLabel(t.getDescrizione());
			lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
			lblNewLabel_1_2_1.setBackground(Color.WHITE);
			lblNewLabel_1_2_1.setBounds(6, 24, 131, 13);
			panel_3_1.add(lblNewLabel_1_2_1);
		}
		
		JButton btnInserisciTransazione = new JButton("+ Inserisci transazione");
		btnInserisciTransazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNuovaTransazione(InformazioniConto.this, cc);
			}
		});
		btnInserisciTransazione.setOpaque(true);
		btnInserisciTransazione.setForeground(Color.WHITE);
		btnInserisciTransazione.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnInserisciTransazione.setBorderPainted(false);
		btnInserisciTransazione.setBackground(new Color(53, 45, 72));
		btnInserisciTransazione.setBounds(9, 421, 193, 28);
		panel_4.add(btnInserisciTransazione);
		
		JButton btnRicercaAvanzata = new JButton("Ricerca avanzata");
		btnRicercaAvanzata.setOpaque(true);
		btnRicercaAvanzata.setForeground(Color.WHITE);
		btnRicercaAvanzata.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnRicercaAvanzata.setBorderPainted(false);
		btnRicercaAvanzata.setBackground(new Color(53, 45, 72));
		btnRicercaAvanzata.setBounds(9, 460, 193, 28);
		panel_4.add(btnRicercaAvanzata);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(35, 21, 40));
		panel_1_1.setBounds(17, 313, 210, 76);
		getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Dati conto");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(16, 16, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_2);
		
		JLabel lbIban = new JLabel(cc.getIban());
		lbIban.setForeground(Color.WHITE);
		lbIban.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lbIban.setBounds(102, 36, 181, 24);
		panel_1_1.add(lbIban);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("IBAN");
		lblNewLabel_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_2.setBounds(16, 38, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_2);
	}
}
