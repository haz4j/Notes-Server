package com.dev.server.sockets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataExchangeServer {

	private final int PORTNUMBER = 2010;
	private final boolean listening = true;
	private final static ApplicationContext context = new ClassPathXmlApplicationContext("beansConfFileServer.xml");

	public DataExchangeServer() {

	}

	public void execute() {
		try (ServerSocket serverSocket = (ServerSocket) context.getBean("serverSocket")) {
			while (listening) {
				Thread t = (MultiServerThread) context.getBean("multiServerThread");
				t.start();
			}

		} catch (IOException e) {
			System.err.println("Could not listen on port ");
			System.exit(-1);
		}
	}

	public static void main(String[] args) throws IOException {
		DataExchangeServer dataExchangeServer = (DataExchangeServer) context.getBean("dataExchangeServer");
		dataExchangeServer.execute();
	}
}
