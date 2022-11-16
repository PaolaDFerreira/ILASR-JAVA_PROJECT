package DAOs;
import java.util.List;
import entities.Conta;
import entities.Receita;

public interface ReceitaDAO {
	boolean existente (String Nome, String Email);
	public void insert(Receita obj);
	public void update(Receita obj, String nome);
	public boolean pertence(Integer id, Conta obj2);
	public void deletebyId(Integer id, Conta obj);
	Receita findById(Integer Id);
	List <Receita> findAll();
}