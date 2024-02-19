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
		String url = "jdbc:postgresql://ep-hidden-glitter-a20zui75-pooler.eu-central-1.aws.neon.tech/progetto?user=admin&password=quHyp8ENF6lB&sslmode=require";
		try {
			this.con = DriverManager.getConnection(url,"admin","quHyp8ENF6lB");
			this.con.prepareStatement("SET search_path = \"SavingMoneyUnina\"").execute(); //Imposta schema di default per evitare di doverlo inserire in ogni query
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