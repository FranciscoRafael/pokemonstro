package model.classes.action;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import model.classes.element.Item;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Effect extends Action{
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy="effect")
    private List<Item> item;
}
