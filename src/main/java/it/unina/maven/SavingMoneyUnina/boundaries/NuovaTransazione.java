package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class NuovaTransazione extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Controller controller = new Controller();
	
	public NuovaTransazione(final JFrame caller, final ContoCorrente cc) {
		setTitle("Aggiungi transazione");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setBounds(100, 100, 291, 449);
		
		final JLabel lblIban = new JLabel("IBAN");
		lblIban.setForeground(new Color(172, 163, 175));
		lblIban.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblIban.setBounds(18, 18, 267, 13);
		getContentPane().add(lblIban);
		
		textField = new JTextField();
		textField.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField.setBackground(Color.WHITE);
		textField.setBounds(18, 34, 258, 27);
		getContentPane().add(textField);
		
		JLabel lblImporto = new JLabel("IMPORTO");
		lblImporto.setForeground(new Color(172, 163, 175));
		lblImporto.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblImporto.setBounds(18, 87, 267, 13);
		getContentPane().add(lblImporto);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(18, 103, 258, 27);
		getContentPane().add(textField_1);
		
		JLabel lblDescrizione = new JLabel("DESCRIZIONE");
		lblDescrizione.setForeground(new Color(172, 163, 175));
		lblDescrizione.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblDescrizione.setBounds(18, 157, 267, 13);
		getContentPane().add(lblDescrizione);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(18, 173, 258, 27);
		getContentPane().add(textField_2);
		
		JLabel lblTipologia = new JLabel("TIPOLOGIA");
		lblTipologia.setForeground(new Color(172, 163, 175));
		lblTipologia.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblTipologia.setBounds(18, 223, 267, 13);
		getContentPane().add(lblTipologia);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 0) {
					lblIban.setText("IBAN ORDINANTE");
				}else {
					lblIban.setText("IBAN BENEFICIARIO");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Entrata", "Uscita"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(18, 236, 258, 27);
		getContentPane().add(comboBox);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setForeground(new Color(172, 163, 175));
		lblData.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblData.setBounds(18, 287, 450, 13);
		getContentPane().add(lblData);
		
		LocalDate oggi = LocalDate.now();
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(18, 315, 80, 27);
		getContentPane().add(textField_3);
		textField_3.setText(Integer.toString(oggi.getDayOfMonth()));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(107, 315, 80, 27);
		getContentPane().add(textField_4);
		textField_4.setText(Integer.toString(oggi.getMonthValue()));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_5.setColumns(10);
		textField_5.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(196, 315, 80, 27);
		getContentPane().add(textField_5);
		textField_5.setText(Integer.toString(oggi.getYear()));
		
		JLabel lblGiorno = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno.setForeground(new Color(172, 163, 175));
		lblGiorno.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno.setBounds(18, 304, 267, 13);
		getContentPane().add(lblGiorno);
		
		JButton btnAggiungi = new JButton("+ Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					Transazione t = new Transazione();
					t.setAltroIban(textField.getText());
					t.setValore(Double.parseDouble(textField_1.getText()));
					t.setDescrizione(textField_2.getText());
					t.setTipo(comboBox.getSelectedItem().toString().toLowerCase());
					t.setData(new Date(
							Integer.parseInt(textField_5.getText()),
							Integer.parseInt(textField_4.getText()),
							Integer.parseInt(textField_3.getText())
					));
					cc.addTransazione(t);
					cc.refreshTransazioni();
					
					JOptionPane.showMessageDialog(null, "Transazione aggiunta con successo.");
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
		btnAggiungi.setBounds(18, 369, 258, 30);
		getContentPane().add(btnAggiungi);
	}

}
