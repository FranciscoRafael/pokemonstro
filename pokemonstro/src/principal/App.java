package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import model.classes.Building;
import model.classes.City;
import model.classes.Player;
import model.classes.Storage;
import model.classes.element.Pokemonstro;
import model.exceptions.ControlledException;
import model.interfaces.IElement;

public class App {
	public static void main(String args[]) {
		Storage storage=new Storage();
		try {		
			/*Scanner teclado = new Scanner(System.in);
			String linha="";
			while(!linha.equalsIgnoreCase("fim")){
				linha=teclado.nextLine();
				if(!linha.equalsIgnoreCase("fim")){
					System.out.println(linha);
					Pokemonstro monstro = new Pokemonstro();
					monstro.setName(linha);
					monstro.setLife(100);
					linha=teclado.nextLine();
					monstro.setType(linha);
					storage.savePlayer(monstro);
				}
			}
			teclado.close();*/
			
			for (String p : storage.getPossiblePlayers()) {
				System.out.println(p);
			}
			//IElement[] monstros = storage.getAllElements("pokemonstro");
			//for(IElement monstro:monstros){
			//	System.out.println(monstro.getName());
			//}
		}/*catch (ControlledException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					  					 "Erro",JOptionPane.ERROR_MESSAGE);
		}*/catch (PersistenceException e){
			JOptionPane.showMessageDialog(null, "Problemas para conectar com o banco de dados",
										 "Erro",JOptionPane.ERROR_MESSAGE);
		}
	}
}
