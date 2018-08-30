import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * This class return the response from the request,
 * as a string.
 * 
 * @author Owoaje Temi
 *
 */
public class HTTPresponse {
	
	HTTPrequest req;
	
	// final response which is generated
	String response;
	
	// root path of the server// this just contins all the file
	String root = "C:\\Root";
	/**
	 * Constructor takes in a HTTP request
	 * and generates String response.
	 * @param req
	 * @throws Exception 404 if file not found, 500 if internal server error.
	 */
	public HTTPresponse(HTTPrequest req) throws Exception {
		this.req = req;
		//open the file mentioned in request
		File f = new File(root + req.filename );
		
		//to read file
		try {
			//usually how responses look when generated.
			/**HTTP/1.1 200 OK\\r\\n \r\n" + 
					"Content-Length: 55\\r\\n \r\n" + 
					"Content-Type: text/html\\r\\n \r\n" + 
					"Last-Modified: Wed, 12 Aug 1998 15:03:50 GMT\\r\\n \r\n" + 
					"Accept-Ranges: bytes\\r\\n \r\n" + 
					"ETag: “04f97692cbd1:377”\\r\\n \r\n" + 
					"Date: Thu, 19 Jun 2008 19:29:07 GMT\\r\\n \r\n" + 
					"\\r\\n \r\n" + 
					"<55-character response>";**/
			
			response = "HTTP/1.1 200";// version o f http and 200 for status code
			//200 means eveyrhting is ok
			
			response += "Server: Our Java Server/1.0 \r\n";//identify of server;
			response += "Content-Type: text/html \r\n";// response is in html format
			// this line tells the browser that close the connection
			// there will be no future transmission will take place in this connection
			response += "Conection: close \r\n";
			response += "Content-Lenght: " + f.length() + " \r\n";//length of response file
			FileInputStream fis = new FileInputStream(f);
			response += "\r\n"; // append file data after blank line
			int s;
			while((s= fis.read()) != -1) {//-1 means end of file
				response += (char) s;
			}
			fis.close();
		}catch(FileNotFoundException e) {
			//error 404 if we dont get file
			
			response = response.replace("200", "404");
		}catch(Exception e) {
			// other error 500 internal sever error
			response = response.replace("200", "500");
		}
	}
	
	

}
