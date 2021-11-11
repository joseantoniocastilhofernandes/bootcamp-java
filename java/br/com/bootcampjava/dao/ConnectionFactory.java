package br.com.bootcampjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://bootcamp.c90o86kxnub3.us-west-2.rds.amazonaws.com:3306/bootcamp","aluno","12345678"); 
		return con;
	}
	public static String generateUUID(Connection con) throws Exception{
		String id = null;
		String uuid_query = "select UUID() as id";
		PreparedStatement preparedStatementUUID = null;
		ResultSet rs = null;
		try{

			preparedStatementUUID = con.prepareStatement(uuid_query);
			rs = preparedStatementUUID.executeQuery();
			if(rs.next()){
				id = rs.getString("id");
			}
		}finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(preparedStatementUUID != null){
				try {
					preparedStatementUUID.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	public static void main(String[] args) throws Exception {
		Connection con = getConnection();
		System.out.println( con);
		con.close();
	}
}
