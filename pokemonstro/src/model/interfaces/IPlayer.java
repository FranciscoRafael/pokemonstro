package model.interfaces;

import java.awt.Image;

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
	public String getPosition();
	public void setPosition(String position);
	public IInventory getInventory();
    public Image getPlayerImage();
    public Integer getId();
    public void setDirection(String direction);
    public String getDirection();
}
