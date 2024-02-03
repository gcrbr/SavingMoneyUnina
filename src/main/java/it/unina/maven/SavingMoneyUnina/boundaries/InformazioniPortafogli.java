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

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class InformazioniPortafogli extends JFrame {
	private Controller controller = new Controller();
	
	private final Utente u;
	private final Portafogli p;
	
	public InformazioniPortafogli(final Utente u, final Portafogli p) {
		this.u = u;
		this.p = p;
		setResizable(false);
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setSize(632, 570);
		setLocationRelativeTo(null);
		this.load();
	}
	
	public void load() {
		ArrayList<Transazione> transazioni = p.getTransazioni();
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(246, 49, 367, 476);
		//panel_4.setPreferredSize(new Dimension(500, ));
		getContentPane().add(panel_4);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setPreferredSize(new Dimension(500, 73*transazioni.size()));
		panel_2_1.setBackground(new Color(35, 21, 40));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(6, 6, 365, 385);
		panel_4.add(scrollPane_1);
		
		JButton btnInserisciTransazione = new JButton("+ Inserisci transazione");
		btnInserisciTransazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showSceltaManuale(InformazioniPortafogli.this, u, p);
			}
		});
		btnInserisciTransazione.setOpaque(true);
		btnInserisciTransazione.setForeground(Color.WHITE);
		btnInserisciTransazione.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnInserisciTransazione.setBorderPainted(false);
		btnInserisciTransazione.setBackground(new Color(53, 45, 72));
		btnInserisciTransazione.setBounds(9, 403, 352, 28);
		panel_4.add(btnInserisciTransazione);
		
		JButton btnRicercaAvanzata = new JButton("Ricerca avanzata");
		btnRicercaAvanzata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showRicercaAvanzata(u);
			}
		});
		btnRicercaAvanzata.setOpaque(true);
		btnRicercaAvanzata.setForeground(Color.WHITE);
		btnRicercaAvanzata.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnRicercaAvanzata.setBorderPainted(false);
		btnRicercaAvanzata.setBackground(new Color(53, 45, 72));
		btnRicercaAvanzata.setBounds(9, 442, 352, 28);
		panel_4.add(btnRicercaAvanzata);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(24, 49, 210, 218);
		getContentPane().add(panel);
		
		JLabel lbl1 = new JLabel("Nome");
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lbl1.setBounds(23, 18, 105, 20);
		panel.add(lbl1);
		
		JLabel lbl2 = new JLabel(p.getNome());
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lbl2.setBounds(23, 34, 181, 24);
		panel.add(lbl2);
		
		JLabel lblTransazioni = new JLabel("Numero transazioni");
		lblTransazioni.setForeground(Color.WHITE);
		lblTransazioni.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblTransazioni.setBounds(23, 70, 145, 18);
		panel.add(lblTransazioni);
		
		JLabel lblNumtrans = new JLabel(Integer.toString(p.getTransazioni().size()));
		lblNumtrans.setForeground(Color.WHITE);
		lblNumtrans.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNumtrans.setBounds(23, 85, 181, 18);
		panel.add(lblNumtrans);
		
		JLabel lblParoleChiave = new JLabel("Parole chiave");
		lblParoleChiave.setForeground(Color.WHITE);
		lblParoleChiave.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblParoleChiave.setBounds(23, 115, 105, 20);
		panel.add(lblParoleChiave);
		
		String paroleChiave = p.getParoleChiaveString();
		JLabel lblParolechiave = new JLabel((paroleChiave==null||paroleChiave.isEmpty()) ? "Nessuna" : paroleChiave);
		lblParolechiave.setForeground(Color.WHITE);
		lblParolechiave.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblParolechiave.setBounds(23, 131, 181, 22);
		panel.add(lblParolechiave);
		
		JLabel lblParoleChiave_1 = new JLabel("Categorie");
		lblParoleChiave_1.setForeground(Color.WHITE);
		lblParoleChiave_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblParoleChiave_1.setBounds(23, 163, 105, 20);
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
		lblCategorie.setBounds(23, 180, 181, 24);
		panel.add(lblCategorie);
		
		JLabel lblNewLabel_1 = new JLabel("PORTAFOGLI");
		lblNewLabel_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(24, 30, 146, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ELENCO DELLE TRANSAZIONI");
		lblNewLabel_1_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(246, 29, 222, 16);
		getContentPane().add(lblNewLabel_1_1);
		setTitle("Informazioni portafogli");
		
		for(int i=0; i<transazioni.size(); ++i) {
			final Transazione t = transazioni.get(i);
			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+73*i, 350, 63);
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
			lblNewLabel_1_2_1.setBounds(6, 24, 350, 13);
			panel_3_1.add(lblNewLabel_1_2_1);
			
			JLabel lblNewLabel_1_2_4 = new JLabel(controller.dateToString(t.getData()));
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
			btnDelTransazione.setBounds(295, 6, 50, 52);
			btnDelTransazione.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						p.deleteTransazione(t);
						
						getContentPane().removeAll();
						load();
						revalidate();
						repaint();
						
						JOptionPane.showMessageDialog(null, "Transazione rimossa");
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
					}
				}
			});		
			panel_3_1.add(btnDelTransazione);
		}
	}
}
