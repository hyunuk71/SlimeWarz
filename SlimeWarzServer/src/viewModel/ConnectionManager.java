package viewModel;

import model.ServerModel;

import java.io.IOException;
import java.net.InetAddress;

public class ConnectionManager {
	private ServerModel serverModel;
	private InetAddress ip;

	public void closeServer() {
		final String MSG_CLOSING_SERVER = "Closing Server";

		if (serverModel != null) {
			try {
				serverModel.close();
				System.out.println(MSG_CLOSING_SERVER);
			} catch (IOException err) {
				System.out.println(err.getMessage());
			}
		}
	}

	public void createServer(int portNum) {
		try {
			serverModel = new ServerModel(portNum);
			serverModel.start();
			serverModel.addObserver(this);
		} catch (IOException e) {
			String errorMSG = "PORT #" + portNum + " is already opened.\r\n";
			printLog(errorMSG);
		}
	}

	public String getIP() {
		try {
			ip = InetAddress.getLocalHost();
		} catch (Exception ignored) {}
		return String.valueOf(ip.getHostAddress());
	}
}