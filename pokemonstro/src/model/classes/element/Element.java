package model.classes.element;

import java.awt.Image;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import model.interfaces.IAction;
import model.interfaces.IElement;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 *
 * @author Danilo Charantola
 */
@MappedSuperclass
@Component(id = "<src.model.classes.Element>",
		   provides ={"<src.model.interfaces.IElement>"})
public abstract class Element extends ComponentBase implements Serializable, IElement {
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
    /*duracao do efeito do item ou vida do pokemonstro*/
    private int life;
	@Override
	public String getName() {
		return this.name;
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
	public abstract IAction[] getActions();
	@Override
	public abstract void setAction(IAction action);
}