package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chatapp.dao.UserDAO;
import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField useridtxt;

	
	public static void main(String[] args) {
	
					UserScreen window = new UserScreen();
			
	}
	UserDAO userDAO=new UserDAO();
	private void doLogin() {
		char[] password= passwordField.getPassword();
		String userid=useridtxt.getText();
		
		UserDTO userDTO=new UserDTO(userid,password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO)) {
				message="welcome"+userid;
				UserInfo.USER_NAME=userid;
				JOptionPane.showMessageDialog(this,message);
				setVisible(false);
				dispose();
				Dashboard dashboard=new Dashboard(message);
				dashboard.setVisible(true);
				//JOptionPane.showMessageDialog(this,"Wecome"+userid);
			}
			else {
				message="Invalid Userid or Password";
				JOptionPane.showMessageDialog(this,message);
			}
			//JOptionPane.showMessageDialog(this,message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	private void register() {
		
		char[] password= passwordField.getPassword();
		String userid=useridtxt.getText();
		//UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(userid,password);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this,"Register Successfully");
			//System.out.println("record added...");
		}
		else {
			JOptionPane.showMessageDialog(this,"Register Fail");
			//System.out.println("record not added...");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		}
		catch(Exception ex){
		System.out.println("Some generic exception");
		ex.printStackTrace();//where is the exception
		}
		System.out.println(" userid "+userid+" Password "+password);//classname@hashcode(hexdecimalform)
		
		
	}


	public UserScreen() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(319, 40, 175, 75);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(319, 151, 187, 33);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(319, 195, 187, 33);
		getContentPane().add(passwordField);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		useridlbl.setBounds(196, 151, 107, 33);
		getContentPane().add(useridlbl);
		
		JLabel Paswdlbl = new JLabel("Password");
		Paswdlbl.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		Paswdlbl.setBounds(196, 187, 60, 28);
		getContentPane().add(Paswdlbl);
		
		JButton Loginbt = new JButton("Login");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		Loginbt.setBounds(196, 273, 132, 41);
		getContentPane().add(Loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			register() ;
			}
		});
		Registerbt.setBounds(374, 273, 132, 41);
		getContentPane().add(Registerbt);
		setTitle("Login");
		setSize( 833, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
