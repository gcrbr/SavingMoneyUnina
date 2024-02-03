package it.unina.maven.SavingMoneyUnina.boundaries;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.EmptyBorder;

import it.unina.maven.SavingMoneyUnina.control.Controller;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller = new Controller();

	private final Utente utente;
	
	public Home(final Utente utente) {
		this.utente = utente;
		setResizable(false);
		setTitle("Pagina Principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(693, 566);
		setLocationRelativeTo(null);
		this.load();
	}
	
	public void load() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 21, 41));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabel = new JLabel("Bentornato " + utente.getPersona().getNomeIntero());
		JLabel.setForeground(new Color(172, 163, 175));
		JLabel.setFont(new Font("Helvetica", Font.BOLD, 17));
		JLabel.setBounds(17, 17, 258, 20);
		contentPane.add(JLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 21, 40));
		panel.setBounds(17, 67, 210, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(controller.formatMoney(utente.getSaldoTotaleConti()));
		lblNewLabel.setBounds(23, 6, 193, 46);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JButton btnVediReportMensile = new JButton("Vedi report mensile");
		btnVediReportMensile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showReportMensile(utente);
			}
		});
		btnVediReportMensile.setBounds(6, 53, 193, 28);
		panel.add(btnVediReportMensile);
		btnVediReportMensile.setOpaque(true);
		btnVediReportMensile.setForeground(Color.WHITE);
		btnVediReportMensile.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnVediReportMensile.setBorderPainted(false);
		btnVediReportMensile.setBackground(new Color(53, 45, 72));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(35, 21, 40));
		panel_1.setBounds(461, 67, 210, 458);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		ArrayList<ContoCorrente> cs = utente.getContigestiti();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(35, 21, 40));
		panel_2.setLayout(null);
		panel_2.setPreferredSize(new Dimension(500, 53*cs.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel_2);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 198, 403);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		panel_1.add(scrollPane);
		
		JButton btnAggiungiConto = new JButton("+ Aggiungi conto");
		btnAggiungiConto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNuovoConto(Home.this, utente);
			}
		});
		btnAggiungiConto.setOpaque(true);
		btnAggiungiConto.setForeground(Color.WHITE);
		btnAggiungiConto.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnAggiungiConto.setBorderPainted(false);
		btnAggiungiConto.setBackground(new Color(53, 45, 72));
		btnAggiungiConto.setBounds(9, 421, 193, 28);
		panel_1.add(btnAggiungiConto);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(35, 21, 40));
		panel_4.setBounds(239, 67, 210, 458);
		contentPane.add(panel_4);

		ArrayList<Portafogli> ps = utente.getPortafogli();

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(35, 21, 40));
		panel_2_1.setPreferredSize(new Dimension(500, 53*ps.size()));
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_2_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 6, 198, 403);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		panel_4.add(scrollPane_1);
		
		JButton Accedi = new JButton("+ Crea portafogli");
		Accedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNuovoPortafogli(Home.this, utente);
			}
		});
		Accedi.setOpaque(true);
		Accedi.setForeground(Color.WHITE);
		Accedi.setFont(new Font("Helvetica", Font.PLAIN, 14));
		Accedi.setBorderPainted(false);
		Accedi.setBackground(new Color(53, 45, 72));
		Accedi.setBounds(9, 421, 193, 28);
		panel_4.add(Accedi);
		
		JLabel lblNewLabel_1 = new JLabel("SALDO TOTALE");
		lblNewLabel_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(17, 49, 146, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("I TUOI PORTAFOGLI");
		lblNewLabel_1_3.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(239, 49, 146, 16);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("I TUOI CONTI");
		lblNewLabel_1_1.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(461, 49, 146, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(35, 21, 40));
		panel_1_1.setBounds(17, 188, 210, 337);
		contentPane.add(panel_1_1);
		
		JLabel lblNome = new JLabel(utente.getPersona().getNome());
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNome.setBounds(16, 33, 181, 24);
		panel_1_1.add(lblNome);
		
		JLabel lblCognome = new JLabel(utente.getPersona().getCognome());
		lblCognome.setForeground(Color.WHITE);
		lblCognome.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCognome.setBounds(16, 72, 181, 24);
		panel_1_1.add(lblCognome);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nome");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(16, 22, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cognome");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(16, 62, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Indirizzo");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(16, 100, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblIndirizzo = new JLabel(utente.getPersona().getIndirizzo());
		lblIndirizzo.setForeground(Color.WHITE);
		lblIndirizzo.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblIndirizzo.setBounds(16, 114, 181, 24);
		panel_1_1.add(lblIndirizzo);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Città");
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(16, 138, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblCittà = new JLabel(utente.getPersona().getCittà());
		lblCittà.setForeground(Color.WHITE);
		lblCittà.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCittà.setBounds(16, 151, 181, 24);
		panel_1_1.add(lblCittà);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Paese");
		lblNewLabel_1_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_2.setBounds(16, 181, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblPaese = new JLabel(utente.getPersona().getPaese());
		lblPaese.setForeground(Color.WHITE);
		lblPaese.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblPaese.setBounds(16, 195, 181, 24);
		panel_1_1.add(lblPaese);
		
		JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Codice fiscale");
		lblNewLabel_1_1_1_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_2_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_2_1.setBounds(16, 231, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1_1_2_1);
		
		JLabel lblCf = new JLabel(utente.getPersona().getCodicefiscale());
		lblCf.setForeground(Color.WHITE);
		lblCf.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblCf.setBounds(16, 245, 181, 24);
		panel_1_1.add(lblCf);
		
		JLabel lblNewLabel_1_1_1_1_1_2_2 = new JLabel("Indirizzo email");
		lblNewLabel_1_1_1_1_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_2_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_2_2.setBounds(16, 279, 105, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1_1_2_2);
		
		JLabel lblEmail = new JLabel(utente.getEmail());
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblEmail.setBounds(16, 293, 181, 24);
		panel_1_1.add(lblEmail);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("LE TUE INFORMAZIONI");
		lblNewLabel_1_1_2.setForeground(new Color(172, 163, 175));
		lblNewLabel_1_1_2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(17, 171, 190, 16);
		contentPane.add(lblNewLabel_1_1_2);
		
		for(int i=0; i<ps.size(); ++i) {
			final Portafogli px = ps.get(i);
			
			MouseAdapter info2 = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.showInformazioniPortafogli(utente, px);
				}
			};
			
			JPanel panel_3_1 = new JPanel();
			panel_3_1.setLayout(null);
			panel_3_1.setBackground(new Color(53, 45, 72));
			panel_3_1.setBounds(2, 6+53*i, 192, 43);
			panel_2_1.add(panel_3_1);
			panel_3_1.addMouseListener(info2);
			
			JLabel lblNewLabel_1_2_3 = new JLabel(px.getNome());
			lblNewLabel_1_2_3.setForeground(Color.WHITE);
			lblNewLabel_1_2_3.setFont(new Font("Helvetica", Font.BOLD, 14));
			lblNewLabel_1_2_3.setBackground(Color.WHITE);
			lblNewLabel_1_2_3.setBounds(6, 6, 131, 13);
			panel_3_1.add(lblNewLabel_1_2_3);
			lblNewLabel_1_2_3.addMouseListener(info2);
			
			JButton btnRemovePortafogli = new JButton("-");
			btnRemovePortafogli.setOpaque(true);
			btnRemovePortafogli.setForeground(Color.WHITE);
			btnRemovePortafogli.setFont(new Font("Helvetica", Font.BOLD, 5));
			btnRemovePortafogli.setBorderPainted(false);
			btnRemovePortafogli.setBackground(new Color(35,21,40));
			btnRemovePortafogli.setBounds(140, 7, 40, 30);
			
			btnRemovePortafogli.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int reply = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler rimuovere questo portafogli?", "Avviso", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						try {
							px.delete();
							getContentPane().removeAll();
							load();
							revalidate();
							repaint();
						}catch(SQLException e1) {
							JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
						}catch(Exception e2) {
							JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
						}
					}
				}
			});
			
			panel_3_1.add(btnRemovePortafogli);
		}
		
		for(int i=0; i<cs.size(); ++i) {
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(53, 45, 72));
			panel_3.setBounds(2, 6+53*i, 192, 43);
			panel_2.add(panel_3);
			panel_3.setLayout(null);
			
			final ContoCorrente cx = cs.get(i);
			
			MouseAdapter info = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.showInformazioniConto(Home.this, cx);
				}
			};
			
			panel_3.addMouseListener(info);
			
			JLabel lblNewLabel_1_2 = new JLabel(cx.getIban());
			lblNewLabel_1_2.setBounds(6, 6, 131, 13);
			lblNewLabel_1_2.setBackground(new Color(255, 255, 255));
			lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2.setFont(new Font("Helvetica", Font.BOLD, 14));
			panel_3.add(lblNewLabel_1_2);
			lblNewLabel_1_2.addMouseListener(info);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("Carta " + cx.getCarta().getNumero());
			lblNewLabel_1_2_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_2_1.setFont(new Font("Helvetica", Font.PLAIN, 13));
			lblNewLabel_1_2_1.setBackground(Color.WHITE);
			lblNewLabel_1_2_1.setBounds(6, 24, 131, 13);
			panel_3.add(lblNewLabel_1_2_1);
			lblNewLabel_1_2_1.addMouseListener(info);
			
			JButton btnRemoveConto = new JButton("-");
			btnRemoveConto.setOpaque(true);
			btnRemoveConto.setForeground(Color.WHITE);
			btnRemoveConto.setFont(new Font("Helvetica", Font.BOLD, 5));
			btnRemoveConto.setBorderPainted(false);
			btnRemoveConto.setBackground(new Color(35,21,40));
			btnRemoveConto.setBounds(140, 7, 40, 30);
			
			btnRemoveConto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int reply = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler rimuovere questo conto corrente?", "Avviso", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						try {
							cx.delete();
							getContentPane().removeAll();
							load();
							revalidate();
							repaint();
						}catch(SQLException e1) {
							JOptionPane.showMessageDialog(null, "Si è verificato un errore interno: " + e1.getLocalizedMessage());
						}catch(Exception e2) {
							JOptionPane.showMessageDialog(null, "Si è verificato un errore: " + e2.getLocalizedMessage());
						}
					}
				}
			});
			
			panel_3.add(btnRemoveConto);
		}
	}
}
