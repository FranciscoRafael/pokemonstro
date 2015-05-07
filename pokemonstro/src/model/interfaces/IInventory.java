package model.interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IInventory>")
public interface IInventory extends ISupports {
	public IElement getElement(String type, String name);
	public boolean addElement(IElement element, String type);
	public boolean removeElement(String type, String name);
}
