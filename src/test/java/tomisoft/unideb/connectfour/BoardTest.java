/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sinku Tamás
 */
public class BoardTest {

	/**
	 * Test of getCols method, of class Board.
	 */
	@org.junit.Test
	public void testGetCols() {
		Board b = new Board(5, 5);
		assertEquals(5, b.getCols());
	}

	/**
	 * Test of getRows method, of class Board.
	 */
	@org.junit.Test
	public void testGetRows() {
		Board b = new Board(5, 5);
		assertEquals(5, b.getRows());
	}

	/**
	 * Test of getFieldState method, of class Board.
	 */
	@org.junit.Test
	public void testGetFieldState() throws Exception {
		Board b = new Board(5, 5);
		b.Put(Player.A, 3);
		assertEquals(Player.A, b.getFieldState(4, 3));
		b.Put(Player.B, 3);
		assertEquals(Player.B, b.getFieldState(3, 3));
	}

	/**
	 * Test of Put method, of class Board.
	 */
	@org.junit.Test
	public void testPut() throws Exception {
		Board b = new Board(5, 5);
		b.Put(Player.A, 3);
		assertEquals(Player.A, b.getFieldState(4, 3));
		b.Put(Player.B, 3);
		assertEquals(Player.B, b.getFieldState(3, 3));
		
		try {
			b.Put(Player.A, -1);
			fail("Expected BoardIllegalColumnException");
		}
		catch (BoardIllegalColumnException e) {
		
		}
		
		try {
			b.Put(Player.A, 6);
			fail("Expected BoardIllegalColumnException");
		}
		catch (BoardIllegalColumnException e) {}
		
		try {
			b.Put(Player.A, 1);
			b.Put(Player.A, 1);
			b.Put(Player.A, 1);
			b.Put(Player.A, 1);
			b.Put(Player.A, 1);
			b.Put(Player.A, 1);
			fail("Expected BoardColumnFullException");
		}
		
		catch (BoardColumnFullException e) {}
	}

	/**
	 * Test of IsFull method, of class Board.
	 */
	@org.junit.Test
	public void testIsFull() throws Exception {
		Board b = new Board(5, 5);
		assertFalse(b.IsFull());
		
		b.Put(Player.A, 3);
		assertFalse(b.IsFull());
		
		b = new Board(5, 5);
		
		for (int Row = 0; Row < 5; Row++) {
			for (int Col = 0; Col < 5; Col++) {
				b.Put(Player.A, Col);
			}
		}
		
		assertTrue(b.IsFull());
	}

	/**
	 * Test of GetWinnerPlayer method, of class Board.
	 */
	@org.junit.Test
	public void testGetWinnerPlayer() throws Exception {
		Board b = new Board(5, 5);
		
		assertEquals(Player.NONE, b.GetWinnerPlayer());
		
		//===================================================================
		
		b = new Board(5, 5);
		
		/*
			_ _ _ _ _
			_ _ _ _ _
			_ _ _ _ _
			_ _ _ _ _
			A A A A _
		*/
		
		for (int Col = 0; Col < 4; Col++) {
			b.Put(Player.A, Col);
		}
		
		assertEquals(Player.A, b.GetWinnerPlayer());
		
		//===================================================================
		
		b = new Board(5, 5);
		
		/*
			_ _ _ _ _
			_ _ A _ _
			_ _ A _ _
			_ _ A _ _
			_ _ A _ _
		*/
		
		for (int Row = 0; Row < 4; Row++) {
			b.Put(Player.A, 2);
		}
		
		assertEquals(Player.A, b.GetWinnerPlayer());
		
		//===================================================================
		
		b = new Board(5, 5);
		
		/*
			_ _ _ _ A
			_ _ _ A B
			_ _ A A B
			_ A B B A
			B B A A B
		*/
		
		//Fill up first line
		b.Put(Player.B, 0);
		b.Put(Player.B, 1);
		b.Put(Player.A, 2);
		b.Put(Player.A, 3);
		b.Put(Player.B, 4);
		
		//Fill up second line
		b.Put(Player.A, 1);
		b.Put(Player.B, 2);
		b.Put(Player.B, 3);
		b.Put(Player.A, 4);
		
		//Fill up third line
		b.Put(Player.A, 2);
		b.Put(Player.A, 3);
		b.Put(Player.B, 4);
		
		//Fill up fourth line
		b.Put(Player.A, 3);
		b.Put(Player.B, 4);
		
		//Fill up fifth line
		b.Put(Player.A, 4);
		
		assertEquals(Player.A, b.GetWinnerPlayer());
		
		//===================================================================
		
		b = new Board(5, 5);
		
		/*
			_ _ _ _ _
			_ A _ _ _
			_ B A _ _
			_ B A A _
			_ A B A A
		*/
		
		//Fill up fifth col
		b.Put(Player.A, 4);
		
		//Fill up fourth col
		b.Put(Player.A, 3);
		b.Put(Player.A, 3);
		
		//Fill up third col
		b.Put(Player.B, 2);
		b.Put(Player.A, 2);
		b.Put(Player.A, 2);
		
		//Fill up second col
		b.Put(Player.A, 1);
		b.Put(Player.B, 1);
		b.Put(Player.B, 1);
		b.Put(Player.A, 1);
		
		assertEquals(Player.A, b.GetWinnerPlayer());
	}
	
}
