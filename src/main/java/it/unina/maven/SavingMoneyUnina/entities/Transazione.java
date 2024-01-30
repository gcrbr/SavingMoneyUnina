package it.unina.maven.SavingMoneyUnina.entities;

import java.sql.Date;

public class Transazione {
	private int idtransazione;
	private double valore;
	private Date data;
	private String descrizione;
	private String altroIban;
	private String tipo;
	private ContoCorrente conto;
	
	public int getIdtransazione() {
		return idtransazione;
	}
	
	public void setIdtransazione(int idtransazione) {
		this.idtransazione = idtransazione;
	}
	
	public double getValore() {
		return valore;
	}
	
	public void setValore(double valore) {
		this.valore = valore;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getAltroIban() {
		return altroIban;
	}
	
	public void setAltroIban(String altroIban) {
		this.altroIban = altroIban;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public ContoCorrente getConto() {
		return conto;
	}
	
	public void setConto(ContoCorrente conto) {
		this.conto = conto;
	}
	
}
