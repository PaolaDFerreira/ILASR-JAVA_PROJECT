package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import entities.Conta;

public class ContaDAOJDBC implements ContaDAO{
	private Connection conn;
	
	public ContaDAOJDBC () {}
	
	public ContaDAOJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Conta login(String Email, String Senha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT conta.*"
								+"FROM conta "
								+"WHERE conta.Email = ? AND conta.Senha = ? ");	
			st.setString(1, Email);
			st.setString(2, Senha);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta conta = instantiateConta(rs);
				return conta;
			}
			else {
				System.out.println("|Unable to login.");
			return null;}
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
	public void insert(Conta obj) {
		Connection conn = null;
		PreparedStatement st = null;
		if (existente(obj) != true) {
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO conta "
					+ "(Resumo, Nome, Email, Senha) "
					+ "VALUES "
					+ "(?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getResumo());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getSenha());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				System.out.println("|Registration successfully completed!"
						+"\n|Account details: "+"\n"+obj);
			}
			else {
				System.out.println("|Unable to register!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);

		}
	}
		else {System.out.println("|Unable to register. Username and/or Email already exist!");}
	}
	
	@Override
	public void update(Conta obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE conta "
					+ "SET Resumo = ?, Nome = ?, Email = ?, Senha = ?"
					+ "WHERE Id_conta = ? ");
			
			st.setString(1, obj.getResumo());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getSenha());
			st.setInt(5, obj.getId());
			int rowsAffected = st.executeUpdate();
			System.out.println("|Your account has been updated successfully!"
					+"\n|Current account details:\n"+"\n"+obj);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);

		}
	}
		
	@Override
	public void deletebyId(Integer id) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM conta "
					+ "WHERE "
					+ "Id_conta = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
	
	public boolean existente (Conta obj) {
	List <Conta> conferir = findAll();
	for (Conta a: conferir) {
	if (a.getNome().equals(obj.getNome())) {
	return true;
	}
	if (a.getEmail().equals(obj.getEmail())) {
	return true;
	}
	}
	return false;	
	}
	
	private Conta instantiateConta (ResultSet rs) throws SQLException {
		Conta conta = new Conta ();
		conta.setId(rs.getInt("Id_conta"));
		conta.setResumo(rs.getString("Resumo"));
		conta.setNome(rs.getString("Nome"));
		conta.setEmail(rs.getString("Email"));
		conta.setSenha(rs.getString("Senha"));
		return conta;
	}
	
	@Override
	public Conta findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT conta.*"
								+"FROM conta "
								+"WHERE conta.Id_conta = ? ");	
			st.setInt(1, Id);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta conta = instantiateConta(rs);
				return conta;
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
	public List <Conta> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT conta.* "
								+"FROM conta "
								+"ORDER BY Nome ");	
			rs = st.executeQuery();
			List<Conta> list = new ArrayList <> ();
			while (rs.next()) {
				Conta obj = instantiateConta(rs);
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
}