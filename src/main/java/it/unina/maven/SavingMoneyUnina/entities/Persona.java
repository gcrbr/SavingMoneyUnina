package it.unina.maven.SavingMoneyUnina.entities;

public class Persona {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String città;
	private String codicefiscale;
	private Utente utente;

	public Persona(String nome, String cognome, String indirizzo, String città, String codicefiscale) {
		this.nome=nome;
		this.cognome=cognome;
		this.indirizzo=indirizzo;
		this.città=città;
		this.codicefiscale=codicefiscale;
	}

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

}

