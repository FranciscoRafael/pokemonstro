package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private static boolean[] keyboardState = new boolean[525];
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboardState[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public static boolean getKeyboardState(int index) {
		return keyboardState[index];
	}
	
}
