import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {
    private static ConnectionManager connectionManager;

    private Socket socket;
    private InetAddress hostAddress;
    private static final int PORT = 22222;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
	if (connectionManager != null) {
	    System.out.println("Zwracam isteniejący obiekt Connection Manager");
	    return connectionManager;
	} else {
	    System.out.println("Tworzę nowy obiekt Connection Manager");
	    return new ConnectionManager();
	}
    }

    public ConnectionManager setServer(String hostAddress) {
	try {
	    System.out.println("Szukam adresu hosta");
	    this.hostAddress = InetAddress.getByName(hostAddress);
	} catch (UnknownHostException e) {
	    System.out.println("Adress hosta nie został rozpoznany!");
	    e.printStackTrace();
	}
	return this;
    }

    public void openConnection() {
	if (hostAddress != null) {
	    try {
		socket = new Socket(hostAddress, PORT);
		System.out.println("Connected to server");
	    } catch (IOException e) {
		System.out.println("Nie udało się nawiązać połączenia!");
		e.printStackTrace();
	    }
	} else {
	    System.out.println("Host address is null");
	}
    }

    public void closeConnection() {
	try {
	    socket.close();
	    System.out.println("Connection closed.");
	} catch (IOException e) {
	    System.out.println("Nie udało się zamknąć polączenia!");
	    e.printStackTrace();
	}
    }

    public void send(String message) {
	try {
	    dataOutputStream = new DataOutputStream(socket.getOutputStream());
	    dataOutputStream.write(message.getBytes());
	    // dataOutputStream.close();
	    System.out.println("Data has been sent.");
	} catch (IOException e) {
	    System.out.println("Data sending error.");
	    e.printStackTrace();
	}
    }

    public String receive() {
	String message;
	byte[] b = new byte[256];
	try {
	    dataInputStream = new DataInputStream(socket.getInputStream());
	    dataInputStream.read(b);

	    message = new String(b);

	    System.out.println("Data has been received: " + message);
	} catch (IOException e) {
	    message = "";
	    System.out.println("Data reciving error.");
	    e.printStackTrace();
	}
	return message;
    }
}
