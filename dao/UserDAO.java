package com.brainmentors.chatapp.dao;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.Encryption;

public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="Select userid from users where userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedPwd);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//			return true;
//			}
//			else {
//				return false;
//			}
		}
		finally{
			if(pstmt!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
    //public int add(String userid,String city,byte age,String phone,String email){
    	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException ,Exception{
    		System.out.println(" Receive "+userDTO.getUserid()+"  "+userDTO.getPassword());
    		Connection connection=null;
    		Statement stmt=null;//query
    		try {//guarded region
    		connection=CommonDAO.createConnection();//step1 connection create
    		//step2 we do a query
    		stmt=connection.createStatement();
    		int record=stmt.executeUpdate("insert into users(userid,password)values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");//insert,delete,update
    		return record;
    		}
    		finally {//always execute
    			if(stmt!=null) {
    		stmt.close();
    			}
    			if(connection!=null) {
    		connection.close();
    		}
    		}
    		
    		
    }
}
