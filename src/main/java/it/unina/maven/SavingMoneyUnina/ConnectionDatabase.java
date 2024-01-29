package it.unina.maven.SavingMoneyUnina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
	private static ConnectionDatabase istanza;
	private Connection con;
	
	public ConnectionDatabase() {
		inizializzaConnessione();
	}

	private void inizializzaConnessione() {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			this.con = DriverManager.getConnection(url,"postgres","diocane");
			this.con.prepareStatement("SET search_path = \"SavingMoneyUnina\"").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	public static ConnectionDatabase getInstance() {
		if(istanza == null) {
			istanza = new ConnectionDatabase();
		}
		return istanza;
	}
	
}
