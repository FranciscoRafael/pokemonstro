package model.interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IElement>")
public interface IElement  extends ISupports{
	public String getName();
	public void setName(String name);
	public String getType();
	public void setType(String type);
	public int getLife();
	public void setLife(int life);
	//public Image getImage();
	//public void setImage(Image image);
	public IAction[] getActions();
	public void setAction (IAction action);
}
