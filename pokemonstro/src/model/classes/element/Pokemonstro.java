package model.classes.element;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import anima.annotation.Component;
import model.classes.Inventory_pokemonstro;
import model.classes.action.Attack;
import model.interfaces.IAction;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Pokemonstro>",
	       provides = "<pokemonstro.src.model.interfaces.IElement>")
public class Pokemonstro extends Element{ 
	private static final long serialVersionUID = 1L;
	@ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="Pokemonstro_Attack", 
               joinColumns=  @JoinColumn( name = "idPokemonstro"), 
               inverseJoinColumns= @JoinColumn(name = "idAttack") )
	/*lista de ataques do pokemonstro*/
    private List<Attack> attack;
    @OneToMany(mappedBy="pokemonstro")
    /*inventarios que possuem esse pokemonstro*/
    private List<Inventory_pokemonstro> pokemonstro_inventory;
    @Override
	public IAction[] getActions() {
		/*recupera o(s) ataque(s) do item*/
		return null;
	}
	@Override
	public void setAction(IAction action) {
		/*adiciona um ataque*/		
	}
}
