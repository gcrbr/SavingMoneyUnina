package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import it.unina.maven.SavingMoneyUnina.control.DataController;
import it.unina.maven.SavingMoneyUnina.control.NavigationController;
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
	private JTextField limite_plafond;
	
	private NavigationController n_controller = new NavigationController();
	private DataController d_controller = new DataController();
	
	public NuovoConto(final JFrame caller, final Utente utente) {
		setResizable(false);
		setTitle("Aggiungi conto");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setSize(294, 508);
		setLocationRelativeTo(null);
		
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
		
		/*saldo = new JTextField();
		saldo.setFont(new Font("Helvetica", Font.PLAIN, 13));
		saldo.setColumns(10);
		saldo.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		saldo.setBackground(Color.WHITE);
		saldo.setBounds(19, 110, 258, 27);
		saldo.setText("0");
		getContentPane().add(saldo);
		
		JLabel lblSaldo = new JLabel("SALDO");
		lblSaldo.setForeground(new Color(172, 163, 175));
		lblSaldo.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblSaldo.setBounds(19, 94, 267, 13);
		getContentPane().add(lblSaldo);*/
		
		JLabel lblNumeroDellaCarta = new JLabel("NUMERO DELLA CARTA");
		lblNumeroDellaCarta.setForeground(new Color(172, 163, 175));
		lblNumeroDellaCarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNumeroDellaCarta.setBounds(19, 91, 267, 13);
		getContentPane().add(lblNumeroDellaCarta);
		
		numcarta = new JTextField();
		numcarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		numcarta.setColumns(10);
		numcarta.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		numcarta.setBackground(Color.WHITE);
		numcarta.setBounds(19, 107, 258, 27);
		getContentPane().add(numcarta);
		
		giornoscad = new JTextField();
		giornoscad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		giornoscad.setColumns(10);
		giornoscad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		giornoscad.setBackground(Color.WHITE);
		giornoscad.setBounds(19, 319, 80, 27);
		getContentPane().add(giornoscad);
		
		JLabel lblScadenza = new JLabel("SCADENZA");
		lblScadenza.setForeground(new Color(172, 163, 175));
		lblScadenza.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblScadenza.setBounds(19, 291, 450, 13);
		getContentPane().add(lblScadenza);
		
		mesescad = new JTextField();
		mesescad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		mesescad.setColumns(10);
		mesescad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		mesescad.setBackground(Color.WHITE);
		mesescad.setBounds(108, 319, 80, 27);
		getContentPane().add(mesescad);
		
		annoscad = new JTextField();
		annoscad.setFont(new Font("Helvetica", Font.PLAIN, 13));
		annoscad.setColumns(10);
		annoscad.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		annoscad.setBackground(Color.WHITE);
		annoscad.setBounds(197, 319, 80, 27);
		getContentPane().add(annoscad);
		
		JLabel lblGiorno = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno.setForeground(new Color(172, 163, 175));
		lblGiorno.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno.setBounds(19, 308, 267, 13);
		getContentPane().add(lblGiorno);
		
		cvv = new JTextField();
		cvv.setFont(new Font("Helvetica", Font.PLAIN, 13));
		cvv.setColumns(10);
		cvv.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		cvv.setBackground(Color.WHITE);
		cvv.setBounds(19, 387, 258, 27);
		getContentPane().add(cvv);
		
		JLabel lblCodiceDiSicurezza = new JLabel("CODICE DI SICUREZZA");
		lblCodiceDiSicurezza.setForeground(new Color(172, 163, 175));
		lblCodiceDiSicurezza.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblCodiceDiSicurezza.setBounds(19, 371, 267, 13);
		getContentPane().add(lblCodiceDiSicurezza);
		
		final JComboBox tipoCarta = new JComboBox();
		tipoCarta.setForeground(new Color(0, 0, 0));
		tipoCarta.setModel(new DefaultComboBoxModel(new String[] {"Credito", "Debito"}));
		tipoCarta.setSelectedIndex(0);
		tipoCarta.setBounds(19, 170, 258, 27);
		getContentPane().add(tipoCarta);
		
		final JLabel plafondLbl = new JLabel("PLAFOND");
		plafondLbl.setForeground(new Color(172, 163, 175));
		plafondLbl.setFont(new Font("Helvetica", Font.PLAIN, 13));
		plafondLbl.setBounds(19, 224, 267, 13);
		getContentPane().add(plafondLbl);
		
		JButton btnAggiungi = new JButton("+ Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(iban.getText().isEmpty() || numcarta.getText().isEmpty() || giornoscad.getText().isEmpty() || mesescad.getText().isEmpty() || annoscad.getText().isEmpty() || cvv.getText().isEmpty() || limite_plafond.getText().isEmpty()) {
					n_controller.showAlert("Devi compilare tutti i campi");
					return;
				}
				
				try {
					Carta c = new Carta();
					c.setNumero(numcarta.getText());
					c.setScadenza(d_controller.getDate(
							Integer.parseInt(annoscad.getText()),
							Integer.parseInt(mesescad.getText()),
							Integer.parseInt(giornoscad.getText())
					));
					c.setCvv(cvv.getText());
					c.setTipo(tipoCarta.getSelectedItem().toString().toLowerCase());
					
					if(c.getTipo().equals("credito")) {
						c.setPlafond(Double.parseDouble(limite_plafond.getText()));
					}else {
						c.setLimitespesa(Double.parseDouble(limite_plafond.getText()));
					}
					
					ContoCorrente cc = new ContoCorrente();
					
					cc.setCarta(c);
					cc.setIban(iban.getText());
					cc.setSaldo(0);
					
					utente.addContoCorrente(cc, c);
					
					((Home)caller).load();
					
					n_controller.showAlert("Conto aggiunto con successo");
					setVisible(false);
				}catch(SQLException e1) {
					n_controller.showAlert("Si è verificato un errore interno: " + e1.getLocalizedMessage());
				}catch(Exception e2) {
					n_controller.showAlert("Si è verificato un errore: " + e2.getLocalizedMessage());
				}
			}
		});
		btnAggiungi.setOpaque(true);
		btnAggiungi.setForeground(Color.WHITE);
		btnAggiungi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnAggiungi.setBorderPainted(false);
		btnAggiungi.setBackground(new Color(53, 45, 72));
		btnAggiungi.setBounds(19, 430, 258, 30);
		getContentPane().add(btnAggiungi);
		
		JLabel lblTipoCarta = new JLabel("TIPO CARTA");
		lblTipoCarta.setForeground(new Color(172, 163, 175));
		lblTipoCarta.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblTipoCarta.setBounds(19, 157, 267, 13);
		getContentPane().add(lblTipoCarta);
		
		limite_plafond = new JTextField();
		limite_plafond.setFont(new Font("Helvetica", Font.PLAIN, 13));
		limite_plafond.setColumns(10);
		limite_plafond.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		limite_plafond.setBackground(Color.WHITE);
		limite_plafond.setBounds(19, 240, 258, 27);
		getContentPane().add(limite_plafond);
		
		tipoCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipoCarta.getSelectedIndex() == 0) { // credito
					plafondLbl.setText("PLAFOND");
				}else if(tipoCarta.getSelectedIndex() == 1) { // debito
					plafondLbl.setText("LIMITE SPESA");
				}
			}
		});
	}
}
