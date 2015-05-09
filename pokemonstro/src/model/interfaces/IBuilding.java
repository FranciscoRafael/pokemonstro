package model.interfaces;


import model.classes.Building;
import model.classes.City;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Alexandre on 08/05/15.
 */
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
