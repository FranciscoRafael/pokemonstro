package model.classes.action;

import java.io.Serializable;

import javax.persistence.*;

import model.interfaces.IAction;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 *
 * @author Danilo Charantola
 */
@MappedSuperclass
@Component(id = "<src.model.classes.Action>",
		   provides ={"<src.model.interfaces.IAction>"})
public class Action extends ComponentBase implements Serializable, IAction{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=50, nullable=false)
    private String name;
    /* para o efeito, indica, por exemplo, em quanto a vida sera aumentada
     * para o ataque, indica a sua forca*/
	@Column(nullable=false)
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
		/*utiliza o item ou realiza o ataque*/		
	}    
}
