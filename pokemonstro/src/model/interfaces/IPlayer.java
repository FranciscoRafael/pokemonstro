package model.interfaces;

import model.exceptions.PreexistingEntityException;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;
import anima.context.exception.ContextException;
import anima.factory.exception.FactoryException;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IPlayer>")
public interface IPlayer extends ISupports{
	public String getName();
	public void setName(String name) throws PreexistingEntityException, 
											ContextException, FactoryException;
	public String getImage();
	public void setImage(String path);
	public String getPosition();
	public void setPosition(String position);
	public IInventory getInventory();
}
