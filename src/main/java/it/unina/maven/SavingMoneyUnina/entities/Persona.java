package it.unina.maven.SavingMoneyUnina.entities;

public class Persona {
	private String nome;
	private String cognome;
	private String paese;
	private String indirizzo;
	private String città;
	private String codicefiscale;
	private Utente utente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getPaese() {
		return paese;
	}
	
	public void setPaese(String paese) {
		this.paese = paese;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public String getNomeIntero() {
		return this.getNome() + " " + this.getCognome();
	}

}

