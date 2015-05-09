package model.interfaces;

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
	public String getImage();
	public void setImage(String path);
	public String getPosition();
	public void setPosition(String position);
	public IInventory getInventory();
}
