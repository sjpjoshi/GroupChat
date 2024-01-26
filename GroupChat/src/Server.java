import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable{

	Socket socket;
	public static Vector client = new Vector();
	
	public Server(Socket socket) {
		
		try {
			
			this.socket = socket;
			
		} catch(Exception e) {} // catch
		
	} // server

	public static void main(String [] args) throws Exception {
		
		ServerSocket s = new ServerSocket(2007);
		
		while(true) {
			
			Socket socket = s.accept();
			Server server = new Server(socket);		
			Thread thread = new Thread(server);
			thread.start();
			
		} // while
		
	} // main

	@Override
	public void run() {

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			client.add(writer);
			
			while(true) {
				
				String data = reader.readLine().trim();
				System.out.println("Taken: " + data);
				
				for(int i = 0; i < client.size(); i++) {
					
					try {
						
						BufferedWriter writer2 = (BufferedWriter) client.get(i);
						writer2.write(data);
						writer2.write("\r\n");
						writer2.flush();
						
					} catch(Exception e) {}
					
				} // for
 				
			} // while
			
		} catch (Exception e) {} // catch
		
	} // run
	
} // class
