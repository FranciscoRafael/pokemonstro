package model.classes.element;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import model.classes.Inventory_item;
import model.classes.action.Effect;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Item extends Element{
	private static final long serialVersionUID = 1L;
	@ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="Item_Effect", 
               joinColumns=  @JoinColumn( name = "idItem"), 
               inverseJoinColumns= @JoinColumn(name = "idEffect") )
    private List<Effect> effect;
    @OneToMany(mappedBy="item")
    private List<Inventory_item> item_inventory;
}
