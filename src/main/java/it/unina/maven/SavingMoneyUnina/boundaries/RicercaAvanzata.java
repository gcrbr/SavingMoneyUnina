package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.unina.maven.SavingMoneyUnina.control.DataController;
import it.unina.maven.SavingMoneyUnina.control.NavigationController;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;
import it.unina.maven.SavingMoneyUnina.entities.Utente;
import it.unina.maven.SavingMoneyUnina.entities.dao.TransazioneDao;

public class RicercaAvanzata extends JFrame {
	private JTextField giornoDal;
	private JTextField meseDal;
	private JTextField annoDal;
	private JTextField giornoAl;
	private JTextField meseAl;
	private JTextField annoAl;
	
	private Utente u;
	
	private NavigationController n_controller = new NavigationController();
	private DataController d_controller = new DataController();
	
	public RicercaAvanzata(final Utente u) {
		this.u = u;
		setResizable(false);
		setBackground(new Color(28, 21, 40));
		setSize(689, 411);
		setLocationRelativeTo(null);
		setTitle("Ricerca avanzata");
		load();
	}
	
	public void load() {
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		
		JLabel lblContoCorrente = new JLabel("CONTO CORRENTE");
		lblContoCorrente.setForeground(new Color(172, 163, 175));
		lblContoCorrente.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblContoCorrente.setBounds(24, 25, 267, 13);
		getContentPane().add(lblContoCorrente);
		
		JLabel lblPortafogli = new JLabel("PORTAFOGLI");
		lblPortafogli.setForeground(new Color(172, 163, 175));
		lblPortafogli.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblPortafogli.setBounds(24, 93, 267, 13);
		getContentPane().add(lblPortafogli);
		
		ArrayList<String> listaConti = new ArrayList<>();
		listaConti.add("Nessuna selezione");
		for(ContoCorrente c : u.getContigestiti() ) {
			listaConti.add(c.getIban() + " (Carta " + c.getCarta().getNumero() + ")");
		}
		
		ArrayList<String> listaPortafogli = new ArrayList<>();
		listaPortafogli.add("Nessuna selezione");
		for(Portafogli p : u.getPortafogli()) {
			listaPortafogli.add(p.getNome());
		}
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(listaConti.toArray()));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(24, 40, 258, 27);
		getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(listaPortafogli.toArray()));
		comboBox_1_1.setSelectedIndex(0);
		comboBox_1_1.setBounds(24, 108, 258, 27);
		getContentPane().add(comboBox_1_1);
		
		JLabel lblDal = new JLabel("DAL");
		lblDal.setForeground(new Color(172, 163, 175));
		lblDal.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblDal.setBounds(24, 155, 258, 13);
		getContentPane().add(lblDal);
		
		JLabel lblGiorno = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno.setForeground(new Color(172, 163, 175));
		lblGiorno.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno.setBounds(24, 172, 267, 13);
		getContentPane().add(lblGiorno);
		
		giornoDal = new JTextField();
		giornoDal.setFont(new Font("Helvetica", Font.PLAIN, 13));
		giornoDal.setColumns(10);
		giornoDal.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		giornoDal.setBackground(Color.WHITE);
		giornoDal.setBounds(24, 183, 80, 27);
		getContentPane().add(giornoDal);
		
		meseDal = new JTextField();
		meseDal.setFont(new Font("Helvetica", Font.PLAIN, 13));
		meseDal.setColumns(10);
		meseDal.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		meseDal.setBackground(Color.WHITE);
		meseDal.setBounds(113, 183, 80, 27);
		getContentPane().add(meseDal);
		
		annoDal = new JTextField();
		annoDal.setFont(new Font("Helvetica", Font.PLAIN, 13));
		annoDal.setColumns(10);
		annoDal.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		annoDal.setBackground(Color.WHITE);
		annoDal.setBounds(202, 183, 80, 27);
		getContentPane().add(annoDal);
		
		JLabel lblAl = new JLabel("AL");
		lblAl.setForeground(new Color(172, 163, 175));
		lblAl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblAl.setBounds(24, 231, 258, 13);
		getContentPane().add(lblAl);
		
		giornoAl = new JTextField();
		giornoAl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		giornoAl.setColumns(10);
		giornoAl.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		giornoAl.setBackground(Color.WHITE);
		giornoAl.setBounds(24, 259, 80, 27);
		getContentPane().add(giornoAl);
		
		meseAl = new JTextField();
		meseAl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		meseAl.setColumns(10);
		meseAl.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		meseAl.setBackground(Color.WHITE);
		meseAl.setBounds(113, 259, 80, 27);
		getContentPane().add(meseAl);
		
		annoAl = new JTextField();
		annoAl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		annoAl.setColumns(10);
		annoAl.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		annoAl.setBackground(Color.WHITE);
		annoAl.setBounds(202, 259, 80, 27);
		getContentPane().add(annoAl);
		
		JLabel lblGiorno_1 = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno_1.setForeground(new Color(172, 163, 175));
		lblGiorno_1.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno_1.setBounds(24, 248, 267, 13);
		getContentPane().add(lblGiorno_1);
		
		JButton btnCerca = new JButton("Cerca");
		
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 21, 40));
		
		btnCerca.setOpaque(true);
		btnCerca.setForeground(Color.WHITE);
		btnCerca.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnCerca.setBorderPainted(false);
		btnCerca.setBackground(new Color(53, 45, 72));
		btnCerca.setBounds(24, 332, 258, 30);
		getContentPane().add(btnCerca);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setPreferredSize(new Dimension(500, 0));
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(303, 40, 367, 322);
		getContentPane().add(panel_4);
		
		final JScrollPane scrollPane_1 = new JScrollPane(panel);
		panel.setLayout(null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(6, 6, 355, 310);
		panel_4.add(scrollPane_1);
		
		JLabel lblRisultati = new JLabel("RISULTATI");
		lblRisultati.setForeground(new Color(172, 163, 175));
		lblRisultati.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblRisultati.setBounds(303, 22, 267, 13);
		getContentPane().add(lblRisultati);
		
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedIndex() == 0 && comboBox_1_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Devi scegliere almeno un criterio di ricerca: Portafogli o ContoCorrente");
					return;
				}
				if(giornoDal.getText().isEmpty() || meseDal.getText().isEmpty() || annoDal.getText().isEmpty() || giornoAl.getText().isEmpty() || meseAl.getText().isEmpty() || annoAl.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Devi inserire l'intervallo di date");
					return;
				}
				Date date_from = d_controller.getDate(
						Integer.parseInt(annoDal.getText()),
						Integer.parseInt(meseDal.getText()),
						Integer.parseInt(giornoDal.getText())
				);
				Date date_to = d_controller.getDate(
						Integer.parseInt(annoAl.getText()),
						Integer.parseInt(meseAl.getText()),
						Integer.parseInt(giornoAl.getText())
				);
				
				ArrayList<Transazione> risultati = new ArrayList<>();
				
				try {
					if(comboBox_1.getSelectedIndex() != 0 && comboBox_1_1.getSelectedIndex() == 0) {
						risultati = new TransazioneDao().cercaTransazione(u.getContigestiti().get(comboBox_1.getSelectedIndex()-1), date_from, date_to);
					}
					if(comboBox_1.getSelectedIndex() == 0 && comboBox_1_1.getSelectedIndex() != 0) {
						risultati = new TransazioneDao().cercaTransazione(u.getPortafogli().get(comboBox_1_1.getSelectedIndex()-1), date_from, date_to);
					}
					if(comboBox_1.getSelectedIndex() != 0 && comboBox_1_1.getSelectedIndex() != 0) {
						risultati = new TransazioneDao().cercaTransazione(u.getContigestiti().get(comboBox_1.getSelectedIndex()-1), u.getPortafogli().get(comboBox_1_1.getSelectedIndex()-1), date_from, date_to);
					}
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
				}

				panel.removeAll();
				
				if(risultati.size() == 0) {
					JLabel lblNessunR = new JLabel("Nessun risultato");
					lblNessunR.setForeground(Color.WHITE);
					lblNessunR.setFont(new Font("Helvetica", Font.PLAIN, 13));
					lblNessunR.setBounds(6, 6, 100, 50);
					panel.add(lblNessunR);
				}
				
				panel.setPreferredSize(new Dimension(500, 73*risultati.size()));
				
				for(int i=0; i<risultati.size(); ++i) {
					final Transazione t = risultati.get(i);
					JPanel panel_3_1 = new JPanel();
					panel_3_1.setLayout(null);
					panel_3_1.setBackground(new Color(53, 45, 72));
					panel_3_1.setBounds(2, 6+73*i, 350, 63);
					panel.add(panel_3_1);
					
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
				}
				
				panel.revalidate();
				panel.repaint();
			}
		});
	}
}