package it.unina.maven.SavingMoneyUnina.entities;

import java.util.ArrayList;

public class ContoCorrente {
	private double saldo;
	private String iban;
	private Utente utente;
	private Carta carta;
	ArrayList<Transazione> transazioneconti = new ArrayList<>();
	
	
	public double getSaldo() {
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
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public ArrayList<Transazione> getTransazioneconti() {
		return transazioneconti;
	}
	public void setTransazioneconti(ArrayList<Transazione> transazioneconti) {
		this.transazioneconti = transazioneconti;
	}
	
}
