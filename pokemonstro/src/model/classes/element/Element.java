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
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	@Override
	public abstract IAction[] getActions();
	@Override
	public abstract void setAction(IAction action);
}