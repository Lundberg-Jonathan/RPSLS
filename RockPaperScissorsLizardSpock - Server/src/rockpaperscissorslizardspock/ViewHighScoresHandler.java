/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissorslizardspock;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

/**
 *
 * @author Architect
 */
public class ViewHighScoresHandler implements Handler {
    
    private List<Score> scores;
    
    @Override
    public void handleIt(Object... objects) {
        
        HashMap<String, String> clientRequest = (HashMap<String, String>) objects[0];
        Socket clientSocket = (Socket) objects[1];
        JSONOutputStream outToClient;
        Score score = new Score();
        Session session = ConnectDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query topScoresQuery = session.createQuery("select score from Score score order by score.score desc");
        ArrayList<String> highScores = new ArrayList<>();
        
        scores = topScoresQuery.list();
        
        Iterator<Score> scoreList = scores.iterator();
        
        while (scoreList.hasNext()) {
            
            Score element = scoreList.next();
            highScores.add(String.format("%-10s%-10s", element.getPlayerName(), element.getScore()));
            
        }
        
        /*for (int i=0; i < highScores.size(); i++) {
            
            System.out.println(highScores.get(i));
            
        }*/
        
        try {
            
            outToClient = new JSONOutputStream(clientSocket.getOutputStream());
            
            outToClient.writeObject(highScores);
            
        }
        
        catch (IOException | JSONException e) {
            
            System.err.println("Error sending data to client");
            e.printStackTrace();
            
        }
        
        transaction.commit();
        
        System.out.println("Success");
        
    }
    
}
