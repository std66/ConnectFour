/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

/**
 * Exception class that occures when the given column index is out of range when the player wants to move. See <code>Board</code>.
 * 
 * @author Sinku Tamás
 */
public class BoardIllegalColumnException extends Exception {

	/**
	 * The game board's column index.
	 */
    private int Column;

	/**
	 * Gets the game board's column index.
	 * 
	 * @return the game board's column index.
	 */
    public int getColumn() {
        return Column;
    }

    /**
	 * Constructs an instance of <code>BoardIllegalColumnException</code> with the
	 * specified index of the game board.
	 * 
	 * @param Column the column index of the board
	 */
    public BoardIllegalColumnException(int Column) {
        super("Illegal column");
    }
}
