package it.unina.maven.SavingMoneyUnina.entities;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unina.maven.SavingMoneyUnina.entities.dao.UtenteDao;

public class Utente {
	private String email;
	private String password;
	private Persona persona;
	ArrayList<ContoCorrente> contigestiti= new ArrayList<>();
	ArrayList<Portafogli> portafogli = new ArrayList<>();

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Persona getPersona() {
		if(persona == null) {
			try {
				Persona p = new UtenteDao().getPersona(this);
				this.setPersona(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public ArrayList<ContoCorrente> getContigestiti() {
		try {
			this.setContigestiti(new UtenteDao().getContiGestiti(this));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contigestiti;
	}
	
	public ArrayList<Portafogli> getPortafogli() {
		try {
			ArrayList<Portafogli> p = new UtenteDao().getPortafogli(this);
			setPortafogli(p);
 		}catch(SQLException e) {
			e.printStackTrace();
		}
		return portafogli;
	}
	
	public void setContigestiti(ArrayList<ContoCorrente> contigestiti) {
		this.contigestiti = contigestiti;
	}
	
	public void setPortafogli(ArrayList<Portafogli> portafogli) {
		this.portafogli = portafogli;
	}
	
	public double getSaldoTotaleConti() {
		try {
			return new UtenteDao().getSaldoTotaleConti(this);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addContoCorrente(ContoCorrente cc, Carta c) throws SQLException {
		new UtenteDao().inserisciNuovoConto(this, cc, c);
	}
	
	public int addPortafogli(Portafogli p) throws SQLException {
		return new UtenteDao().inserisciNuovoPortafogli(this, p);
	}
	
	public ArrayList<Object[]> getReportMensile(int mese, int anno) throws SQLException {
		return new UtenteDao().getReportMensile(this, mese, anno);
	}
}
