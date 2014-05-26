/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

/**
 * A class for representing a player's statistics.
 * 
 * @author Sinku Tamás
 */
public class PlayerInfo {
	/**
	 * The player's name.
	 */
	public String Name;
	
	/**
	 * Number of wins.
	 */
	public int Wins;
	
	/**
	 * Number of loses.
	 */
	public int Loses;
	
	/**
	 * Constructs a new PlayerInfo instance.
	 * 
	 * @param Name the player's name
	 * @param Wins the number of wins
	 * @param Loses the number of loses
	 */
	public PlayerInfo(String Name, int Wins, int Loses) {
		this.Name = Name;
		this.Wins = Wins;
		this.Loses = Loses;
	}
}
