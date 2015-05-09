package model.classes.action;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import anima.annotation.Component;
import model.classes.element.Pokemonstro;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.Attack>",
		   provides = "<pokemonstro.src.model.interfaces.IAction>")
public class Attack extends Action{
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy="attack")
	/*lista de pokemonstros que possuem esse ataque*/
    private List<Pokemonstro> pokemonstro;
}
