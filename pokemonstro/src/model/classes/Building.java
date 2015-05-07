package model.classes;

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

@Entity
public class Building implements Serializable{
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Building> getInternalbuilding() {
		return Internalbuilding;
	}
	public void setInternalbuilding(List<Building> internalbuilding) {
		Internalbuilding = internalbuilding;
	}
	public Building getExternalBuilding() {
		return ExternalBuilding;
	}
	public void setExternalBuilding(Building externalBuilding) {
		ExternalBuilding = externalBuilding;
	}
}