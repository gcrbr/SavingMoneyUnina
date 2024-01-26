package it.unina.maven.SavingMoneyUnina.entities;

import java.util.Date;

public class Carta {
	private String numero;
	private Date scadenza;
	private String cvv;
	private String tipo;
	private double plafond;
	private double limitespesa;
	private ContoCorrente contoAssociato;
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Date getScadenza() {
		return scadenza;
	}
	
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public double getPlafond() {
		return plafond;
	}
	
	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}
	
	public double getLimitespesa() {
		return limitespesa;
	}
	
	public void setLimitespesa(double limitespesa) {
		this.limitespesa = limitespesa;
	}
	
	public ContoCorrente getContoAssociato() {
		return contoAssociato;
	}

	public void setContoAssociato(ContoCorrente contoAssociato) {
		this.contoAssociato = contoAssociato;
	}

	public boolean isCredito() {
		return getTipo().equals("credito");
	}
	
	public boolean isDebito() {
		return getTipo().equals("debito");
	}
	
}
