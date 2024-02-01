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
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class InformazioniPortafogli extends JFrame {
	private Controller controller = new Controller();
	
	public InformazioniPortafogli(final Utente u, final Portafogli p) {
		setResizable(false);
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setBounds(100, 100, 510, 570);
		
		ArrayList<Transazione> transazioni = p.getTransazioni();
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(246, 24, 239, 501);
		panel_4.setPreferredSize(new Dimension(500, 53*transazioni.size()));
		getContentPane().add(panel_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("Elenco delle transazioni");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(19, 6, 156, 46);
		panel_4.add(lblNewLabel_1_3);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setPreferredSize(new Dimension(500, 0));
		panel_2_1.setBackground(new Color(35, 21, 40));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(6, 48, 227, 365);
		panel_4.add(scrollPane_1);
		
		JButton btnInserisciTransazione = new JButton("+ Inserisci transazione");
		btnInserisciTransazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showSceltaManuale(u, p);
			}
		});
		btnInserisciTransazione.setOpaque(true);
		btnInserisciTransazione.setForeground(Color.WHITE);
		btnInserisciTransazione.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnInserisciTransazione.setBorderPainted(false);
		btnInserisciTransazione.setBackground(new Color(53, 45, 72));
		btnInserisciTransazione.setBounds(9, 421, 224, 28);
		panel_4.add(btnInserisciTransazione);
		
		JButton btnRicercaAvanzata = new JButton("Ricerca avanzata");
		btnRicercaAvanzata.setOpaque(true);
		btnRicercaAvanzata.setForeground(Color.WHITE);
		btnRicercaAvanzata.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnRicercaAvanzata.setBorderPainted(false);
		btnRicercaAvanzata.setBackground(new Color(53, 45, 72));
		btnRicercaAvanzata.setBounds(9, 460, 224, 28);
		panel_4.add(btnRicercaAvanzata);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(24, 24, 210, 202);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Portafogli");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(18, 2, 105, 46);
		panel.add(lblNewLabel_1);
		
		JLabel lbl1 = new JLabel("Nome");
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lbl1.setBounds(18, 42, 105, 20);
		panel.add(lbl1);
		
		JLabel lbl2 = new JLabel(p.getNome());
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lbl2.setBounds(104, 40, 181, 24);
		panel.add(lbl2);
		
		JLabel lblTransazioni = new JLabel("Transazioni");
		lblTransazioni.setForeground(Color.WHITE);
		lblTransazioni.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblTransazioni.setBounds(18, 70, 105, 18);
		panel.add(lblTransazioni);
		
		JLabel lblNumtrans = new JLabel(Integer.toString(p.getTransazioni().size()));
		lblNumtrans.setForeground(Color.WHITE);
		lblNumtrans.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNumtrans.setBounds(104, 70, 181, 18);
		panel.add(lblNumtrans);
		
		JLabel lblParoleChiave = new JLabel("Parole chiave");
		lblParoleChiave.setForeground(Color.WHITE);
		lblParoleChiave.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblParoleChiave.setBounds(18, 100, 105, 20);
		panel.add(lblParoleChiave);
		
		JLabel lblParolechiave = new JLabel(p.getParoleChiaveString());
		lblParolechiave.setForeground(Color.WHITE);
		lblParolechiave.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblParolechiave.setBounds(18, 116, 181, 22);
		panel.add(lblParolechiave);
		
		JLabel lblParoleChiave_1 = new JLabel("Categorie");
		lblParoleChiave_1.setForeground(Color.WHITE);
		lblParoleChiave_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblParoleChiave_1.setBounds(18, 148, 105, 20);
		panel.add(lblParoleChiave_1);
		
		String categorie = "";
		ArrayList<Categoria> cc = p.getCategorie();
		if(cc.size() > 0) {
			for(Categoria c : p.getCategorie()) {
				categorie += c.getNome() + ", ";
			}
			categorie = categorie.substring(0, categorie.length()-2);
		}else {
			categorie = "Nessuna";
		}
		
		JLabel lblCategorie = new JLabel(categorie);
		lblCategorie.setForeground(Color.WHITE);
		lblCategorie.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCategorie.setBounds(18, 165, 181, 24);
		panel.add(lblCategorie);
		setTitle("Informazioni portafogli");
		
		for(int i=0; i<transazioni.size(); ++i) {
			Transazione t = transazioni.get(i);
			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+53*i, 220, 43);
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
	}
}
