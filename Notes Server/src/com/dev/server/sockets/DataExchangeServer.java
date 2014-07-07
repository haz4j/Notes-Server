package com.dev.server.sockets;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * The Class DataExchangeServer.
 */
@Component
public class DataExchangeServer {

	/** The listening. */
	private final boolean listening = true;

	/** The Constant context. */
	private final static ApplicationContext context = new ClassPathXmlApplicationContext("beansConfFileServer.xml");

	/**
	 * Instantiates a new data exchange server.
	 */
	public DataExchangeServer() {

	}

	/**
	 * Execute. Launches MultiServerThread.
	 */
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

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		DataExchangeServer dataExchangeServer = (DataExchangeServer) context.getBean("dataExchangeServer");
		dataExchangeServer.execute();
	}
}
