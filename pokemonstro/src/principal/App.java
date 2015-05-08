package principal;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import anima.context.exception.ContextException;
import anima.factory.exception.FactoryException;
import model.classes.Building;
import model.classes.City;
import model.classes.Player;
import model.classes.Storage;
import model.exceptions.EqualPlayers;
import model.exceptions.NonexistentEntityException;
import model.exceptions.PreexistingEntityException;

public class App {
	public static void main(String args[]) {
		Storage storage=new Storage();
		Player player;
		try {		
			try {
				player=new Player();
				player.setName("mestre");
				storage.savePlayer(player);
			} catch (PreexistingEntityException|ContextException | FactoryException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
							"Erro",JOptionPane.ERROR_MESSAGE);
			}
			/*No inicio do jogo, carregar nome dos players para o usuario escolher*/
			List<String> players = storage.getPossiblePlayers();
			for(String p:players){
				/*pegando um player qualquer do bd pelo nome*/
				try {
					player = (Player) storage.getPlayer(p);
					/*teste para ver se carregou*/
					/*imprime nome do player*/
					System.out.println(player.getName());
					City city=player.getCity();
					/*imprime nome da cidade*/
					System.out.println(city.getName());
					List<Building> build = city.getBuilding();
					/*imprime construcoes*/
					for(Building builds:build){
						System.out.println(builds.getName());
					}
					storage.edit(player);
				} catch (EqualPlayers e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
  							"Erro",JOptionPane.ERROR_MESSAGE);
				}				
			}
		} catch (NonexistentEntityException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					  							"Erro",JOptionPane.ERROR_MESSAGE);
		}catch (PersistenceException e){
			JOptionPane.showMessageDialog(null, "Problemas para conectar com o banco de dados",
										  "Erro",JOptionPane.ERROR_MESSAGE);
		}
	}
}
