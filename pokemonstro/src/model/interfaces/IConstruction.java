package model.interfaces;

import model.classes.Building;

import java.awt.*;
import java.util.List;

import anima.annotation.ComponentInterface;

/**
 * Created by Alexandre on 08/05/15.
 */
@ComponentInterface("<pokemonstro.src.model.interfaces.IConstruction>")
public interface IConstruction {
    public String getName();
    public void setName(String name);
    public List<Building> getInternalbuilding();
    public void setInternalbuilding(List<Building> internalbuilding);
    public Image getImage();
}
