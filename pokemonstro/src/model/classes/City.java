package model.classes;

import model.interfaces.IConstruction;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

import anima.annotation.Component;

/**
 *
 * @author Danilo Charantola
 */
@Entity
@Component(id = "<src.model.classes.City>",
		   provides ={"<src.model.interfaces.IConstruction>"})
public class City implements Serializable, IConstruction{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column(nullable=false)
    private String name;
    @OneToMany(mappedBy="city")
    /*construcoes da cidade*/
    List<Building> building;

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    @Override
    public List<Building> getInternalbuilding() {
        return building;
    }

    @Override
    public void setInternalbuilding(List<Building> internalbuilding) {
        building = internalbuilding;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return new ImageIcon(getClass().getResource("/Images/"+ name + ".png")).getImage();
    }
    
}