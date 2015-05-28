package model.interfaces;

import java.awt.Image;
import java.awt.Rectangle;

import model.classes.City;
import model.exceptions.ControlledException;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IPlayer>")
public interface IPlayer extends ISupports{
	public String getName();
	public void setName(String name) throws ControlledException;
	public IInventory getInventory();
    public Image getPlayerImage();
    public Integer getId();
    public void setDirection(String direction);
    public String getDirection();
    
    public City getCity();
    public void setCity(City city);
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Rectangle getRectangle();
}
