package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class TransazioneDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ArrayList<Transazione> cercaTransazione(Date from, Date to) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione WHERE data BETWEEN ? AND ?");
		stm.setDate(1, from);
		stm.setDate(2, to);
		ArrayList<Transazione> risultati = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setValore(rs.getDouble(1));
			t.setData(rs.getDate(2));
			t.setDescrizione(rs.getString(3));
			t.setTipo(rs.getString(4));
			t.setAltroIban(rs.getString(5));
			t.setIdtransazione(rs.getInt(6));
			risultati.add(t);
		}
		return risultati;
	}
	
	public ArrayList<Transazione> cercaTransazione(ContoCorrente cc, Date from, Date to) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione WHERE data BETWEEN ? AND ? AND iban = ?");
		stm.setDate(1, from);
		stm.setDate(2, to);
		stm.setString(3, cc.getIban());
		ArrayList<Transazione> risultati = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setValore(rs.getDouble(1));
			t.setData(rs.getDate(2));
			t.setDescrizione(rs.getString(3));
			t.setTipo(rs.getString(4));
			t.setAltroIban(rs.getString(5));
			t.setIdtransazione(rs.getInt(6));
			t.setConto(cc);
			risultati.add(t);
		}
		return risultati;
	}
	
	public ArrayList<Transazione> cercaTransazione(Portafogli p, Date from, Date to) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione NATURAL JOIN transazione_portafogli WHERE data BETWEEN ? AND ? AND idportafogli = ?");
		stm.setDate(1, from);
		stm.setDate(2, to);
		stm.setInt(4, p.getIdportafogli());
		ArrayList<Transazione> risultati = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setIdtransazione(rs.getInt(1));
			t.setValore(rs.getDouble(2));
			t.setData(rs.getDate(3));
			t.setDescrizione(rs.getString(4));
			t.setTipo(rs.getString(5));
			t.setAltroIban(rs.getString(6));
			risultati.add(t);
		}
		return risultati;
	}
	
	public ArrayList<Transazione> cercaTransazione(ContoCorrente cc, Portafogli p, Date from, Date to) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione NATURAL JOIN transazione_portafogli WHERE data BETWEEN ? AND ? AND iban = ? AND idportafogli = ?");
		stm.setDate(1, from);
		stm.setDate(2, to);
		stm.setString(3, cc.getIban());
		stm.setInt(4, p.getIdportafogli());
		ArrayList<Transazione> risultati = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setIdtransazione(rs.getInt(1));
			t.setValore(rs.getDouble(2));
			t.setData(rs.getDate(3));
			t.setDescrizione(rs.getString(4));
			t.setTipo(rs.getString(5));
			t.setAltroIban(rs.getString(6));
			t.setConto(cc);
			risultati.add(t);
		}
		return risultati;
	}

}
