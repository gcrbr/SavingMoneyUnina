package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Carta;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class ContoCorrenteDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public Carta getCartaAssociata(ContoCorrente cc) throws SQLException{
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT numero, scadenza, cvv, tipo, plafond, limitespesa FROM ContoCorrente JOIN Carta ON numerocarta=numero WHERE iban=?");
		stm.setString(1, cc.getIban());
		ResultSet rs = stm.executeQuery();
		Carta carta = null;
		while(rs.next()) {
			carta = new Carta();
			carta.setNumero(rs.getString(1));
			carta.setScadenza(rs.getDate(2));
			carta.setCvv(rs.getString(3));
			carta.setTipo(rs.getString(4));
			carta.setPlafond(rs.getDouble(5));
			carta.setLimitespesa(rs.getDouble(6));
			carta.setContoAssociato(cc);
		}
		return carta;
	}
	
	public ArrayList<Transazione> getTransazioni(ContoCorrente cc) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione WHERE iban = ? ORDER BY data DESC, idtransazione DESC");
		stm.setString(1, cc.getIban());
		ResultSet rs = stm.executeQuery();
		ArrayList<Transazione> transazioni = new ArrayList<>();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setValore(rs.getDouble(1));
			t.setData(rs.getDate(2));
			t.setDescrizione(rs.getString(3));
			t.setTipo(rs.getString(4));
			t.setAltroIban(rs.getString(5));
			t.setIdtransazione(rs.getInt(6));
			t.setConto(cc);
			transazioni.add(t);
		}
		return transazioni;
	}
	
	public void aggiungiTransazione(ContoCorrente cc, Transazione t) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("CALL inserisci_transazione(?, ?, ?, ?, ?, ?)");
		stm.setString(1, cc.getIban());
		stm.setString(2, t.getTipo());
		stm.setDouble(3, t.getValore());
		stm.setDate(4, t.getData());
		stm.setString(5, t.getDescrizione());
		stm.setString(6, t.getAltroIban());
		stm.execute();
	}
	
	public ContoCorrente getConto(ContoCorrente cc) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM ContoCorrente WHERE iban = ?");
		stm.setString(1, cc.getIban());
		ResultSet rs = stm.executeQuery();
		ContoCorrente result = null;
		while(rs.next()) {
			result = new ContoCorrente();
			result.setSaldo(rs.getDouble(1));
			result.setIban(rs.getString(2));
			result.setUtente(cc.getUtente());
			result.setCarta(cc.getCarta());
		}
		return result;
	}
	
	public double getSaldo(ContoCorrente cc) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT saldo FROM ContoCorrente WHERE iban = ?");
		stm.setString(1, cc.getIban());
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return rs.getDouble(1);
		}
		return 0;
	}
	
	public void deleteTransazione(ContoCorrente cc, Transazione t) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("DELETE FROM Transazione WHERE iban = ? AND idtransazione = ?");
		stm.setString(1, cc.getIban());
		stm.setInt(2, t.getIdtransazione());
		stm.execute();
	}
	
	public void deleteConto(ContoCorrente cc) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("DELETE FROM ContoCorrente WHERE iban = ?");
		stm.setString(1, cc.getIban());
		stm.execute();
	}
}
