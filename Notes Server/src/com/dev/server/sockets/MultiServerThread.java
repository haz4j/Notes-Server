package com.dev.server.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dev.note.NoteModel;
import com.dev.server.data.DataSourceModel;

/**
 * The Class MultiServerThread that launched by DataExchangeServer. The only
 * target of this class is to provide the separate thread for each connection
 * with client.
 */
@Component
@Scope("prototype")
class MultiServerThread extends Thread {

	/** The socket. */
	private Socket socket = null;

	/** The data source model. */
	private DataSourceModel dataSourceModel;

	/**
	 * The protocol. Format is very simple: if we need to provide object from
	 * client we send "push object" from client. Or "get object" otherwise
	 */
	private String protocol;

	/** The stored notes. */
	private ArrayList<NoteModel> storedNotes;

	/**
	 * Instantiates a new multi server thread.
	 *
	 * @param socket
	 *            the socket
	 */
	@Autowired
	public MultiServerThread(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	/**
	 * Sets the data source model.
	 *
	 * @param dataSourceModel
	 *            the new data source model
	 */
	@Autowired
	public void setDataSourceModel(DataSourceModel dataSourceModel) {
		this.dataSourceModel = dataSourceModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
			protocol = (String) ois.readObject();
			if (protocol.equals("get object")) {
				storedNotes = dataSourceModel.getStoredNotes();

				oos.writeObject(storedNotes);
				socket.close();
			} else if (protocol.equals("push object")) {
				storedNotes = (ArrayList<NoteModel>) ois.readObject();

				dataSourceModel.setNotes(storedNotes);
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
