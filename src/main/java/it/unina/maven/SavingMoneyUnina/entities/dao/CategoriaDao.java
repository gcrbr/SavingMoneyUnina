package it.unina.maven.SavingMoneyUnina.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.ConnectionDatabase;
import it.unina.maven.SavingMoneyUnina.entities.Categoria;

public class CategoriaDao {
	private ConnectionDatabase database;
	private Connection connection;
	
	public ArrayList<Categoria> getCategorie() throws SQLException {
		database = ConnectionDatabase.getInstance();
		connection = database.getConnection();
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM categoria");
		ResultSet rs = stm.executeQuery();
		ArrayList<Categoria> categorie = new ArrayList<>();
		while(rs.next()) {
			Categoria c = new Categoria();
			c.setNome(rs.getString(1));
			categorie.add(c);
		}
		return categorie;
	}
	
}
