package it.unina.maven.SavingMoneyUnina.control;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import it.unina.maven.SavingMoneyUnina.boundaries.Home;
import it.unina.maven.SavingMoneyUnina.boundaries.InformazioniConto;
import it.unina.maven.SavingMoneyUnina.boundaries.InformazioniPortafogli;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovaTransazione;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovoConto;
import it.unina.maven.SavingMoneyUnina.boundaries.NuovoPortafogli;
import it.unina.maven.SavingMoneyUnina.boundaries.SceltaManualeTransazione;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;
import it.unina.maven.SavingMoneyUnina.entities.dao.CategoriaDao;
import it.unina.maven.SavingMoneyUnina.entities.dao.UtenteDao;

public class Controller {
	
	public Utente checkLoginCredentials(String email, String password) throws SQLException {
		Utente loggedUser = new UtenteDao().getUtenteByEmail(email);
		if(loggedUser == null) return null;
		if(loggedUser.getPassword().equals(password)) {
			return loggedUser;
		}else {
			return null;
		}
	}
	
	public String formatMoney(double money) {
		return NumberFormat.getCurrencyInstance().format(money);
	}
	
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
	
	public void reloadFrame(JFrame frame) {
		frame.repaint();
	}
	
	public void showInformazioniPortafogli(Utente u, Portafogli p) {
		InformazioniPortafogli ip = new InformazioniPortafogli(u, p);
		ip.setVisible(true);
	}
	
	public void showSceltaManuale(Utente u, Portafogli p) {
		SceltaManualeTransazione smt = new SceltaManualeTransazione(u, p);
		smt.setVisible(true);
	}
	
	public void showNuovoPortafogli(Utente u) {
		NuovoPortafogli np = new NuovoPortafogli(u);
		np.setVisible(true);
	}
	
	public ArrayList<Categoria> getCategorie() {
		try {
			return new CategoriaDao().getCategorie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
