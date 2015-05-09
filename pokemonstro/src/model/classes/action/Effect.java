package model.classes.action;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import anima.annotation.Component;
import model.classes.element.Item;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Effect>",
		   provides = "<pokemonstro.src.model.interfaces.IAction>")
public class Effect extends Action{
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy="effect")
	/*lista de item que possuem esse efeito*/
    private List<Item> item;
}
