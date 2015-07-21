/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissorslizardspock;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

/**
 *
 * @author Administrator
 */
public class SendScore implements Runnable {
    
    HashMap<String, String> clientRequest;
    String command;
    Socket clientSocket;
    JSONInputStream inFromClient;
    
    public SendScore(Socket clientSocket) {
        
        this.clientSocket = clientSocket;
        
        try {
            
            inFromClient = new JSONInputStream(clientSocket.getInputStream());
            clientRequest = (HashMap<String, String>) inFromClient.readObject();
            command = clientRequest.get("Command");
            
        }
        
        catch (IOException | JSONException e) {
            
            e.printStackTrace();
            
        }
        
    }
    
    @Override
    public void run() {
        
        Controller controller = new Controller();
        
        controller.handle(command, clientRequest);
        
    }
    
}
