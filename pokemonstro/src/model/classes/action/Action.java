package model.classes.action;

import java.io.Serializable;

import javax.persistence.*;

import model.interfaces.IAction;
import anima.component.base.ComponentBase;

/**
 *
 * @author Danilo Charantola
 */
@MappedSuperclass
public class Action extends ComponentBase implements Serializable, IAction{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=50)
    private String name;
    private int power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

	@Override
	public void use() {
		// TODO Auto-generated method stub		
	}    
}
