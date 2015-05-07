package model.classes.action;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import model.classes.element.Pokemonstro;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Attack extends Action{
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy="attack")
    private List<Pokemonstro> pokemonstro;
}
