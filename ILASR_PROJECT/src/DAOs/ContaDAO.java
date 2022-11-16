package DAOs;
import java.util.List;

import entities.Conta;

public interface ContaDAO {
	boolean existente (Conta obj);
	Conta login(String Email, String Senha);
	void insert(Conta obj);
	void update(Conta obj);
	void deletebyId(Integer id);
	Conta findById(Integer Id);
	List <Conta> findAll();
}