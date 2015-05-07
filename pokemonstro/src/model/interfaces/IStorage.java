package model.interfaces;

import model.classes.Player;
import model.exceptions.NonexistentEntityException;
import model.exceptions.PreexistingEntityException;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IStorage>")
public interface IStorage extends ISupports {
	public void edit(Player player) throws NonexistentEntityException, Exception;
	public void destroy(Integer id) throws NonexistentEntityException;
	public IPlayer getPlayer(int id) throws NonexistentEntityException;
	public void savePlayer(IPlayer player) throws PreexistingEntityException, Exception ;
	public IElement[] getAllElements(String type);
}
