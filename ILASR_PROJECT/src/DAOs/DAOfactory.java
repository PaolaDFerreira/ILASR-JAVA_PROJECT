package DAOs;

import DAOs.ContaDAO;
import DAOs.ContaDAOJDBC;
import DAOs.ReceitaDAO;
import DAOs.ReceitaDAOJDBC;
import db.DB;

public class DAOfactory {
	
	public static ContaDAO createContaDao() {
		return new ContaDAOJDBC(DB.getConnection());
	}
	
	public static ReceitaDAO createReceitaDao() {
		return new ReceitaDAOJDBC();
	}
	
}
