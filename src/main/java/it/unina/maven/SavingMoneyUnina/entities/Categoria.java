package it.unina.maven.SavingMoneyUnina.entities;

public class Categoria {

	private String nome;
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}