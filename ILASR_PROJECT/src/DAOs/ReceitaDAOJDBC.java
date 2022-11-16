package DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entities.Receita;
import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.util.ArrayList;
import java.util.List;

import entities.Conta;

public class ReceitaDAOJDBC implements ReceitaDAO {
	private Connection conn;
	
	public ReceitaDAOJDBC () {}
	
	public ReceitaDAOJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Receita obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO receita "
					+ "(Nome_receita, Descricao, Data_publicacao, curtidas, Nome) "
					+ "VALUES "
					+ "(?, ?, CURRENT_TIMESTAMP, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setDouble(3, 0);
			st.setString(4, obj.getNomeConta());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("|Your recipe has been published successfully!");
					
				}
			}
			else {
				System.out.println("|There was an error, your recipe could not be published.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void update(Receita obj, String nome) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE receita "
					+ "SET Nome_receita = ?, Descricao = ? "
					+ "WHERE Id_receita = ? ");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setInt(3, obj.getId());
			int rowsAffected = st.executeUpdate();
			System.out.println("|Your recipe has been updated successfully!"
					+"\n|Current data of your recipe: "+"\n"+obj);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deletebyId(Integer id, Conta obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM receita "
					+ " WHERE "
					+ " Id_receita = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();	
			System.out.println("Your recipe has been deleted!");
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
	}
	}
	
	@Override
	public Receita findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT receita.*"
								+"FROM receita "
								+"WHERE receita.Id_receita = ? ");	
			st.setInt(1, Id);
			rs = st.executeQuery();
			if (rs.next()) {
				Receita receita = instantiateReceita(rs);
				return receita;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	@Override
	public boolean pertence(Integer id, Conta obj2) {
		boolean valor = false;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT receita.*"
								+"FROM receita "
								+"WHERE receita.Id_receita = ? ");	
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Receita receita = instantiateReceita(rs);
				if (receita.getNomeConta() == obj2.getNome()) {valor = true;}
			}
			return valor;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		}
	
	@Override
	public boolean existente (String Nome, String Email) {
		List <Receita> conferir = findAll();
		for (Receita a: conferir) {
		if (a.getNome().equals(Nome)) {
		return true;
		}
		}
		return false;	
		}
	
	@Override
	public List <Receita> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT receita.* "
								+"FROM receita "
								+"ORDER BY Nome_receita ");	
			rs = st.executeQuery();
			List<Receita> list = new ArrayList <> ();
			while (rs.next()) {
				Receita obj = instantiateReceita(rs);
				list.add(obj);
			}
				return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Receita instantiateReceita (ResultSet rs) throws SQLException {
		Receita receita = new Receita ();
		receita.setId(rs.getInt("Id_receita"));
		receita.setNome(rs.getString("Nome_receita"));
		receita.setDescricao(rs.getString("Descricao"));
		receita.setData(rs.getDate("Data_publicacao"));
		receita.setCurtidas(rs.getInt("curtidas"));
		receita.setNomeConta(rs.getString("Nome"));
		return receita;
	}



	
}
