package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class NuovoPortafogli extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private Controller controller = new Controller();
	
	public NuovoPortafogli(final Utente u) {
		setResizable(false);
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setBounds(100, 100, 477, 288);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(172, 163, 175));
		lblNome.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNome.setBounds(20, 18, 267, 13);
		getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField.setBackground(Color.WHITE);
		textField.setBounds(20, 34, 258, 27);
		getContentPane().add(textField);
		
		JLabel lblParoleChiavemax = new JLabel("PAROLE CHIAVE (MAX. 5)");
		lblParoleChiavemax.setForeground(new Color(172, 163, 175));
		lblParoleChiavemax.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblParoleChiavemax.setBounds(20, 87, 267, 13);
		getContentPane().add(lblParoleChiavemax);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(20, 103, 258, 27);
		getContentPane().add(textField_1);
		
		JLabel lblCategorie = new JLabel("CATEGORIE");
		lblCategorie.setForeground(new Color(172, 163, 175));
		lblCategorie.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblCategorie.setBounds(299, 18, 267, 13);
		getContentPane().add(lblCategorie);
		
		ArrayList<Categoria> categorie = controller.getCategorie();
		final ArrayList<JCheckBox> categorie_scelte = new ArrayList<>();
		for(int i=0;i<categorie.size();++i) {
			JCheckBox checkBxCat = new JCheckBox(categorie.get(i).getNome());
			checkBxCat.setForeground(new Color(255, 255, 255));
			checkBxCat.setFont(new Font("Helvetica", Font.PLAIN, 13));
			checkBxCat.setBounds(298, 34+33*i, 128, 23);
			categorie_scelte.add(checkBxCat);
			getContentPane().add(checkBxCat);
			setTitle("Crea un nuovo portafogli");
		}
		
		JButton btnAggiungi = new JButton("+ Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Portafogli p = new Portafogli();
				p.setNome(textField.getText());
				p.setUtente(u);
				try {
					p.setIdportafogli(u.addPortafogli(p));
					p.setParoleChiaveString(textField_1.getText());
					for(JCheckBox j : categorie_scelte) {
						if(j.isSelected()) {
							Categoria c = new Categoria();
							c.setNome(j.getText());
							p.addCategoria(c);
						}
					}
					JOptionPane.showMessageDialog(null, "Portafogli creato con successo");
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
		btnAggiungi.setBounds(20, 203, 258, 30);
		getContentPane().add(btnAggiungi);
		
		JLabel lblInserisciLeParole = new JLabel("<html>Inserisci le parole chiave separate da una virgola, serviranno per la funzione di sincronizzazione automatica delle transazioni.</html>");
		lblInserisciLeParole.setForeground(new Color(172, 163, 175));
		lblInserisciLeParole.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblInserisciLeParole.setBounds(20, 142, 258, 49);
		getContentPane().add(lblInserisciLeParole);
	}
}
