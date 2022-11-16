package entities;
import entities.Receita;
public class Conta {
	private String nome, resumo, senha, email;
	private int id;
	
	public Conta(String nome, String resumo, String senha, String email) {
		this.nome = nome;
		this.resumo = resumo;
		this.senha = senha;
		this.email = email;
	}
	
	public Conta () {}

	public void setSenha (String senha) {
	this.senha = senha;
	}
	public void setNome (String nome_usuario) {
	this.nome = nome_usuario;
	}
	public void setEmail (String email) {
	this.email = email;
	}
	public void setId (int id) {
	this.id = id;
	}
	public void setResumo (String resumo) {
		this.resumo = resumo;
		}
	
	public int getId () {return id;}
	public String getResumo () {return resumo;}
	public String getSenha () {return senha;}
	public String getEmail () {return email;}
	public String getNome () {return nome;}
	
	@Override
	public String toString() {
		return "|Account (id "+this.id+"):\n|Username: "+this.nome+"\n|Email: "+this.email+"\n|Account summary: "+this.resumo+"\n";
	}
	}
