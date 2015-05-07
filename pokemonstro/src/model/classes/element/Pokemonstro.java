package model.classes.element;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import model.classes.Inventory_pokemonstro;
import model.classes.action.Attack;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Pokemonstro extends Element{ 
	private static final long serialVersionUID = 1L;
	@ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="Pokemonstro_Attack", 
               joinColumns=  @JoinColumn( name = "idPokemonstro"), 
               inverseJoinColumns= @JoinColumn(name = "idAttack") )
    private List<Attack> attack;
    @OneToMany(mappedBy="pokemonstro")
    private List<Inventory_pokemonstro> pokemonstro_inventory;
}
