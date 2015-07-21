package rockpaperscissorslizardspock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

/**
 *
 * @author Architect
 */
public class RockPaperScissorsLizardSpockServer {

    public static void main(String[] args) {
        
        Integer portNumber = 4444;
        Executor clientConnect = Executors.newCachedThreadPool();
        ProcessRequest processRequest;
        boolean listening = true;
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            
            while (listening) {
            
                processRequest = new ProcessRequest(serverSocket.accept());
            
                clientConnect.execute(processRequest);
                
            }
            
        }
        
        catch (IOException e) {
            
            System.err.println("Error listening on port " + portNumber);
            
        }
        
    }
    
}
