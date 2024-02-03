package it.unina.maven.SavingMoneyUnina.entities;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.entities.dao.PortafogliDao;

public class Portafogli {
	private int idportafogli;
	private String nome;
	private Utente utente;
	private ArrayList<Transazione> transazioni = new ArrayList<>();
	private ArrayList<String> paroleChiave = new ArrayList<>();
	private ArrayList<Categoria> categorie = new ArrayList<>();
	
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
	
	public ArrayList<Transazione> getTransazioni() {
		try {
			setTransazioni(new PortafogliDao().getTransazioni(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transazioni;
	}
	
	public void setTransazioni(ArrayList<Transazione> t) {
		this.transazioni = t;
	}
	
	public ArrayList<String> getParoleChiave() {
		try {
			setParoleChiave(new PortafogliDao().getParoleChiave(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return paroleChiave;
	}

	public void setParoleChiave(ArrayList<String> paroleChiave) {
		this.paroleChiave = paroleChiave;
	}
	
	public String getParoleChiaveString() {
		try {
			return new PortafogliDao().getParoleChiaveString(this);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void addTransazione(Transazione t) throws SQLException{
		new PortafogliDao().addTransazione(this, t);
	}
	
	public void setParoleChiaveString(String parolechiave) throws SQLException{
		new PortafogliDao().addParoleChiaveMultiple(this, parolechiave);
	}
	
	public void setCategorie(ArrayList<Categoria> c) {
		this.categorie = c;
	}
	
	public ArrayList<Categoria> getCategorie() {
		try {
			setCategorie(new PortafogliDao().getCategorie(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}
	
	public void addCategoria(Categoria c) throws SQLException{
		new PortafogliDao().addCategoria(this, c);
	}
	
	public void deleteTransazione(Transazione t) throws SQLException {
		new PortafogliDao().deleteTransazione(this, t);
	}
	
	public void delete() throws SQLException {
		new PortafogliDao().deletePortafogli(this);
	}
	
}
