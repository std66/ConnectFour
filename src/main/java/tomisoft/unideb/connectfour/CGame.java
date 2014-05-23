/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game class for Connect Four. Handles the board and the players.
 * 
 * @author Sinku Tamás
 */
public class CGame {
	/**
	 * The current player.
	 */
    private Player CurrentPlayer;
	
	/**
	 * The game board.
	 */
    private Board board;
	
	/**
	 * Logger class for logging events.
	 */
	private Logger logger;
	
	/**
	 * How many times has Player A moved.
	 */
	private int PlayerAMoves;
	
	/**
	 * How many times has Player B moved.
	 */
	private int PlayerBMoves;
	
	/**
	 * Constructs a new game class.
	 * 
	 * @param l a logger for logging events
	 */
	public CGame(Logger l) {
		this.logger = l;
		this.PlayerAMoves = 0;
		this.PlayerBMoves = 0;
	}

	/**
	 * Gets the current player. See {@code Player} for more details.
	 * 
	 * @return the current player
	 */
    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }

	/**
	 * Gets the game board. See {@code Board} for more details.
	 * 
	 * @return the game board
	 */
    public Board getBoard() {
        return board;
    }

	/**
	 * Gets that how many times has the Player A moved.
	 * 
	 * @return the number of moves
	 */
	public int getPlayerAMoves() {
		return PlayerAMoves;
	}

	/**
	 * Gets that how many times has the Player B moved.
	 * 
	 * @return the number of moves
	 */
	public int getPlayerBMoves() {
		return PlayerBMoves;
	}
    
	/**
	 * Starts a new game.
	 */
    public void NewGame() {
        Random r = new Random();
        
        this.CurrentPlayer = (r.nextBoolean()) ? Player.A : Player.B;
		this.logger.log(Level.INFO, "Starting a new game, starting player is: " + this.CurrentPlayer.name());
        this.board = new Board(5, 5);
		this.PlayerAMoves = 0;
		this.PlayerBMoves = 0;
    }
    
	/**
	 * Places a marker on the board for the current player at the given column.
	 * 
	 * @param Col the column index
	 * @return true if moving was successful
	 */
    public boolean Move(int Col) {
        try {
            this.board.Put(CurrentPlayer, Col);
			
			this.logger.log(Level.INFO, "Placed successfully by player " + this.CurrentPlayer.name());
			
			if (this.CurrentPlayer == Player.A) {
				this.PlayerAMoves++;
				this.CurrentPlayer = Player.B;
			}
			else {
				this.PlayerBMoves++;
				this.CurrentPlayer = Player.A;
			}
			
			return true;
        }
        catch (BoardColumnFullException ex) {
			this.logger.log(Level.WARNING, "Could not place player's move - column is full");
            return false;
        } catch (BoardIllegalColumnException ex) {
			this.logger.log(Level.WARNING, "Could not place player's move - illegal column");
            return false;
        }
    }
}
