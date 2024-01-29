package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Persona;
import it.unina.maven.SavingMoneyUnina.entities.Utente;

public class PersonaDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public Utente getUtente(Persona persona) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM utente WHERE codicefiscale = ?");
		stm.setString(1, persona.getCodicefiscale());
		ResultSet rs = stm.executeQuery();
		Utente utente = null;
		while(rs.next()) {
			utente = new Utente();
			utente.setEmail(rs.getString(1));
			utente.setPassword(rs.getString(2));
			utente.setPersona(persona);
		}
		return utente;
	}
}
