package com.dev.server.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.dev.node.NoteModel;
import com.dev.server.data.model.DataSourceModel;

class MultiServerThread extends Thread {
	private Socket socket = null;
	private DataSourceModel dataSourceModel;
	private String protocol;
	private ArrayList<NoteModel> storedNodes;

	public MultiServerThread(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	public void setDataSourceModel(DataSourceModel dataSourceModel) {
		System.err.println("dataSourceModel is binded");
		this.dataSourceModel = dataSourceModel;
	}

	public void run() {

		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
			protocol = (String) ois.readObject();
			if (protocol.equals("get object")) {
				storedNodes = dataSourceModel.getStoredNodes();
				oos.writeObject(storedNodes);
				socket.close();
			} else if (protocol.equals("push object")) {
				storedNodes = (ArrayList<NoteModel>) ois.readObject();
				dataSourceModel.setNodes(storedNodes);
			} else
				throw new IllegalProtocolException();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalProtocolException e) {
			e.printStackTrace();
		}
	}
}

class IllegalProtocolException extends java.lang.Exception {

}
