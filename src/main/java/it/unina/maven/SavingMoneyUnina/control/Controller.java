package it.unina.maven.SavingMoneyUnina.control;

import java.sql.SQLException;
import java.text.NumberFormat;

import it.unina.maven.SavingMoneyUnina.entities.Utente;
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
	
}
