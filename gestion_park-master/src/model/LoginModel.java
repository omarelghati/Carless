package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;

public class LoginModel {
	Connection connection ;
	public LoginModel(){
		connection = SqliteConnection.connector();
		if(connection==null) System.exit(1);
	}
	public boolean isDatabaseConnected(){
		try{
			return !connection.isClosed();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean isLogin(String user,String pass) throws SQLException {
		PreparedStatement prepa = null ;
		ResultSet result = null;
		String query = "select * from employee where username = ? and password = ?";
		try{
			prepa = connection.prepareStatement(query);
			prepa.setString(1, user);
			prepa.setString(2, pass);
			result = prepa.executeQuery();
			if(result.next()){
				return true;
			}else {
				return false;
			}

		}catch(Exception e){
			return false;
		}finally{
			prepa.close();
			result.close();
		}

	}

}
