package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import it.unina.maven.SavingMoneyUnina.control.DataController;
import it.unina.maven.SavingMoneyUnina.control.NavigationController;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class NuovaTransazione extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	final JComboBox comboBox = new JComboBox();
	
	private NavigationController n_controller = new NavigationController();
	private DataController d_controller = new DataController();
	
	public void setIban(String i) {
		textField.setText(i);
	}
	
	public void setImporto(String i) {
		textField_1.setText(i);
	}
	
	public void setDescrizione(String d) {
		textField_2.setText(d);
	}
	
	public void setTipo(String t) {
		if(t.equals("entrata")) {
			comboBox.setSelectedIndex(0);
		}else {
			comboBox.setSelectedIndex(1);
		}
	}
	
	public void setGiorno(String g) {
		textField_3.setText(g);
	}
	
	public void setMese(String m) {
		textField_4.setText(m);
	}
	
	public void setAnno(String a) {
		textField_5.setText(a);
	}
	
	public String getIban() {
		return textField.getText();
	}
	
	public String getImporto() {
		return textField_1.getText();
	}
	
	public String getDescrizione() {
		return textField_2.getText();
	}
	
	public String getTipo() {
		return comboBox.getSelectedItem().toString().toLowerCase();
	}
	
	public String getGiorno() {
		return textField_3.getText();
	}
	
	public String getMese() {
		return textField_4.getText();
	}
	
	public String getAnno() {
		return textField_5.getText();
	}
	
	public boolean hasEmptyFields() {
		return getIban().isEmpty() || getImporto().isEmpty() || getDescrizione().isEmpty() || getTipo().isEmpty() || getGiorno().isEmpty() || getMese().isEmpty() || getAnno().isEmpty();
	}
	
	
	public NuovaTransazione(final JFrame caller, final ContoCorrente cc) {
		setResizable(false);
		setTitle("Aggiungi transazione");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setSize(291, 449);
		setLocationRelativeTo(null);
		
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
		setGiorno(Integer.toString(oggi.getDayOfMonth()));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(107, 315, 80, 27);
		getContentPane().add(textField_4);
		setMese(Integer.toString(oggi.getMonthValue()));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_5.setColumns(10);
		textField_5.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(196, 315, 80, 27);
		getContentPane().add(textField_5);
		setAnno(Integer.toString(oggi.getYear()));
		
		JLabel lblGiorno = new JLabel("GIORNO                MESE                    ANNO");
		lblGiorno.setForeground(new Color(172, 163, 175));
		lblGiorno.setFont(new Font("Helvetica", Font.PLAIN, 10));
		lblGiorno.setBounds(18, 304, 267, 13);
		getContentPane().add(lblGiorno);
		
		JButton btnAggiungi = new JButton("+ Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(hasEmptyFields()) {
					JOptionPane.showMessageDialog(null, "Devi compilare tutti i campi");
					return;
				}
				
				try {
					Transazione t = new Transazione();
					t.setAltroIban(getIban());
					t.setValore(Double.parseDouble(getImporto()));
					t.setDescrizione(getDescrizione());
					t.setTipo(getTipo());
					t.setData(d_controller.getDate(
							Integer.parseInt(getAnno()),
							Integer.parseInt(getMese()),
							Integer.parseInt(getGiorno())
					));
					cc.addTransazione(t);
					
					((InformazioniConto)caller).getContentPane().removeAll();
					((InformazioniConto)caller).load();
					((InformazioniConto)caller).revalidate();
					((InformazioniConto)caller).repaint();
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
