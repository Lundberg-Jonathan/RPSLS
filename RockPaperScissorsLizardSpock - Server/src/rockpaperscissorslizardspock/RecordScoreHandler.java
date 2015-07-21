package rockpaperscissorslizardspock;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Architect
 */
public class RecordScoreHandler implements Handler {
    
    @Override
    public void handleIt(Object... objects) {
        
        HashMap<String, String> clientRequest = (HashMap<String, String>) objects[0];
        Score score = new Score();
        Session session = ConnectDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        String playerName = clientRequest.get("Username");
        Integer playerScore = Integer.parseInt(clientRequest.get("Score").toString());
        
        score.setPlayerName(playerName);
        score.setScore(playerScore);
        
        session.save(score);
        
        transaction.commit();
        
        System.out.println("Score " + playerScore + " recorded for user " + playerName);
        
    }
    
}
