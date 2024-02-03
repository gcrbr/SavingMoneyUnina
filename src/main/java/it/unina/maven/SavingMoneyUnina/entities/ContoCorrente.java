package it.unina.maven.SavingMoneyUnina.entities;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.entities.dao.ContoCorrenteDao;

public class ContoCorrente {
	private double saldo;
	private String iban;
	private Utente utente;
	private Carta carta;
	ArrayList<Transazione> transazioni = new ArrayList<>();
	
	
	public double getSaldo() {
		try {
			setSaldo(new ContoCorrenteDao().getSaldo(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public Carta getCarta() {
		if(carta == null) {
			try {
				Carta c = new ContoCorrenteDao().getCartaAssociata(this);
				setCarta(c);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return carta;
	}
	
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	public ArrayList<Transazione> getTransazioni() {
		try {
			setTransazioni(new ContoCorrenteDao().getTransazioni(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transazioni;
	}
	
	public void setTransazioni(ArrayList<Transazione> transazioni) {
		this.transazioni = transazioni;
	}
	
	public void addTransazione(Transazione t) throws SQLException{
		new ContoCorrenteDao().aggiungiTransazione(this, t);
	}
	
	public void removeTransazione(Transazione t) throws SQLException {
		new ContoCorrenteDao().deleteTransazione(this, t);
	}
	
	public void delete() throws SQLException {
		new ContoCorrenteDao().deleteConto(this);
	}
}
