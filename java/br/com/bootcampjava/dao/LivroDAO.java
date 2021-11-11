package br.com.bootcampjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bootcampjava.dto.Livro;
import br.com.bootcampjava.exceptions.DAOException;

/**
 * 
 * @author jose.castilho Bootcamp Java
 *
 */
public class LivroDAO {
	
	/**
	 * Insere um livro no banco de dados e gera um id automaticamente
	 * @param livro 
	 * @return o livro com o id gerado
	 * @throws DAOException
	 */
	public Livro inserir(Livro livro) throws DAOException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO livro ");
		sql.append(" ( ");
		sql.append("	titulo  ");
		sql.append(" ) ");
		sql.append("  VALUES (?) ;");

		System.out.println(sql.toString());
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = ConnectionFactory.getConnection();	
			
			ps = con.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, livro.getTitulo());
			
			ps.execute();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				Long generatedId = rs.getLong(1);
				livro.setId(generatedId);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){
					throw new DAOException(e);
				}
			}
			
			if(ps != null){
				try{
					ps.close();
				}catch(SQLException e){
					throw new DAOException(e);
				}
			}

			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					throw new DAOException(e);
				}
			}
		}
		return livro;
	}
	
	/**
	 * exclui o livro do banco de dados 
	 * @param id do livro a excluir
	 * @return true caso o livro seja excluido com sucesso
	 * @throws DAOException
	 */
	public boolean excluir(Long idLivro) throws DAOException{
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from livro where id = ? ");

		System.out.println(sql.toString());
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ConnectionFactory.getConnection();	
			
			ps = con.prepareStatement(sql.toString());

			ps.setLong(1, idLivro);
			
			result = ps.execute();
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException(e);
		}finally{		
			if(ps != null){
				try{
					ps.close();
				}catch(SQLException e){
					throw new DAOException(e);
				}
			}

			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					throw new DAOException(e);
				}
			}
		}
		return result;
	}
	
	/**
	 * Lista todos os livros cadastrados ordenados pelo titulo em ordem ascendente
	 * @return
	 * @throws DAOException
	 */
	public List<Livro> listarTodos() throws DAOException{
		List<Livro> result = new ArrayList<Livro>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("		id,");
		sql.append("		titulo");
		sql.append("	FROM livro order by titulo asc ");
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionFactory.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Livro livro = null;
			while(rs.next()){
				livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				result.add(livro);
			}
			
		}catch(Exception e){
			throw new DAOException(e);
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return result;
	}
	
	/**
	 * Lista todos os livros cadastrados que contenham o termo fornecido como parametro
	 * @param termo
	 * @return
	 * @throws DAOException
	 */
	public List<Livro> listarComTituloQueContenha(String termo) throws DAOException{
		List<Livro> result = new ArrayList<Livro>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("		id,");
		sql.append("		titulo");
		sql.append("	FROM livro where titulo like ? order by titulo asc ");
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ConnectionFactory.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + termo + "%");
			rs = pstmt.executeQuery();
			Livro livro = null;
			while(rs.next()){
				livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				result.add(livro);
			}
			
		}catch(Exception e){
			throw new DAOException(e);
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return result;
	}
	
	public static void main(String[] args) throws DAOException {
		List<Livro> livros = null;
		LivroDAO dao = new LivroDAO();
		
		System.out.println("Excluiu? " + dao.excluir(2l));
		livros = dao.listarComTituloQueContenha("Java");
		
		System.out.println("Livros encontrados: " + livros.size());
		for(Livro livro: livros) {
			System.out.println("Livro com id: " + livro.getId() + " titulo: " + livro.getTitulo());
		}
	}
}
