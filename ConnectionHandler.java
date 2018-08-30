import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * class handles all the connection which contains the request. Extends thread
 * @author Owoaje Temi
 *
 */
public class ConnectionHandler extends Thread {// class becomes Thread by extending thread
	
	Socket sok;
	
	// sendin the output to client
	PrintWriter pw;
	
	// getting the input from client
	BufferedReader br;
	// this  is a constructor and it accepts a socket
	public ConnectionHandler(Socket sok) throws Exception {
		this.sok = sok;
		br = new BufferedReader(new InputStreamReader(sok.getInputStream()));
		pw = new PrintWriter(sok.getOutputStream());
	}
	// thread class already contains a method run which is called automatically when we start the thread
	// we are reading the request and giving the response
	@Override
	public void run() {
	
		try {
		// here we get the request string and give this string to HttpRequest class
		
		String reqs = "";
		
		
// from br we have to read our request
		//ready until request is not get or br is ready
		while(br.ready() || reqs.length() == 0) {
			reqs += (char) br.read();
		}
		System.out.println(reqs);// for display
		HTTPrequest hr = new HTTPrequest(reqs);
		
		// now pass the httprequest object to httpresponse class for getting the response
		
		HTTPresponse hres = new HTTPresponse(hr);
		
		// write final output to pw
		pw.write(hres.response.toCharArray());
		
		
		pw.close();
		br.close();
		sok.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
