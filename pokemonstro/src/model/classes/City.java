package model.classes;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

/**
 *
 * @author Danilo Charantola
 */
@Entity
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(mappedBy="city")
    List<Building> building;

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

	public List<Building> getBuilding() {
		return building;
	}

	public void setBuilding(List<Building> building) {
		this.building = building;
	}

    public Image getImage() {
        return new ImageIcon(getClass().getResource("/Images/"+ name + ".png")).getImage();
    }
    
}