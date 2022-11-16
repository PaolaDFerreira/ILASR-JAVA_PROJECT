package Main;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAOs.ContaDAO;
import DAOs.ContaDAOJDBC;
import DAOs.DAOfactory;
import DAOs.ReceitaDAO;
import DAOs.ReceitaDAOJDBC;
import db.DB;
import entities.Conta;
import entities.Receita;
public class Main {
	static Conta contalogada;
	private static List<Conta> listaConta = new ArrayList<>();
	private static int menu = 0;
	private static int menuconta = 0;
	private static int menureceita = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner t = new Scanner (System.in);
		
		while (menu != 4) {
            telaDeInicio();
            menu = t.nextInt();

            switch (menu) {
                case 1:
                	contaLogada();
                	break;
                case 2:
                	 cadastrarConta();
                     break;
                case 4:
                    System.out.println("|Thank you for using ILASR!");
                    DB.closeConnection();
                    break;
                case 5: 
                	updateConta();
                    break;
                default:
                    System.out.println("|Select one of the existing options: ");
            }
        }
	}
	
	// account menu
	private static void menuConta() {
				System.out.println("|---------------------------------------|");
				System.out.println("|               Account menu:           |");
				System.out.println("|     1 - Delete current account        |");
				System.out.println("|     2 - Go to Recipes menu            |");
				System.out.println("|     3 - Update account                |");
				System.out.println("|     4 - Show current account details  |");
				System.out.println("|     5 - Accounts list                 |");
				System.out.println("|     6 - Search account by id          |");
				System.out.println("|     7 - Logout                        |");
				System.out.println("|---------------------------------------|");
	}

	// tela de in�cio
		private static void telaDeInicio() {
				System.out.println("|-------------------------------|");
				System.out.println("|        Welcome to ILASR!      |");
				System.out.println("|     1 - Login                 |");
				System.out.println("|     2 - Register an account   |");
				System.out.println("|     4 - Logout                |");
				System.out.println("|-------------------------------|");
			}
	// menu receita
			private static void menuReceita() {
				System.out.println("|---------------------------------------|");
				System.out.println("|             Recipes menu:             |");
				System.out.println("|     3 - Publish a recipe              |");
				System.out.println("|     4 - Recipes list                  |");
				System.out.println("|     5 - Delete recipe                 |");
				System.out.println("|     6 - Update recipe                 |");
				System.out.println("|     7 - Search a recipe               |");
				System.out.println("|     2 - Logout                        |");
				System.out.println("|---------------------------------------|");		
			}
	
	private static void mostrarConta() {
		System.out.println(contalogada);
	}
	
	private static void buscarConta() {
		Scanner t = new Scanner (System.in);
		ContaDAO contaDao = DAOfactory.createContaDao();
		System.out.println("|Enter the id of the account you are looking for: ");
		int id = t.nextInt();
		Conta conta = contaDao.findById(id);
		System.out.println("|Account data:\n|Account name: "+conta.getNome()+"\n|Account summary: "+conta.getResumo());
	}
	
	private static void listaReceitas() {
		ReceitaDAO ReceitaDAO = DAOfactory.createReceitaDao();
		System.out.println("|Recipes list:");
		List<Receita> list = ReceitaDAO.findAll();
		for (Receita obj : list) {
			System.out.println(obj+"\n");}
	}
	
	private static void listaContas() {
		DB.getConnection();
		ContaDAO ContaDAO = DAOfactory.createContaDao();
		System.out.println("|Accounts list:");
		List<Conta> list = ContaDAO.findAll();
		for (Conta obj : list) {
			System.out.println(obj+"\n");}
	}
	
	private static void deletarReceita() {
		Scanner t = new Scanner (System.in);
		System.out.println("|Enter the id of the recipe you want to delete: ");
		int deletar = t.nextInt();
		ReceitaDAOJDBC ReceitaDAO = new ReceitaDAOJDBC ();
		ReceitaDAO.deletebyId(deletar, contalogada);
 }
	
	
	private static void cadastrarConta() {
		Scanner t = new Scanner (System.in);
        System.out.println("|-------------------------------------|");
        System.out.println("|          Register account  :        |");
        System.out.println("|-------------------------------------|");

    	ContaDAO ContaDAO = DAOfactory.createContaDao();
    	System.out.println("|To register your account, fill in the information regarding the account:|");
    	String resumo, nome, email,senha;
    	System.out.println("|Name: ");
    	nome = t.nextLine();
    	System.out.println("|Account summary: ");
    	resumo = t.nextLine();
    	System.out.println("|Email: ");
    	email = t.nextLine();
    	System.out.println("|Password: ");
    	senha = t.nextLine();
    	Conta newConta = new Conta(nome,resumo,senha,email);
    	ContaDAO.insert(newConta);
    	contalogada = newConta;
    	gerenciarMenuConta();
    }
	
	private static void deletarConta() {
		Scanner t = new Scanner (System.in);
		System.out.println("|Delete current account?\n|1 - yes\n|2 - no");
		int sim = t.nextInt();
		if (sim == 1) {
		ContaDAOJDBC ContaDAO = new ContaDAOJDBC ();
		ContaDAO.deletebyId(contalogada.getId());
		contalogada = null;
		System.out.println("|Your account has been successfully deleted, you will be redirected to the homepage."); }
		else {
		System.out.println("|You chose the 'no' option. You will be redirected to the homepage.");
		}
		menuconta = 7;
	}
	
	private static void updateConta() {
		Scanner t = new Scanner (System.in);
		ContaDAO ContaDAO = DAOfactory.createContaDao();
		System.out.println("|-------------------------------------|");
        System.out.println("|           Update account:           |");
        System.out.println("|-------------------------------------|");
		Conta Conta = new Conta ();
		Conta = ContaDAO.findById(contalogada.getId());
		System.out.println(Conta+"\n");
		System.out.println("|Enter the new account values: ");
		System.out.println("|Name: ");
		String nome = t.nextLine();
		Conta.setNome(nome);
		System.out.println("|Email: ");
		String email = t.nextLine();
		Conta.setEmail(email);
		System.out.println("|Summary: ");
		String resumo = t.nextLine();
		Conta.setResumo(resumo);
		System.out.println("|Password: ");
		String senha = t.nextLine();
		Conta.setSenha(senha);	
		ContaDAO.update(Conta);
	}
	
	private static void publicarReceita(){
		Scanner t = new Scanner (System.in);
        System.out.println("|-------------------------------------|");
        System.out.println("|          Publicar receita:          |");
        System.out.println("|-------------------------------------|");
        
    	ReceitaDAO ReceitaDAO = DAOfactory.createReceitaDao();
    	System.out.println("|To publish your recipe, fill in the necessary information:|");
    	String descricao, nome;
    	System.out.println("|Name: ");
    	nome = t.nextLine();
    	System.out.println("|Recipe description: ");
    	descricao = t.nextLine();
    	String nome_conta = contalogada.getNome();
    	Receita newReceita = new Receita(nome,descricao,nome_conta);
    	ReceitaDAO.insert(newReceita);
	}
	
	private static void buscarReceita () {
		Scanner t = new Scanner (System.in);
		ReceitaDAO ReceitaDAO = DAOfactory.createReceitaDao();
		System.out.println("|-------------------------------------|");
        System.out.println("|           Search recipe:            |");
        System.out.println("|-------------------------------------|");
        System.out.println("Please enter the id of the recipe: ");
        int id = t.nextInt();
		Receita Receita = new Receita ();
		Receita = ReceitaDAO.findById(id);
		System.out.println(Receita);
	}
	
	private static void atualizarReceita() {
		Scanner t = new Scanner (System.in);
		ReceitaDAO ReceitaDAO = DAOfactory.createReceitaDao();
		System.out.println("|-------------------------------------|");
        System.out.println("|            Update recipe:           |");
        System.out.println("|-------------------------------------|");
        System.out.println("Please enter the id of the recipe you want to update: ");
        String id = t.nextLine();
		Receita Receita = new Receita ();
		Receita = ReceitaDAO.findById(Integer. parseInt(id));
		System.out.println("|Enter the new recipe values: ");
		System.out.println("|Name: ");
		Receita.setNome(t.nextLine());
		System.out.println("|Description: ");
		Receita.setDescricao(t.nextLine());

		ReceitaDAO.update(Receita,contalogada.getNome());
	}
	
	private static void contaLogada() {
		Scanner t = new Scanner (System.in);
        System.out.println("|---------------------------------------|");
        System.out.println("|                 Login:                |");
        System.out.println("|---------------------------------------|");
        System.out.print("|Email: ");
        String email = t.next();
        System.out.print("|Password: ");
        String senha = t.next();
        
        ContaDAO contaDao = DAOfactory.createContaDao();
		contalogada = contaDao.login(email,senha);
		System.out.println("|You logged into "+contalogada.getNome()+" account successfully!");
		gerenciarMenuConta();
    }
	
	private static void gerenciarMenuReceita() {
		Scanner t = new Scanner (System.in);
        while (menureceita != 2) {
            menuReceita();
            menureceita = t.nextInt();
            switch (menureceita) {
                case 3:
                	publicarReceita();
                    break;
                case 4:
                	listaReceitas();
                    break;
                case 5:
                	deletarReceita();
                	break;
                case 6:
                	atualizarReceita();
                	break;
                case 7:
                	buscarReceita();
                	break;
                case 2:
                	System.out.println("|Thank you for using ILASR!");
                	menureceita = 2;
                	menuconta = 7;
                	menu = 4;
                	break;
                default:
                    System.out.println("|Please select an existing menu: ");
            }
        }
        menureceita = 0;

	}
	
	private static void gerenciarMenuConta(){
		Scanner t = new Scanner (System.in);
        while (menuconta != 7) {
            menuConta();
            menuconta = t.nextInt();
            switch (menuconta) {
                case 1:
                    deletarConta();
                    break;
                case 2:
                	gerenciarMenuReceita();
                    break;
                case 3:
                    updateConta();
                    break;
                case 4:
                	mostrarConta();
                	break;
                case 5:
                	listaContas();
                	break;
                case 6:
                	buscarConta();
                	break;
                case 7:
                	System.out.println("|Thank you for using ILASR!");
                	menu = 4;
                	break;
                default:
                    System.out.println("|Please select an existing menu: ");
            }
        }
        menuconta = 0;

    }
	
}
