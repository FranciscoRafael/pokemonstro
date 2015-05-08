package model.interfaces;

import javax.persistence.PersistenceException;

import model.classes.Player;
import model.exceptions.EqualPlayers;
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
	public void edit(Player player) throws NonexistentEntityException, PersistenceException;
	public void destroy(Integer id) throws NonexistentEntityException;
	public IPlayer getPlayer(String name) throws NonexistentEntityException, EqualPlayers;
	public void savePlayer(IPlayer player) throws PreexistingEntityException, PersistenceException ;
	public IElement[] getAllElements(String type);
	public boolean possibleName(String name);
}
