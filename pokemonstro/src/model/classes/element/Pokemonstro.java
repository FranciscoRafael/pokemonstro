package model.classes.element;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

import model.classes.Inventory_pokemonstro;
import model.classes.action.Attack;
import model.interfaces.IAction;
import java.awt.Image;
import anima.annotation.Component;

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
		return (IAction[]) attack.toArray(new IAction[0]);
	}
	@Override
	public void setAction(IAction action) {
		/*adiciona um ataque*/		
	}
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("/pokemonstroImages/"+ super.getType()+"/"+ super.getName() + ".png")).getImage();
	}
}
