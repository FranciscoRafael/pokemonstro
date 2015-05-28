package principal;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import model.classes.Storage;
import model.exceptions.ControlledException;
import model.interfaces.IAction;
import model.interfaces.IElement;
import model.interfaces.IPlayer;

public class App {
	public static void main(String args[]) {
		Storage storage=new Storage();
		try {	
			IPlayer player;
			for (String p : storage.getPossiblePlayers()) {
				System.out.println("Nome do Player: "+p);
				player=storage.getPlayer(p);
				System.out.println("posicao: ("+player.getX()+","+player.getY()+")");
			}
			System.out.println("\nPokemonstros:");
			IElement[] monstros = storage.getAllElements("pokemonstro");
			for(IElement monstro:monstros){
				System.out.println("\n"+monstro.getName()+", "+monstro.getType());
				for(IAction ataque:monstro.getActions()){
					System.out.println("Ataque: "+ataque.getName()
									   +", Forca: "+ataque.getPower());
				}
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
