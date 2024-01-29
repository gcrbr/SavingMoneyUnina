package it.unina.maven.SavingMoneyUnina.entities;

public class Portafogli {
	private int idportafogli;
	private String nome;
	private Utente utente;
	
	public int getIdportafogli() {
		return idportafogli;
	}
	
	public void setIdportafogli(int idportafogli) {
		this.idportafogli = idportafogli;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}
