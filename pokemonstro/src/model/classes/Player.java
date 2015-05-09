package model.classes;

import java.awt.*;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.swing.*;

import model.exceptions.PreexistingEntityException;
import model.interfaces.IInventory;
import model.interfaces.IPlayer;
import model.interfaces.IStorage;
import anima.annotation.Component;
import anima.component.base.ComponentBase;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public Image getPlayerImage() {
        return new ImageIcon(getClass().getResource("/Images/"+ image + ".png")).getImage();
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

    public void setName(String name) throws PreexistingEntityException, ContextException, FactoryException{
    	IGlobalFactory factory;
		factory = ComponentContextFactory.createGlobalFactory();
    	factory.registerPrototype(Storage.class);
    	IStorage storage=factory.createInstance("<pokemonstro.src.model.classes.Storage>");
    	if(storage.possibleName(name)){
    		this.name = name;
    	}else{
    		throw new PreexistingEntityException("Algum frango ja escolheu esse nome.\n"
    							   			     + "Por favor, escolha outro.", null);
    	}
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }    
}
