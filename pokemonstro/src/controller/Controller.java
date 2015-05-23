package controller;

import view.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

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

    public static GameState gameState = new GameState();
    private State movingState;
    private State loadingState;
    private State menuState;

    /*-------------------------------------------------------------------*/
    private long gameTime;
    private long lastTime;
    public static final long secInNanosec     = 1000000000L;
    public static final long milisecInNanosec = 1000000L;
    private final int GAME_FPS = 60;
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    /*-------------------------------------------------------------------*/

	private View gui;
    
    //models
    public static IPlayer player;
    public static City city;
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
			player.setName("mestre");
    		player.setX(0);
    		player.setY(0);
    		player.setName("player");
    		player.setDirection("");
    		
			List<String> players = storage.getPossiblePlayers();
			for (String p : players) {
				player = (IPlayer) storage.getPlayer(p);
				System.out.println(player.getName());
				
				
				city = player.getCity();
				//System.out.println(city.getName());
				//List<Building> build = city.getInternalbuilding();
				//for(Building builds:build)
					//System.out.println(builds.getName());*/
				storage.edit(player);
			}
		}catch (ControlledException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					  					 "Erro",JOptionPane.ERROR_MESSAGE);
		}catch (PersistenceException e){
			JOptionPane.showMessageDialog(null, "Problemas para conectar com o banco de dados",
										 "Erro",JOptionPane.ERROR_MESSAGE);
		}
    }

    private void Initialize() {
    	//player  = new Player();
    	city    = new City();
    	storage = new Storage();
    	this.loadingState = new LoadingState();
    	this.movingState  = new MovingState();
    	this.menuState    = new MenuState();
    }

    public void UpdateMoving() {
    	
        if (Keyboard.getKeyboardState(KeyEvent.VK_DOWN)) {
        	player.setY(player.getY() + 10);
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_UP)) {
        	player.setY(player.getY() - 10);
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_LEFT)) {
        	player.setX(player.getX() - 10);
        } else if (Keyboard.getKeyboardState(KeyEvent.VK_RIGHT)) {
        	player.setX(player.getX() + 10);
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
                    
                    UpdateMoving();
                    
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