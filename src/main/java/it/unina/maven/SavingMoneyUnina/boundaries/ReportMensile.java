package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class ReportMensile extends JFrame {
	
	private Utente u;
	private JTextField textField;
	private Controller controller = new Controller();
	
	public ReportMensile(final Utente u) {
		this.u=u;
		setTitle("Report mensile");
		getContentPane().setBackground(new Color(28, 21, 40));
		getContentPane().setLayout(null);
		setSize(311,562);
		setLocationRelativeTo(null);
		setResizable(false);
		load();
	}
	
	public void load() {
		JLabel lblMeseDiRiferimento = new JLabel("MESE");
		lblMeseDiRiferimento.setForeground(new Color(172, 163, 175));
		lblMeseDiRiferimento.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblMeseDiRiferimento.setBounds(22, 25, 267, 13);
		getContentPane().add(lblMeseDiRiferimento);
		
		final JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 21, 40));
		panel.setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() > 0) {
					panel.removeAll();
					try {
						ArrayList<Object[]> report = u.getReportMensile(comboBox.getSelectedIndex(), Integer.parseInt(textField.getText()));
						for(int i=0;i<report.size();++i) {
							Object[] conto = report.get(i);
							
							JPanel panel_1 = new JPanel();
							panel_1.setLayout(null);
							panel_1.setBackground(new Color(35, 21, 40));
							panel_1.setBounds(0, 6+195*i, 270, 185);
							
							JLabel lbl_1 = new JLabel("Conto " + conto[0].toString());
							lbl_1.setForeground(new Color(255, 255, 255));
							lbl_1.setFont(new Font("Helvetica", Font.BOLD, 14));
							lbl_1.setBackground(Color.WHITE);
							lbl_1.setBounds(16, 16, 267, 13);
							panel_1.add(lbl_1);
							
							JLabel lbl_2 = new JLabel("Entrata massima");
							lbl_2.setForeground(new Color(255, 255, 255));
							lbl_2.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_2.setBackground(Color.WHITE);
							lbl_2.setBounds(16, 36, 267, 13);
							panel_1.add(lbl_2);
							
							JLabel lbl_3 = new JLabel(controller.formatMoney((Double)conto[1]));
							lbl_3.setForeground(new Color(255, 255, 255));
							lbl_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_3.setBackground(Color.WHITE);
							lbl_3.setBounds(16, 51, 267, 13);
							panel_1.add(lbl_3);
							
							JLabel lbl_4 = new JLabel("Entrata media");
							lbl_4.setForeground(new Color(255, 255, 255));
							lbl_4.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_4.setBackground(Color.WHITE);
							lbl_4.setBounds(16, 71, 267, 13);
							panel_1.add(lbl_4);
							
							JLabel lbl_5 = new JLabel(controller.formatMoney((Double)conto[2]));
							lbl_5.setForeground(new Color(255, 255, 255));
							lbl_5.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_5.setBackground(Color.WHITE);
							lbl_5.setBounds(16, 86, 267, 13);
							panel_1.add(lbl_5);
							
							JLabel lbl_6 = new JLabel("Entrata minima");
							lbl_6.setForeground(new Color(255, 255, 255));
							lbl_6.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_6.setBackground(Color.WHITE);
							lbl_6.setBounds(16, 106, 267, 13);
							panel_1.add(lbl_6);
							
							JLabel lbl_7 = new JLabel(controller.formatMoney((Double)conto[3]));
							lbl_7.setForeground(new Color(255, 255, 255));
							lbl_7.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_7.setBackground(Color.WHITE);
							lbl_7.setBounds(16, 121, 267, 13);
							panel_1.add(lbl_7);
							
							JLabel lbl_2_1 = new JLabel("Uscita massima", SwingConstants.RIGHT);
							lbl_2_1.setForeground(new Color(255, 255, 255));
							lbl_2_1.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_2_1.setBackground(Color.WHITE);
							lbl_2_1.setBounds(-22, 36, 267, 13);
							panel_1.add(lbl_2_1);
							
							JLabel lbl_3_1 = new JLabel(controller.formatMoney((Double)conto[4]), SwingConstants.RIGHT);
							lbl_3_1.setForeground(new Color(255, 255, 255));
							lbl_3_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_3_1.setBackground(Color.WHITE);
							lbl_3_1.setBounds(-22, 51, 267, 13);
							panel_1.add(lbl_3_1);
							
							JLabel lbl_4_1 = new JLabel("Uscita media", SwingConstants.RIGHT);
							lbl_4_1.setForeground(new Color(255, 255, 255));
							lbl_4_1.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_4_1.setBackground(Color.WHITE);
							lbl_4_1.setBounds(-22, 71, 267, 13);
							panel_1.add(lbl_4_1);
							
							JLabel lbl_5_1 = new JLabel(controller.formatMoney((Double)conto[5]), SwingConstants.RIGHT);
							lbl_5_1.setForeground(new Color(255, 255, 255));
							lbl_5_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_5_1.setBackground(Color.WHITE);
							lbl_5_1.setBounds(-22, 86, 267, 13);
							panel_1.add(lbl_5_1);
							
							JLabel lbl_6_1 = new JLabel("Uscita minima", SwingConstants.RIGHT);
							lbl_6_1.setForeground(new Color(255, 255, 255));
							lbl_6_1.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_6_1.setBackground(Color.WHITE);
							lbl_6_1.setBounds(-22, 106, 267, 13);
							panel_1.add(lbl_6_1);
							
							JLabel lbl_7_1 = new JLabel(controller.formatMoney((Double)conto[6]), SwingConstants.RIGHT);
							lbl_7_1.setForeground(new Color(255, 255, 255));
							lbl_7_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
							lbl_7_1.setBackground(Color.WHITE);
							lbl_7_1.setBounds(-22, 121, 267, 13);
							panel_1.add(lbl_7_1);
							
							JLabel lbl_8 = new JLabel("Saldo iniziale");
							lbl_8.setForeground(new Color(255, 255, 255));
							lbl_8.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_8.setBackground(Color.WHITE);
							lbl_8.setBounds(16, 141, 267, 13);
							panel_1.add(lbl_8);
							
							JLabel lbl_9 = new JLabel(controller.formatMoney((Double)conto[7]));
							lbl_9.setForeground(new Color(255, 255, 255));
							lbl_9.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_9.setBackground(Color.WHITE);
							lbl_9.setBounds(16, 156, 267, 13);
							panel_1.add(lbl_9);
							
							JLabel lbl_8_1 = new JLabel("Saldo finale", SwingConstants.RIGHT);
							lbl_8_1.setForeground(new Color(255, 255, 255));
							lbl_8_1.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_8_1.setBackground(Color.WHITE);
							lbl_8_1.setBounds(-22, 141, 267, 13);
							panel_1.add(lbl_8_1);
							
							JLabel lbl_9_1 = new JLabel(controller.formatMoney((Double)conto[8]), SwingConstants.RIGHT);
							lbl_9_1.setForeground(new Color(255, 255, 255));
							lbl_9_1.setFont(new Font("Helvetica", Font.BOLD, 13));
							lbl_9_1.setBackground(Color.WHITE);
							lbl_9_1.setBounds(-22, 156, 267, 13);
							panel_1.add(lbl_9_1);
							
							panel.add(panel_1);
						}
						panel.setPreferredSize(new Dimension(500, 200*report.size()));
						panel.revalidate();
						panel.repaint();
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
					}
				}else {
					panel.removeAll();
					panel.revalidate();
					panel.repaint();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nessuna selezione", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(22, 38, 258, 27);
		getContentPane().add(comboBox);
		
		JLabel lblReport = new JLabel("REPORT");
		lblReport.setForeground(new Color(172, 163, 175));
		lblReport.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblReport.setBounds(22, 165, 267, 13);
		getContentPane().add(lblReport);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(22, 180, 278, 329);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane);
		
		JLabel lblAnno = new JLabel("IBAN");
		lblAnno.setForeground(new Color(172, 163, 175));
		lblAnno.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblAnno.setBounds(22, 89, 267, 13);
		getContentPane().add(lblAnno);
		
		textField = new JTextField();
		textField.setFont(new Font("Helvetica", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(new CompoundBorder(new LineBorder(new Color(172, 163, 175)), new EmptyBorder(5, 5, 5, 5)));
		textField.setBackground(Color.WHITE);
		textField.setBounds(22, 105, 258, 27);
		textField.setText(String.valueOf(LocalDate.now().getYear()));
		getContentPane().add(textField);
		
		
	}
}
