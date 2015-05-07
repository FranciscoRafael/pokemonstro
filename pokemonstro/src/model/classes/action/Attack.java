package model.classes.action;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import model.classes.Inventory;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Attack extends Action{
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy="pokemonstro")
    private List<Inventory> pokemonstro;
}
