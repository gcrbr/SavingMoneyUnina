package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Persona;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class UtenteDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ArrayList<ContoCorrente> getContiGestiti(Utente utente) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM ContoCorrente WHERE email = ?");
		stm.setString(1, utente.getEmail());
		ResultSet rs = stm.executeQuery();
		ArrayList<ContoCorrente> conti = new ArrayList<>();
		while(rs.next()) {
			ContoCorrente c = new ContoCorrente();
			c.setSaldo(rs.getDouble(1));
			c.setIban(rs.getString(2));
			conti.add(c);
		}
		return conti;
	}
	
	public Utente getUtenteByEmail(String email) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM utente WHERE email = ?");
		stm.setString(1, email);
		ResultSet rs = stm.executeQuery();
		Utente utente = null;
		while(rs.next()) {
			utente = new Utente();
			utente.setEmail(rs.getString(1));
			utente.setPassword(rs.getString(2));
		}
		return utente;
	}
	
	public Persona getPersona(Utente utente) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT nome,cognome,indirizzo,paese,codicefiscale,città FROM persona NATURAL JOIN utente");
		ResultSet rs = stm.executeQuery();
		Persona persona = new Persona();
		while(rs.next()) {
			persona.setNome(rs.getString(1));
			persona.setCognome(rs.getString(2));
			persona.setIndirizzo(rs.getString(3));
			persona.setPaese(rs.getString(4));
			persona.setCodicefiscale(rs.getString(5));
			persona.setCittà(rs.getString(6));
		}
		return persona;
	}
	
	public ArrayList<Portafogli> getPortafogli(Utente utente) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM portafogli WHERE email = ?");
		stm.setString(1, utente.getEmail());
		ArrayList<Portafogli> portafogli = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Portafogli p = new Portafogli();
			p.setNome(rs.getString(1));
			p.setIdportafogli(rs.getInt(2));
			p.setUtente(utente);
			portafogli.add(p);
		}
		return portafogli;
	}
}
