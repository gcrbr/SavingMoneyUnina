package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.Carta;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class NuovoConto extends JFrame {
	private JTextField iban;
	private JTextField saldo;
	private JTextField numcarta;
	private JTextField giornoscad;
	private JTextField mesescad;
	private JTextField annoscad;
	private JTextField cvv;
	private JTextField jolly;
	private Controller controller = new Controller();
	
	public NuovoConto(final JFrame caller, final Utente utente) {
		setResizable(false);
		setTitle("Aggiungi conto");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setBounds(100, 100, 294, 579);
		
		JLabel lblNewLabel = new JLabel("IBAN");
		lblNewLabel.setBounds(19, 25, 267, 13);
		lblNewLabel.setForeground(new Color(172, 163, 175));
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		getContentPane().add(lblNewLabel);
		
		iban = new JTextField();
		iban.setFont(new Font("Helvetica", Font.PLAIN, 13));
		iban.setColumns(10);
		iban.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		iban.setBackground(Color.WHITE);
		iban.setBounds(19, 41, 258, 27);
		getContentPane().add(iban);
		
		saldo = new JTextField();
		saldo.setFont(new Font("Helvetica", Font.PLAIN, 13));
		saldo.setColumns(10);
		saldo.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		saldo.setBackground(Color.WHITE);
		saldo.setBounds(19, 110, 258, 27);
		getContentPane().add(saldo);
		
		JLabel lblSaldo = new JLabel("SALDO");
		lblSaldo.setForeground(new Color(172, 163, 175));
		lblSaldo.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblSaldo.setBounds(19, 94, 267, 13);
		getContentPane().add(lblSaldo);
		
		JLabel lblNumeroDellaCarta = new JLabel("NUMERO DELLA CARTA");
		lblNumeroDellaCarta.setForeground(new Color(172, 163, 175));
		lblNumeroDellaCarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNumeroDellaCarta.setBounds(19, 164, 267, 13);
		getContentPane().add(lblNumeroDellaCarta);
		
		numcarta = new JTextField();
		numcarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		numcarta.setColumns(10);
		numcarta.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		numcarta.setBackground(Color.WHITE);
		numcarta.setBounds(19, 180, 258, 27);
		getContentPane().add(numcarta);
		
		giornoscad = new JTextField();
		giornoscad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		giornoscad.setColumns(10);
		giornoscad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		giornoscad.setBackground(Color.WHITE);
		giornoscad.setBounds(19, 386, 80, 27);
		getContentPane().add(giornoscad);
		
		JLabel lblScadenza = new JLabel("SCADENZA");
		lblScadenza.setForeground(new Color(172, 163, 175));
		lblScadenza.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblScadenza.setBounds(19, 358, 450, 13);
		getContentPane().add(lblScadenza);
		
		mesescad = new JTextField();
		mesescad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		mesescad.setColumns(10);
		mesescad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		mesescad.setBackground(Color.WHITE);
		mesescad.setBounds(108, 386, 80, 27);
		getContentPane().add(mesescad);
		
		annoscad = new JTextField();
		annoscad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		annoscad.setColumns(10);
		annoscad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		annoscad.setBackground(Color.WHITE);
		annoscad.setBounds(197, 386, 80, 27);
		getContentPane().add(annoscad);
		
		JLabel lblGiorno = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno.setForeground(new Color(172, 163, 175));
		lblGiorno.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno.setBounds(19, 375, 267, 13);
		getContentPane().add(lblGiorno);
		
		cvv = new JTextField();
		cvv.setFont(new Font("Helvetica", Font.PLAIN, 13));
		cvv.setColumns(10);
		cvv.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		cvv.setBackground(Color.WHITE);
		cvv.setBounds(19, 451, 258, 27);
		getContentPane().add(cvv);
		
		JLabel lblCodiceDiSicurezza = new JLabel("CODICE DI SICUREZZA");
		lblCodiceDiSicurezza.setForeground(new Color(172, 163, 175));
		lblCodiceDiSicurezza.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblCodiceDiSicurezza.setBounds(19, 435, 267, 13);
		getContentPane().add(lblCodiceDiSicurezza);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Credito", "Debito"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(19, 243, 258, 27);
		getContentPane().add(comboBox);
		
		final JLabel plafondLbl = new JLabel("PLAFOND");
		plafondLbl.setForeground(new Color(172, 163, 175));
		plafondLbl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		plafondLbl.setBounds(19, 292, 267, 13);
		getContentPane().add(plafondLbl);
		
		JButton btnAggiungi = new JButton("+ Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					Carta c = new Carta();
					c.setNumero(numcarta.getText());
					c.setScadenza(new Date(
							Integer.parseInt(annoscad.getText()),
							Integer.parseInt(mesescad.getText()),
							Integer.parseInt(giornoscad.getText())
					));
					c.setCvv(cvv.getText());
					c.setTipo(comboBox.getSelectedItem().toString().toLowerCase());
					
					if(c.getTipo().equals("credito")) {
						c.setPlafond(Double.parseDouble(plafondLbl.getText()));
					}else {
						c.setLimitespesa(Double.parseDouble(plafondLbl.getText()));
					}
					
					ContoCorrente cc = new ContoCorrente();
					cc.setCarta(c);
					cc.setIban(iban.getText());
					cc.setSaldo(Double.parseDouble(saldo.getText()));
					
					utente.addContoCorrente(cc, c);
					utente.refreshContiGestiti();
					
					controller.reloadFrame(caller);
					
					JOptionPane.showMessageDialog(null, "Conto aggiunto con successo");
					setVisible(false);
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
				}
			}
		});
		btnAggiungi.setOpaque(true);
		btnAggiungi.setForeground(Color.WHITE);
		btnAggiungi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnAggiungi.setBorderPainted(false);
		btnAggiungi.setBackground(new Color(53, 45, 72));
		btnAggiungi.setBounds(19, 502, 258, 30);
		getContentPane().add(btnAggiungi);
		
		
		
		JLabel lblTipoCarta = new JLabel("TIPO CARTA");
		lblTipoCarta.setForeground(new Color(172, 163, 175));
		lblTipoCarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblTipoCarta.setBounds(19, 230, 267, 13);
		getContentPane().add(lblTipoCarta);
		
		jolly = new JTextField();
		jolly.setFont(new Font("Helvetica", Font.PLAIN, 13));
		jolly.setColumns(10);
		jolly.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		jolly.setBackground(Color.WHITE);
		jolly.setBounds(19, 308, 258, 27);
		getContentPane().add(jolly);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 0) { // credito
					plafondLbl.setText("PLAFOND");
				}else if(comboBox.getSelectedIndex() == 1) { // debito
					plafondLbl.setText("LIMITE SPESA");
				}
			}
		});
	}
}
