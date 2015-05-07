package model.classes.element;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import anima.component.base.ComponentBase;
import model.classes.Inventory;
import model.interfaces.IAction;
import model.interfaces.IElement;

/**
 *
 * @author Danilo Charantola
 */
@MappedSuperclass
public class Element extends ComponentBase implements Serializable, IElement {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=50)
    private String name;
    @Column(length=20)
    private String type;
    @Column(length=250)
    private String image;
    private int life;
    @ManyToMany(mappedBy="inventory")
    private List<Inventory> inventory;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return life;
	}
	@Override
	public void setLife(int life) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getQty() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setQty(int qty) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public IAction[] getActions() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAction(IAction action) {
		// TODO Auto-generated method stub
		
	}
}