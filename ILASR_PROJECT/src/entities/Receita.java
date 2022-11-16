package entities;
import entities.Conta;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Receita {
private String Nome, Descricao;
private Date data;
private String nome_conta;
private int Id_receita, curtidas;
private Conta conta;

public Receita(String nome, String descricao, String nome_conta) {
	this.Nome = nome;
	this.Descricao = descricao;
	this.nome_conta = nome_conta;
}

public Receita () {}

public void setNome (String nome) {
	this.Nome = nome;
}

public void setNomeConta (String nome_conta) {
	this.nome_conta = nome_conta;
}

public void setId (int id) {
	this.Id_receita = id;
}

public void setCurtidas (int curtidas) {
	this.curtidas = curtidas;
}

public void setDescricao (String descricao) {
	this.Descricao = descricao;
}

public void setData (Date date) {
 this.data = date;
}

public String getNomeConta() {return nome_conta;}
public String getNomee() {return this.conta.getNome();}
public int getId() {return this.Id_receita;}
public int getCurtidas() {return this.curtidas;}
public Date getData () {return this.data;}
public String getNome() {return this.Nome;}
public String getDescricao () {return this.Descricao;}

@Override
public String toString() {
return "|Recipe: "+this.Nome+"\n|Description: "+this.Descricao+"\n|Recipe author: "+this.nome_conta;
}



}
