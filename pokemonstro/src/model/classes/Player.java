package model.classes;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import model.interfaces.IInventory;
import model.interfaces.IPlayer;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Player>",
           provides ={"<src.model.interfaces.IPlayer>"})
public class Player extends ComponentBase implements Serializable, IPlayer {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=50)
    private String name;
    @Column(length=250)
    private String image;
    @Column(length=20)
    private String position;
    @OneToOne(cascade= CascadeType.ALL)
    private Inventory inventory;
    @OneToOne(cascade=CascadeType.ALL)
    private City city;
    
    public void setCity(City city){
    	this.city=city;
    }
    public City getCity(){
    	return city;
    }
    public IInventory getInventory() {
        return inventory;
    }

    public void setInventory(IInventory inventory) {
        this.inventory = (Inventory) inventory;
    }    
    
    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }    
}
