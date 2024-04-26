package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();//contains alll the socket
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println(" SERVER START AND WAITINT FOR THE CLIENT TO JOIN ");
		handleClientRequest() ;
	}
	//MULTIPLE CLIENT HANDSHAKING
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket=serverSocket.accept();//HANDSHAKING
			//PER CLIENT PER THREAD
			ServerWorker serverWorker=new ServerWorker(clientSocket,this);//creating a new worker
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	//SINGLE CLIENT
	/*
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection...");
		Socket socket=serverSocket.accept();///HANDSHAKING
		System.out.println("Client joins the Server");
		InputStream in=socket.getInputStream();
		byte arr[]=in.readAllBytes();
		String str=new String(arr);//bytes convert into string
		System.out.println("Message received from the Client"+str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server=new Server();

	}

}
