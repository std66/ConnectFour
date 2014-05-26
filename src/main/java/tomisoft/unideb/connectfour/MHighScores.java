/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for querying the database for scores.
 * 
 * @author Sinku Tamás
 */
public class MHighScores {
	
	/**
	 * The SQL connection resource.
	 */
	private Connection c;
	
	/**
	 * Constructs a new MHighScores instance and connects to the database.
	 */
	public MHighScores() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, "Could not load Oracle driver.");
		}
		
		try {
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "H_H4855U", "kassai");
		} catch (SQLException ex) {
			Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, null, "Could not connect to the database");
		}
	}
	
	/**
	 * Gets all players' information from the database.
	 * 
	 * @return a list of <code>PlayerInfo</code> instances
	 */
	public List<PlayerInfo> GetAllPlayerInfo() {
		ArrayList<PlayerInfo> Result = new ArrayList<PlayerInfo>();
		
		try {
			Statement s = this.c.createStatement();
			ResultSet row = s.executeQuery("SELECT * FROM cf_playerinfo ORDER BY wins DESC");
			
			while (row.next()) {
				Result.add(new PlayerInfo(
					row.getString("name"),
					row.getInt("wins"),
					row.getInt("loses")
				));
			}
			
			row.close();
			s.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, "Could not get data from the database");
		}
		
		return Result;
	}
	
	/**
	 * Updates a player's data.
	 * 
	 * @param Name the player's name
	 * @param HasWon has the player won the game?
	 */
	public void UpdatePlayer(String Name, boolean HasWon) {
		if (this.IsPlayerExists(Name)) {
			try {
				String SQL = HasWon ? "wins = wins+1" : "loses = loses+1";
				
				Statement s = this.c.createStatement();
				ResultSet row = s.executeQuery("UPDATE cf_playerinfo SET "+SQL+" WHERE lower(name) = '" + Name.toLowerCase() + "'");
			
				row.close();
				s.close();
			} catch (SQLException ex) {
				Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, "Could not set data to the database");
			}
		}
		else {
			try {
				Statement s = this.c.createStatement();
				
				if (HasWon)
					s.executeQuery("INSERT INTO cf_playerinfo VALUES('" + Name + "', 1, 0)");
				else
					s.executeQuery("INSERT INTO cf_playerinfo VALUES('" + Name + "', 0, 1)");
			
				s.close();
			} catch (SQLException ex) {
				Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, "Could not set data to the database");
			}
		}
	}
	
	private boolean IsPlayerExists(String Name) {
		try {
			Statement s = this.c.createStatement();
			ResultSet row = s.executeQuery("SELECT COUNT(*) AS count_rows FROM cf_playerinfo WHERE lower(name) = '" + Name.toLowerCase() + "'");
			
			row.next();
			if (row.getInt("count_rows") == 1)
				return true;
			
			row.close();
			s.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(MHighScores.class.getName()).log(Level.SEVERE, "Could not get data from the database");
			return false;
		}
		return false;
	}
	
}
