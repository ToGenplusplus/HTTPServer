import java.net.ServerSocket;
import java.net.Socket;
/**
 * This class represents the main class for our server
 * Incorporates java's .net SserverSocket and Socket.
 * @author Owoaje Temi
 *
 */
public class mainclass {
	// initialize socket
	ServerSocket ssocket;

	// main entry point for program.
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		new mainclass().runServer(); // avoid any problem with static fields
	}

	/**
	 * run the server, creates a server at a port number to use 
	 * as local host.
	 * @throws Exception if server couldn't connect.
	 */
	public void runServer() throws Exception {
		System.out.println("Server started");
		ssocket = new ServerSocket(6543); // create server at specific port number.
		
		acceptRequest();// accepting request
	}
	/**
	 * method accepts all request to connect to server.
	 * 
	 * @throws Exception if request didn't come through successfully.
	 */
	private void acceptRequest() throws Exception {
		// to accept all request
		while(true) {
			Socket s = ssocket.accept();
			ConnectionHandler con = new ConnectionHandler(s);
			
			// con IS THE thread, and we have to start our thread using
			con.start();// this will call the run method
		}
	}
	
	
}
