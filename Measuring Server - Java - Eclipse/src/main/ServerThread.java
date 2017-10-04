package main;

import com.example.tungvu.wardriving.Objects.SendToServer;

import modules.DBC;

public class ServerThread extends Thread {
	private DBC dbc;
	private SendToServer sendToServer;
	private int id_thread;
	public ServerThread(SendToServer sendToServer, int id_thread){
		this.sendToServer = sendToServer;
		dbc = new DBC();
		this.id_thread = id_thread;
	}
	public void run(){
		dbc.addRawData(sendToServer, id_thread);
	}
}
