package pe.com.belatrix.messabel.exercise.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import pe.com.belatrix.messabel.exercise.factory.DbConnection;
import pe.com.belatrix.messabel.exercise.interfaces.MessageDAO;

public class MySqlMessageDAO implements MessageDAO{

	public Connection getConnection(){
		DbConnection mysql = new DbConnection();
		return (Connection) mysql.getConnection();
	}
	
	@Override
	public void insertMessage(String message, Integer t) throws Exception {
		String sql = 
				"insert into Log_Values values('"
						+ message
						+"', "
						+ String.valueOf(t)
						+ ")";
			Connection conn = null;
			try {
				conn = getConnection();
				Statement stm = conn.createStatement();
				stm.executeUpdate(sql);
				if (stm != null){
					stm.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if (conn != null){
					conn.close();
	            }
			}
	}

}
