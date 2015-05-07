package model.interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IAction>")
public interface IAction extends ISupports{
	public String getName();
	public void setName(String Name);
	public int getPower();
	public void setPower(int power);
	public void use();
}
