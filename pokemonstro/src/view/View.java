package view;

import controller.Controller;
import controller.Keyboard;

import javax.swing.*;

import model.classes.Building;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

/**
 * @author Arthur Costa Lopes
 */
public class View extends JPanel {
	
	AffineTransform aff, camera, buildings;
	
	public static final int Height = 320;
	public static final int Width = 480;
	
    public View() {
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.white);
        
        camera = new AffineTransform();
    	camera.setToIdentity();
    	buildings = new AffineTransform();
    	buildings.setToIdentity();
    }

    public void Draw(Graphics2D g2d) {
    	camera.setToTranslation(Controller.player.getX(), Controller.player.getY());
    	
    	g2d.drawImage(Controller.city.getImage(), camera, this);
    	
    	for (Building b : Controller.city.getInternalbuilding()) {
			buildings.setToIdentity();
			buildings.setToTranslation(b.getX(), b.getY());
	    	camera.concatenate(buildings);
	    	g2d.drawImage(b.getImage(), camera, null);

			g2d.drawRect((int)(2*b.getRectangle().getX() - Controller.player.getX() + 225),
					(int)(2*b.getRectangle().getY() - Controller.player.getY() + 125),
					2*(int)b.getRectangle().getWidth(), 2*(int)b.getRectangle().getHeight());

	    	try {
	    		camera.concatenate(buildings.createInverse());
	    	} catch (NoninvertibleTransformException ex) {
	    		ex.printStackTrace();
	    	}
    	}
    	
    	g2d.drawImage(Controller.player.getPlayerImage(), 
    					this.Width/2 - Controller.player.getPlayerImage().getWidth(null)/2,
    					this.Height/2 - Controller.player.getPlayerImage().getHeight(null)/2,
    					null);
    	
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        Draw(g2d);
    }
}