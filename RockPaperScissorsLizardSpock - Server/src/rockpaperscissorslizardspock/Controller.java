package rockpaperscissorslizardspock;

import java.util.HashMap;

/**
 *
 * @author Architect
 */
public class Controller {
    
    private HashMap<String, Handler> controlHash = new HashMap<>();
    
    public Controller() {
        
        RecordScoreHandler recordScoreHandler = new RecordScoreHandler();
        controlHash.put("Record High Score", recordScoreHandler);
        
        ViewHighScoresHandler viewHighScoresHandler = new ViewHighScoresHandler();
        controlHash.put("View High Scores", viewHighScoresHandler);
        
    }
    
    public void handle(String command, Object... objects) {
        
        Handler task = controlHash.get(command);
        
        task.handleIt(objects);
        
    }
    
}
