package model.classes.element;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import anima.annotation.Component;
import model.classes.Inventory_item;
import model.classes.action.Effect;
import model.interfaces.IAction;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Item>",
		   provides = "<pokemonstro.src.model.interfaces.IElement>")
public class Item extends Element{
	private static final long serialVersionUID = 1L;
	@ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="Item_Effect", 
               joinColumns=  @JoinColumn( name = "idItem"), 
               inverseJoinColumns= @JoinColumn(name = "idEffect") )
	/*lista de efeitos do item*/
    private List<Effect> effect;
    @OneToMany(mappedBy="item")
    /*inventarios que possuem esse item*/
    private List<Inventory_item> item_inventory;
    
	@Override
	public IAction[] getActions() {
		/*recupera o(s) efeito(s) do item*/
		return null;
	}
	@Override
	public void setAction(IAction action) {
		/*adiciona um efeito*/		
	}
}
