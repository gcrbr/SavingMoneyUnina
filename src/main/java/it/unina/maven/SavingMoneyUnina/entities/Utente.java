package it.unina.maven.SavingMoneyUnina.entities;
import java.util.*;

public class Utente {
	private String email;
	private String password;
	private Persona persona;
	ArrayList<ContoCorrente> contigestiti= new ArrayList<>();

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
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public ArrayList<ContoCorrente> getContigestiti() {
		return contigestiti;
	}
	public void setContigestiti(ArrayList<ContoCorrente> contigestiti) {
		this.contigestiti = contigestiti;
	}
 
	public void removeContoCorrente(ContoCorrente cc) {
		contigestiti.remove(cc);
	}
	
	public void addContoCorrente(ContoCorrente cc) {
		contigestiti.add(cc);
	}

}
