package com.brainmentors.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.brainmentors.chatapp.utils.ConfigReader.getValue;
public interface CommonDAO {
    public static  Connection createConnection() throws ClassNotFoundException, SQLException {
    	//step-1 load a driver
    	Class.forName(getValue("DRIVER"));
    	//step 2 making a connection
    	final String CONNECTION_STRING=getValue("CONNECTION_URL");
    	final String USER_ID=getValue("USERID");
    	final String PASSWORD=getValue("PASSWORD");
    	Connection con=DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
    	if(con!=null) {
    		System.out.println("Connection Created...");
    		//con.close();
    	}
    	return con;
    }
    //public static void main(String args[]) throws ClassNotFoundException, SQLException {
    	//CommonDAO commonDAO=new CommonDAO();
    	//commonDAO.createConnection();
    }
//}
