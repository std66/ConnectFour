/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

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
public class CGameTest {
	
	public CGameTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getCurrentPlayer method, of class CGame.
	 */
	@Test
	public void testGetCurrentPlayer() {
		CGame game = new CGame(Logger.getLogger("Test Logger"));
		game.NewGame();
		
		Player Starter = game.getCurrentPlayer();
		
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		
		if (Starter == Player.A) {
			assertEquals(Player.B, game.getCurrentPlayer());
		}
		else {
			assertEquals(Player.A, game.getCurrentPlayer());
		}
	}

	/**
	 * Test of getPlayerAMoves method, of class CGame.
	 */
	@Test
	public void testGetPlayerAMoves() {
		CGame game = new CGame(Logger.getLogger("Test Logger"));
		game.NewGame();
		
		Player Starter = game.getCurrentPlayer();
		
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		
		if (Starter == Player.A) {
			assertEquals(3, game.getPlayerAMoves());
		}
		else {
			assertEquals(2, game.getPlayerAMoves());
		}
	}

	/**
	 * Test of getPlayerBMoves method, of class CGame.
	 */
	@Test
	public void testGetPlayerBMoves() {
		CGame game = new CGame(Logger.getLogger("Test Logger"));
		game.NewGame();
		
		Player Starter = game.getCurrentPlayer();
		
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		
		if (Starter == Player.A) {
			assertEquals(2, game.getPlayerBMoves());
		}
		else {
			assertEquals(3, game.getPlayerBMoves());
		}
	}

	/**
	 * Test of Move method, of class CGame.
	 */
	@Test
	public void testMove() {
		CGame game = new CGame(Logger.getLogger("Test Logger"));
		game.NewGame();
		assertTrue(game.Move(3));
		
		game.Move(3);
		game.Move(3);
		game.Move(3);
		game.Move(3);
		
		assertFalse(game.Move(3));
	}
	
}
