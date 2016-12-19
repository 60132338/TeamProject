package database;

import java.sql.*;

import constants.CSConstants;

public class SelectFromCoffee {
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	
	public SelectFromCoffee(){
		this.con = null;
		this.stmt = null;
	}
	
	// coffee 테이블에서 모든 데이터를 가져오는 메소드
	public ResultSet searchAll(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFound Exception: ");
			System.err.println("드라이버 로딩 오류: "+e.getMessage());
			return null;
		}
		
		try{
			con = DriverManager.getConnection(CSConstants.URL, CSConstants.USERID, CSConstants.USERPWD);
			stmt = con.createStatement();
			
			result = stmt.executeQuery(CSConstants.SEARCHALL);
			return result;
		}catch(SQLException e){
			System.err.println("Select 오류: "+e.getMessage());
			return null;
		}
	}
	
	// 커피메뉴의 이름을 통해서 데이터베이스에서 검색해주는 메소드
	public ResultSet searchToMenu(String word){
		String query = CSConstants.SEARCHALL+" WHERE menu LIKE '%"+word+"%' OR etc LIKE \'%"+word+"%\'";
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.println("드라이버 로딩 오류: "+e.getMessage());
			return null;
		}
		
		try{
			con = DriverManager.getConnection(CSConstants.URL, CSConstants.USERID, CSConstants.USERPWD);
			stmt = con.createStatement();
			
			result = stmt.executeQuery(query);
			return result;
		}catch(SQLException e){
			System.err.println("Select 오류: "+e.getMessage());
			return null;
		}
	}
	
	// 브랜드를 통해 검색해주는 메소드
	public ResultSet searchToBrand(String word){
		String query = CSConstants.SEARCHALL+" WHERE brand='"+word+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.println("드라이버 로딩 오류: "+e.getMessage());
			return null;
		}
		
		try{
			con = DriverManager.getConnection(CSConstants.URL, CSConstants.USERID, CSConstants.USERPWD);
			stmt = con.createStatement();
			
			result = stmt.executeQuery(query);
			return result;
		}catch(SQLException e){
			System.err.println("Select 오류: "+e.getMessage());
			return null;
		}
	}
	
	//메뉴이름과 브랜드 이름을 통해 한개 메뉴를 검색하는 메소드
	public ResultSet searchToDetail(String brand, String menu){
		String query = CSConstants.SEARCHALL+" WHERE brand='"+brand+"' AND menu='"+menu+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.println("드라이버 로딩 오류: "+e.getMessage());
			return null;
		}
		
		try{
			con = DriverManager.getConnection(CSConstants.URL, CSConstants.USERID, CSConstants.USERPWD);
			stmt = con.createStatement();
			
			result = stmt.executeQuery(query);
			return result;
		}catch(SQLException e){
			System.err.println("Select 오류: "+e.getMessage());
			return null;
		}
	}
}
