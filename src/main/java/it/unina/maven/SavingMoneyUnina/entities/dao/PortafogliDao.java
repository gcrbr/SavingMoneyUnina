package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class PortafogliDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ArrayList<Transazione> getTransazioni(Portafogli portafogli) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM transazione_portafogli NATURAL JOIN transazione WHERE idportafogli=?");
		stm.setInt(1, portafogli.getIdportafogli());
		ArrayList<Transazione> transazioni = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setIdtransazione(rs.getInt(1));
			t.setValore(rs.getDouble(2));
			t.setData(rs.getDate(3));
			t.setDescrizione(rs.getString(4));
			t.setAltroIban(rs.getString(5));
			t.setTipo(rs.getString(6));
			transazioni.add(t);
		}
		return transazioni;
	}
	
	public ArrayList<String> getParoleChiave(Portafogli portafogli) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT parola FROM ParolaChiave WHERE idportafogli=?");
		stm.setInt(1, portafogli.getIdportafogli());
		ArrayList<String> parolechiave = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			parolechiave.add(rs.getString(1));
		}
		return parolechiave;
	}
	
	public String getParoleChiaveString(Portafogli portafogli) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT get_parolechiave_string(?)");
		stm.setInt(1, portafogli.getIdportafogli());
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return rs.getString(1);
		}
		return "";
	}
	
}
