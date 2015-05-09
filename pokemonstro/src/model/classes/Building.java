package model.classes;

import model.interfaces.IBuilding;
import model.interfaces.IConstruction;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;

@Entity
public class Building implements Serializable, IConstruction, IBuilding{
	private static final long serialVersionUID = 1L;
	 @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	private City city;
	@Column(length=50)
	private String name;
	private int x;
	private int y;
	private String image;
	@OneToMany(mappedBy="ExternalBuilding")
	private List<Building> Internalbuilding;
	@ManyToOne(cascade=CascadeType.ALL)
	private Building ExternalBuilding;
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public List<Building> getInternalbuilding() {
		return Internalbuilding;
	}
	public void setInternalbuilding(List<Building> internalbuilding) {
		Internalbuilding = internalbuilding;
	}
	public Image getImage() {
		return new ImageIcon(getClass().getResource("/Images/"+ image + ".png")).getImage();
	}
	public Building getExternalBuilding() {
		return ExternalBuilding;
	}
	public void setExternalBuilding(Building externalBuilding) {
		ExternalBuilding = externalBuilding;
	}
	public Rectangle getRectangle() {
		return new Rectangle(x, y, this.getImage().getWidth(null), this.getImage().getHeight(null));
	}
}