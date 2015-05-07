package model.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import anima.component.base.ComponentBase;
import model.interfaces.IElement;
import model.interfaces.IInventory;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Inventory extends ComponentBase implements Serializable, IInventory {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(mappedBy="inventory")
    private List<Inventory_pokemonstro> inventory_pokemonstro;
    @OneToMany(mappedBy="inventory")
    private List<Inventory_item> inventory_item;

    public Integer getId() {
        return id;
    }

	@Override
	public IElement getElement(String type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addElement(IElement element, String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeElement(String type, String name) {
		// TODO Auto-generated method stub
		return false;
	}
}
