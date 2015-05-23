package principal;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import model.classes.Building;
import model.classes.City;
import model.classes.Player;
import model.classes.Storage;
import model.exceptions.ControlledException;

public class App {
	public static void main(String args[]) {
		Storage storage=new Storage();
		Player player;
		try {		
			player = new Player();
			//player.setName("mestre2");
			//storage.savePlayer(player);
			/*No inicio do jogo, carregar nome dos players para o usuario escolher*/
			List<String> players = storage.getPossiblePlayers();
			for(String p:players){
				/*pegando um player qualquer do bd pelo nome*/
				player = (Player) storage.getPlayer(p);
				/*
				System.out.println(player.getName());
				City city=player.getCity();
				
				System.out.println(city.getName());
				List<Building> build = city.getInternalbuilding();
				
				for(Building builds:build){
					System.out.println(builds.getName());
				}*/
				storage.edit(player);				
			}
		}catch (ControlledException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					  					 "Erro",JOptionPane.ERROR_MESSAGE);
		}catch (PersistenceException e){
			JOptionPane.showMessageDialog(null, "Problemas para conectar com o banco de dados",
										 "Erro",JOptionPane.ERROR_MESSAGE);
		}
	}
}
