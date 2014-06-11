package com.dev.server.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DataExchangeServer {

	private final int PORTNUMBER = 2010;
	private final boolean listening = true;

	public DataExchangeServer() {

		try (ServerSocket serverSocket = new ServerSocket(PORTNUMBER)) {
			while (listening) {
				Socket socket = serverSocket.accept();
				Thread t = new MultiServerThread(socket);
				t.start();

			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + PORTNUMBER);
			System.exit(-1);
		}
	}

	public static void main(String[] args) throws IOException {
		DataExchangeServer dataExchangeServer = new DataExchangeServer();
	}
}
