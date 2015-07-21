package rockpaperscissorslizardspock;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 *
 * @author Architect
 */

@Entity
@Table(name = "score")
public class Score {
    
    private Integer score_id;
    private String player_name;
    private Integer score;
    
    public Score() {
        
        
        
    }
    
    @Id
    @GeneratedValue
    @Column(name = "score_id")
    public Integer getScoreID() {
        
        return score_id;
        
    }
    
    public void setScoreID(Integer score_id) {
        
        this.score_id = score_id;
        
    }
    
    @Column(name = "player_name")
    public String getPlayerName() {
        
        return player_name;
        
    }
    
    public void setPlayerName(String player_name) {
        
        this.player_name = player_name;
        
    }
    
    @Column(name = "score")
    public Integer getScore() {
        
        return score;
        
    }
    
    public void setScore(Integer score) {
        
        this.score = score;
        
    }
    
}
