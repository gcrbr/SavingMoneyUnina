package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Carta;
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
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM ContoCorrente WHERE email = ? ORDER BY iban");
		stm.setString(1, utente.getEmail());
		ResultSet rs = stm.executeQuery();
		ArrayList<ContoCorrente> conti = new ArrayList<>();
		while(rs.next()) {
			ContoCorrente c = new ContoCorrente();
			c.setSaldo(rs.getDouble(1));
			c.setIban(rs.getString(2));
			c.setUtente(utente);
			
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
		PreparedStatement stm = connection.prepareStatement("SELECT nome,cognome,indirizzo,paese,codicefiscale,città FROM persona NATURAL JOIN utente WHERE email = ?");
		stm.setString(1, utente.getEmail());
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
	
	public double getSaldoTotaleConti(Utente utente) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT calcola_saldo_totale(?)");
		stm.setString(1, utente.getEmail());
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return rs.getDouble(1);
		}
		return 0;
	}
	
	public void inserisciNuovoConto(Utente u, ContoCorrente cc, Carta c) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("CALL crea_nuovo_conto(?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setString(1, cc.getIban());
		stm.setDouble(2, cc.getSaldo());
		stm.setString(3, c.getNumero());
		stm.setDate(4, c.getScadenza());
		stm.setString(5, c.getCvv());
		stm.setString(6, c.getTipo());
		if(c.getTipo().equals("credito")) {
			stm.setDouble(7, c.getPlafond());
		}else {
			stm.setDouble(7, c.getLimitespesa());
		}
		stm.setString(8, u.getEmail());
		stm.execute();
	}
	
	public int inserisciNuovoPortafogli(Utente u, Portafogli p) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO PORTAFOGLI(nome, email) VALUES (?, ?) RETURNING idportafogli;");
		stm.setString(1, p.getNome());
		stm.setString(2, u.getEmail());
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	
	public ArrayList<Object[]> getReportMensile(Utente u, int mese, int anno) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT report_mensile(?, ?, ?)");
		stm.setInt(1, mese);
		stm.setInt(2, anno);
		stm.setString(3, u.getEmail());
		ArrayList<Object[]> ret = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			String risultato = rs.getString(1);
			String[] righe = risultato.split(",");
			for(String riga : righe) {
				String[] colonne = riga.split(";");
				ret.add(new Object[] {
					colonne[0], //iban
					Double.parseDouble(colonne[1]), //entrataMax
					Double.parseDouble(colonne[2]), //entrataAvg
					Double.parseDouble(colonne[3]), //entrataMin
					Double.parseDouble(colonne[4]), //uscitaMax
					Double.parseDouble(colonne[5]), //uscitaAvg
					Double.parseDouble(colonne[6]), //uscitaMin
					Double.parseDouble(colonne[7]), //saldoIniziale
					Double.parseDouble(colonne[8]), //saldoFinale
				});
			}
		}
		return ret;
	}
	
}
