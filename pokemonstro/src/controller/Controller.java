package controller;

import view.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import org.eclipse.persistence.internal.libraries.antlr.runtime.RecognitionException;

import model.classes.*;
import model.interfaces.*;
import model.exceptions.*;
import controller.gamestate.GameState;
import controller.gamestate.LoadingState;
import controller.gamestate.MenuState;
import controller.gamestate.MovingState;
import controller.gamestate.State;

/**
 * @author Arthur Costa Lopes
 */
public class Controller {

	//States
    public static GameState gameState = new GameState();
    private State movingState;
    private State loadingState;
    private State menuState;
    
    //Movement Collision
    private boolean ableMoveNorth = true;
    private boolean ableMoveSouth = true;
    private boolean ableMoveWest  = true;
    private boolean ableMoveEast  = true;
    
    //Movement
    private boolean isMoving = false;
    private long movTime;
    
    //Time Loop
    private long gameTime;
    private long lastTime;
    public static final long secInNanosec     = 1000000000L;
    public static final long milisecInNanosec = 1000000L;
    private final int GAME_FPS = 30;
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;

    //GUI
	private View gui;
    
    //models
    public static IPlayer player;
    public static IConstruction city;
    private Storage storage;
    
    
    
    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        Controller.gameState = gameState;
    }

    public Controller(View view) {
        super();
        this.gui = view;
        
        Thread gameThread = new Thread() {
            @Override
            public void run() {
            	Initialize();
                LoadContent();
                
                gameState.setState(movingState);
                GameLoop();
            }
        };
        gameThread.start();
    }

    private void LoadContent() {
    	try {
    		player = storage.getPlayer("Player");
    		System.out.println(player.getName());
    		
    		city = player.getCity();
    		System.out.println(city.getName());
			
		}catch (ControlledException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					  					 "Erro",JOptionPane.ERROR_MESSAGE);
		}catch (PersistenceException e){
			JOptionPane.showMessageDialog(null, "Problemas para conectar com o banco de dados"+e.getMessage(),
										 "Erro",JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void Initialize() {
    	storage = new Storage();
    	this.loadingState = new LoadingState();
    	this.movingState  = new MovingState();
    	this.menuState    = new MenuState();
    	movTime = 0;
    	isMoving = false;
    }
    
    public void Collision() throws ControlledException {
    	boolean collided = false;
    	Rectangle collisionRectangle = null;
    	
    	Rectangle leftRectangle  = new Rectangle(player.getX() - 10, player.getY(), 10, 55);
		Rectangle rightRectangle = new Rectangle(player.getX() + 100, player.getY(), 10, 55);
		Rectangle upRectangle    = new Rectangle(player.getX(), player.getY() - 20,  20, 10);
		Rectangle downRectangle  = new Rectangle(player.getX(), player.getY() + 130, 20, 10);
    	
    	for (Building b : city.getInternalbuilding()) {
    		if (leftRectangle.intersects(b.getRectangle()) || rightRectangle.intersects(b.getRectangle()) || 
    			  upRectangle.intersects(b.getRectangle()) || downRectangle.intersects(b.getRectangle())) {
	    		System.out.println("Colidiu com " + b.getName());
	    		if (b.getName().substring(0, 2).equals("to")) {
	    			//player.setCity(storage.getCity("GymInside"));
	    		}
	    		collided = true;
	    		collisionRectangle = new Rectangle(b.getRectangle());
	    	}
    	}
    	
    	
    	ableMoveEast  = true;
		ableMoveNorth = true;
		ableMoveSouth = true;
		ableMoveWest  = true;
		
		
		if (collided) {	
    		
    		if (leftRectangle.intersects(collisionRectangle)) {
				ableMoveWest = false;
			}
    		
    		if (rightRectangle.intersects(collisionRectangle)) {
				ableMoveEast = false;
			}
    		
    		
    		if (downRectangle.intersects(collisionRectangle)) {
				ableMoveSouth = false;
			}
    		
    		if (upRectangle.intersects(collisionRectangle)) {
				ableMoveNorth = false;
    		}
		}
	}
    
    public void UpdateMoving() {
    	
        if (Keyboard.getKeyboardState(KeyEvent.VK_DOWN)) {
        	if (ableMoveSouth) {
        		if (isMoving == false) {
	        		player.setY(player.getY() + 75);
	        		player.setDirection("sul");
	        		movTime = System.currentTimeMillis();
	        		isMoving = true;
        		}
        	}
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_UP)) {
        	if (ableMoveNorth) {
        		if (isMoving == false) {
		        	player.setY(player.getY() - 75);
		        	player.setDirection("norte");
		        	isMoving = true;
		        	movTime = System.currentTimeMillis();
        		}
        	}
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_LEFT)) {
        	if (ableMoveWest) {
        		if (isMoving == false) {
		        	player.setX(player.getX() - 55);
		        	player.setDirection("oeste");
		        	isMoving = true;
		        	movTime = System.currentTimeMillis();
        		}
        	}
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_RIGHT)) {
        	if (ableMoveEast) {
        		if (isMoving == false) {
		        	player.setX(player.getX() + 55);
		        	player.setDirection("leste");
		        	isMoving = true;
		        	movTime = System.currentTimeMillis();
        		}
        	}
        }
        
    }

    public void GameLoop() {
        // This variables are used for calculating the time that defines for how
        // long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;

        while(true) {
            beginTime = System.nanoTime();
            if (gameState.getState().getStateString().equals("MOVING")) {
                    gameTime += System.nanoTime() - lastTime;
                    
                    try {
                    	Collision();
                    } catch (ControlledException ex) {
						ex.printStackTrace();
					}
                    
                    UpdateMoving();
                    if (isMoving) {
                    	if (System.currentTimeMillis() - movTime > 100) {
                    		isMoving = false;
                    	}
                    }
                    
                    lastTime = System.nanoTime();
            } else if (gameState.getState().getStateString().equals("LOADING")) {
                    Initialize();
                    LoadContent();
                    gameState.setState(movingState);
                    break;
            }
            // Repaint the screen.
            gui.repaint();

            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for
            // 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10)
                timeLeft = 10; //set a minimum
            try {
                //Provides the necessary delay and also yields control so that other thread can do work.
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) {
            	
            }
        }
    }
}