package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Carta;
import it.unina.maven.SavingMoneyUnina.entities.ContoCorrente;

public class CartaDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ContoCorrente getContoCorrente(Carta carta) throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM ContoCorrente WHERE numerocarta = ?");
		stm.setString(1, carta.getNumero());
		ResultSet rs = stm.executeQuery();
		ContoCorrente conto = new ContoCorrente();
		while(rs.next()) {
			conto.setSaldo(rs.getDouble(1));
			conto.setIban(rs.getString(2));
			conto.setUtente(new UtenteDao().getUtenteByEmail(rs.getString(3)));
			conto.setCarta(carta);
		}
		return conto;
	}
}
