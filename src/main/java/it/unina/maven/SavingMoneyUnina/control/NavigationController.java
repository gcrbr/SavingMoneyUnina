package it.unina.maven.SavingMoneyUnina.control;

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unina.maven.SavingMoneyUnina.boundaries.Home;
import it.unina.maven.SavingMoneyUnina.boundaries.InformazioniConto;
import it.unina.maven.SavingMoneyUnina.boundaries.InformazioniPortafogli;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovaTransazione;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovoConto;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovoPortafogli;
import it.unina.maven.SavingMoneyUnina.boundaries.ReportMensile;
import it.unina.maven.SavingMoneyUnina.boundaries.RicercaAvanzata;
import it.unina.maven.SavingMoneyUnina.boundaries.SceltaManualeTransazione;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;
import it.unina.maven.SavingMoneyUnina.entities.dao.CategoriaDao;
import it.unina.maven.SavingMoneyUnina.entities.dao.UtenteDao;

public class NavigationController {
	
	public void showHomePage(Utente u) {
		Home h = new Home(u);
		h.setVisible(true);
	}
	
	public void showNuovoConto(JFrame caller, Utente u) {
		NuovoConto nc = new NuovoConto(caller, u);
		nc.setVisible(true);
	}
	
	public void showInformazioniConto(JFrame caller, ContoCorrente cc) {
		InformazioniConto ic = new InformazioniConto(caller, cc);
		ic.setVisible(true);
	}
	
	public void showNuovaTransazione(JFrame caller, ContoCorrente cc) {
		NuovaTransazione nt = new NuovaTransazione(caller, cc);
		nt.setVisible(true);
	}
	
	public void showInformazioniPortafogli(Utente u, Portafogli p) {
		InformazioniPortafogli ip = new InformazioniPortafogli(u, p);
		ip.setVisible(true);
	}
	
	public void showSceltaManuale(JFrame caller, Utente u, Portafogli p) {
		SceltaManualeTransazione smt = new SceltaManualeTransazione(caller, u, p);
		smt.setVisible(true);
	}
	
	public void showNuovoPortafogli(JFrame caller, Utente u) {
		NuovoPortafogli np = new NuovoPortafogli(caller, u);
		np.setVisible(true);
	}
	
	public void showRicercaAvanzata(Utente u) {
		RicercaAvanzata ra = new RicercaAvanzata(u);
		ra.setVisible(true);
	}
	
	public void showReportMensile(Utente u) {
		ReportMensile rm = new ReportMensile(u);
		rm.setVisible(true);
	}
	
	public void showAlert(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
}
