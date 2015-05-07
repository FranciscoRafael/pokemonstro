package model.classes;

import java.io.Serializable;
import javax.persistence.*;
import model.interfaces.IPlayer;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class SavedGame implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade=CascadeType.ALL)
    private Player player;

    public IPlayer getPlayer() {
        return player;
    }

    public void setPlayer(IPlayer player) {
        this.player = (Player) player;
    } 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
}
