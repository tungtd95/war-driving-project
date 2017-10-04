package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.tungvu.wardriving.Objects.SendToServer;

import modules.DBC;


public class ServerMain {
	
	private static final int PORT = 1357;
	private static DBC dbc;
	
	public static void main(String[] args) throws IOException{
		
		dbc = new DBC();
		int id_thread = 0;
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Tung-pc server is ready!");
		Socket socket;
		while(true){
			try {
				socket = serverSocket.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				Object o = ois.readObject();
				if(o instanceof SendToServer){
					id_thread++;
					SendToServer sendToServer = (SendToServer) o;
					dos.writeUTF("data's size = "+sendToServer.getListSample().size());
					System.out.println("\n--------News from Client--------");
					System.out.println("List received size: "+sendToServer.getListSample().size());
					new ServerThread(sendToServer, id_thread).start();
				}else{
					dos.writeUTF("data error");
				}
				
				dos.close();
				ois.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
