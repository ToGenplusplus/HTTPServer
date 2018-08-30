/**
 * This class gets and reads the request
 * for the connection handler to read.
 * @author Owoaje Temi
 *
 */
public class HTTPrequest {
	/*
	 * first line contins 3 parts
	 * 1-requesttype
	 * 2-file
	 * 3-http version
	 */
	
	String filename;
	public HTTPrequest(String req) {
		//only the 1st lines is important /* GET / HTTP/1.1
		
		
		String line[] = req.split("\n");
			// this gives us all the lines of request specifically
		
		// spilts the first line using space.
		filename = line[0].split(" ") [1];
	}

}
