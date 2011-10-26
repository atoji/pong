package com.beyondweb.pong;

public class ScoreBoard {

	private final Player player1;
	private final Player player2;

	public ScoreBoard(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void playerOneGoal() {
		player1.increaseScore();
	}
	
	public void playerTwoGoal() {
		player2.increaseScore();
	}
}
