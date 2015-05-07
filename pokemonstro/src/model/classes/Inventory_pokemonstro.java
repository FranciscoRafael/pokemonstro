package model.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import model.classes.element.Pokemonstro;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class Inventory_pokemonstro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer qty;
    @ManyToOne(cascade=CascadeType.ALL)
    private Inventory inventory;
    @ManyToOne(cascade=CascadeType.ALL)
    private Pokemonstro pokemonstro;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Pokemonstro getPokemonstro() {
        return pokemonstro;
    }

    public void setPokemonstro(Pokemonstro pokemonstro) {
        this.pokemonstro = pokemonstro;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
