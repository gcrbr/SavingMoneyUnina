package it.unina.maven.SavingMoneyUnina.control;

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.dao.CategoriaDao;

public class DataController {

	public ArrayList<Categoria> getCategorie() {
		try {
			return new CategoriaDao().getCategorie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getDate(int anno, int mese, int giorno) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, anno);
		cal.set(Calendar.MONTH, mese - 1);
		cal.set(Calendar.DAY_OF_MONTH, giorno);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return new Date(cal.getTimeInMillis());
	}
	
	public String dateToString(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
	
	public String formatMoney(double money) {
		return NumberFormat.getCurrencyInstance().format(money);
	}
	
}
