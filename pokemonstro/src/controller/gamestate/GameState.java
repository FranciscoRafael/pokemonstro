package controller.gamestate;

public class GameState {
	private State state;
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public GameState() {
		state = null;
	}
}
