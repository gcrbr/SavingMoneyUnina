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
		PreparedStatement stm = connection.prepareStatement("SELECT numero, scadenza, cvv, tipo, plafond, limitespesa FROM ContoCorrente NATURAL JOIN Carta WHERE iban=?");
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
	
	public List<Transazione> getTransazioni(ContoCorrente cc) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM Transazione WHERE iban = ?");
		stm.setString(1, cc.getIban());
		ResultSet rs = stm.executeQuery();
		List<Transazione> transazioni = new ArrayList<>();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setIdtransazione(rs.getInt(1));
			t.setValore(rs.getDouble(2));
			t.setData(rs.getDate(3));
			t.setDescrizione(rs.getString(4));
			t.setAltroIban(rs.getString(5));
			t.setTipo(rs.getString(6));
			t.setConto(cc);
			transazioni.add(t);
		}
		return transazioni;
	}

}
