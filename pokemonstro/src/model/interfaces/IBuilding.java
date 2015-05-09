package model.interfaces;


import anima.annotation.ComponentInterface;
import model.classes.Building;
import model.classes.City;

/**
 * Created by Alexandre on 08/05/15.
 */
@ComponentInterface("<pokemonstro.src.model.interfaces.IBuilding>")
public interface IBuilding {
    public City getCity();
    public void setCity(City city);
    public Building getExternalBuilding();
    public void setExternalBuilding(Building externalBuilding);
    public int getX();
    public void setX(int x);
    public int getY();
    public void setY(int y);

}
