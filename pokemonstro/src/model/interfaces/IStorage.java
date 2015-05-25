package model.interfaces;

import model.classes.Player;
import model.exceptions.ControlledException;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
*
* @author Danilo Charantola
*/

@ComponentInterface("<pokemonstro.src.model.interfaces.IStorage>")
public interface IStorage extends ISupports {
	/*Salva player ja existente no bd*/
	public void edit(IPlayer player) throws ControlledException;
	/*remove player do bd*/
	public void destroy(Integer id) throws ControlledException; 
	 /*recupera um player do bd*/
	public IPlayer getPlayer(String name) throws ControlledException;
	/*cria novo player e salva*/
	public void savePlayer(Object player) throws ControlledException; 
	/*retorna todos os elementos de certo tipo*/
	public IElement[] getAllElements(String type); 
	/*verifica se um player pode escolher certo nome*/
	public boolean possibleName(String name); 
}
