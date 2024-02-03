package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;
import it.unina.maven.SavingMoneyUnina.entities.Portafogli;
import it.unina.maven.SavingMoneyUnina.entities.Transazione;

public class PortafogliDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ArrayList<Transazione> getTransazioni(Portafogli portafogli) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM transazione_portafogli NATURAL JOIN transazione WHERE idportafogli=? ORDER BY data DESC, idtransazione DESC");
		stm.setInt(1, portafogli.getIdportafogli());
		ArrayList<Transazione> transazioni = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Transazione t = new Transazione();
			t.setIdtransazione(rs.getInt(1));
			t.setValore(rs.getDouble(3));
			t.setData(rs.getDate(4));
			t.setDescrizione(rs.getString(5));
			t.setTipo(rs.getString(6));
			t.setAltroIban(rs.getString(7));
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
	
	public void addTransazione(Portafogli portafogli, Transazione transazione) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO transazione_portafogli VALUES(?, ?)");
		stm.setInt(1, transazione.getIdtransazione());
		stm.setInt(2, portafogli.getIdportafogli());
		stm.execute();
	}
	
	public void addParoleChiaveMultiple(Portafogli p, String parolechiave) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("CALL inserisci_parolechiave_multiple(?, ?)");
		stm.setInt(1, p.getIdportafogli());
		stm.setString(2, parolechiave);
		stm.execute();
	}
	
	public void addCategoria(Portafogli p, Categoria c) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO portafogli_categoria VALUES(?, ?)");
		stm.setInt(1, p.getIdportafogli());
		stm.setString(2, c.getNome());
		stm.execute();
	}
	
	public ArrayList<Categoria> getCategorie(Portafogli p) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT nomecategoria FROM portafogli_categoria WHERE idportafogli = ?");
		stm.setInt(1, p.getIdportafogli());
		ArrayList<Categoria> categorie = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			Categoria c = new Categoria();
			c.setNome(rs.getString(1));
			categorie.add(c);
		}
		return categorie;
	}
	
	public void deleteTransazione(Portafogli p, Transazione t) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("DELETE FROM transazione_portafogli WHERE idtransazione = ? AND idportafogli = ?");
		stm.setInt(1, t.getIdtransazione());
		stm.setInt(2, p.getIdportafogli());
		stm.execute();
	}
	
	public void deletePortafogli(Portafogli p) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("DELETE FROM portafogli WHERE idportafogli = ?");
		stm.setInt(1, p.getIdportafogli());
		stm.execute();
	}
}
