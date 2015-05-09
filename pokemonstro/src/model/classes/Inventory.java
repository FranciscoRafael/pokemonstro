package model.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import anima.annotation.Component;
import anima.component.base.ComponentBase;
import model.interfaces.IElement;
import model.interfaces.IInventory;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Inventory>",
		   provides ={"<src.model.interfaces.IInventory>"})
public class Inventory extends ComponentBase implements Serializable, IInventory {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(mappedBy="inventory")
    /*lista de pokemonstros nesse inventario
     *permite pegar o pokemonstro e a sua quantidade*/
    private List<Inventory_pokemonstro> inventory_pokemonstro;
    @OneToMany(mappedBy="inventory")
    /*lista de itens nesse inventario
     *permite pegar o item e sua quantidade*/
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
