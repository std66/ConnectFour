/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tomisoft.unideb.connectfour;

/**
 * This class represents a board.
 * 
 * Example:
 * <pre>
 * Board b = new Board(5, 5);
 * b.Put(Player.A, 3);
 * System.out.println(b);
 * </pre>
 * 
 * @author Sinku Tamás
 */
public class Board {
    
    /**
     * A 2d array that stores information about the board.
     */
    private Player[][] board;
    
    /**
     * The number of columns in the board.
     */
    private int Cols;
    
    /**
     * The number of rows in the board.
     */
    private int Rows;
    
    /**
     * Constructs a new ConnectFour board using the given row and column numbers.
     * 
     * @param Rows the number of rows
     * @param Cols the number of columns
     */
    public Board(int Rows, int Cols) {
        this.Cols = Cols;
        this.Rows = Rows;
        
        this.board = new Player[this.Rows][Cols];
        
        for (int Row = 0; Row < this.Rows; Row++) {
            for (int Col = 0; Col < this.Cols; Col++) {
                this.board[Row][Col] = Player.NONE;
            }
        }
    }

    /**
     * Gets the number of columns of the board.
     * 
     * @return the number of columns
     */
    public int getCols() {
        return Cols;
    }

    /**
     * Gets the number of rows of the board.
     * 
     * @return the number of rows
     */
    public int getRows() {
        return Rows;
    }
    
    /**
     * Gets the field's state in the given row and column.
     * 
     * @param Row the row index of the field
     * @param Col the column index of the field
     * @return the field's state
     */
    public Player getFieldState(int Row, int Col) {
        return this.board[Row][Col];
    }
    
    /**
     * Places a marker for a player on the board.
     * 
     * @param p which player's mark to set
     * @param Column the column index where to set
     * @throws BoardColumnFullException occures when the column is full
     * @throws BoardIllegalColumnException occures then column is not in range
     */
    public void Put(Player p, int Column) throws BoardColumnFullException, BoardIllegalColumnException {
        if (Column >= this.Cols || Column < 0) {
            throw new BoardIllegalColumnException(Column);
        }
        
        boolean IsPlaced = false;
        for (int Row = this.Rows - 1; Row >= 0; Row--) {
            if (this.board[Row][Column] == Player.NONE) {
                this.board[Row][Column] = p;
                IsPlaced = true;
                break;
            }
        }
        
        if (!IsPlaced) {
            throw new BoardColumnFullException(Column);
        }
    }
    
    /**
     * Determines whether the board is full.
     * 
     * @return true if the board is full, false otherwise
     */
    public boolean IsFull() {
        for (int Row = 0; Row < this.Rows; Row++) {
            for (int Col = 0; Col < this.Cols; Col++) {
                if (this.board[Row][Col] == Player.NONE) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Gets the winner of the game.
     * 
     * @return the winner of the game
     */
    public Player GetWinnerPlayer() {
        //Horizontal test
        for (int Row = 0; Row < this.Rows; Row++) {
            for (int StartCol = 0, EndCol = 3; EndCol < this.Cols; StartCol++, EndCol++) {
                if (this.board[Row][StartCol] != Player.NONE) {
                    Player Check = this.board[Row][StartCol];
                    boolean FoundWinner = true;
                    
                    for (int CurrentCol = StartCol; CurrentCol <= EndCol; CurrentCol++) {
                        if (this.board[Row][CurrentCol] != Check) {
                            FoundWinner = false;
                            break;
                        }
                    }
                    
                    if (FoundWinner) return Check;
                }
            }
        }
        
        //Vertical test
        for (int Col = 0; Col < this.Cols; Col++) {
            for (int StartRow = 0, EndRow = 3; EndRow < this.Rows; StartRow++, EndRow++) {
                if (this.board[StartRow][Col] != Player.NONE) {
                    Player Check = this.board[StartRow][Col];
                    boolean FoundWinner = true;
                    
                    for (int CurrentRow = StartRow; CurrentRow <= EndRow; CurrentRow++) {
                        if (this.board[CurrentRow][Col] != Check) {
                            FoundWinner = false;
                            break;
                        }
                    }
                    
                    if (FoundWinner) return Check;
                }
            }
        }
        
		//Diagonal test \
        for (int Row = this.Rows - 1; Row > 2; Row--) {
			for (int Col = 0; Col < this.Cols - 3; Col++) {
				boolean Found = true;
				
				Player Selected = this.board[Row][Col];
				
				if (Selected == Player.NONE)
					continue;
				
				for (int i = 1; i < 4; i++) {
					Player Current = this.board[Row - i][Col + i];
					if (Selected != Current) {
						Found = false;
						break;
					}
				}
				
				if (Found) {
					return Selected;
				}
			}
		}
        
        //Diagonal test /
		for (int Row = this.Rows - 1; Row >= 2; Row--) {
			for (int Col = this.Cols - 1; Col >= 2; Col--) {
				boolean Found = true;
				
				Player Selected = this.board[Row][Col];
				
				if (Selected == Player.NONE)
					continue;
				
				for (int i = 1; i < 4; i++) {
					Player Current = this.board[Row - i][Col - i];
					if (Selected != Current) {
						Found = false;
						break;
					}
				}
				
				if (Found) return Selected;
			}
		}
		
		/*
		for (int Col = this.Cols - 1; Col >= 0; Col--) {
			for (int StartRow = this.Rows - 1, EndRow = StartRow - 3, StartCol = Col, EndCol = Col - 3; EndCol >= 0 && EndRow >= 0; StartRow--, EndRow--, StartCol--, EndCol--) {
				if (this.board[StartRow][Col] != Player.NONE) {
					System.out.println(StartRow + " " + StartCol);
					Player Check = this.board[StartRow][Col];
					boolean FoundWinner = true;

					for (int CurrentRow = StartRow, CurrentCol = StartCol; CurrentRow >= EndRow && CurrentCol >= EndCol; CurrentCol--, CurrentRow--) {
						if (this.board[CurrentRow][CurrentCol] != Check) {
							FoundWinner = false;
							break;
						}
					}

					if (FoundWinner) return Check;
				}
			}
		}
		*/
		
        return Player.NONE;
    }

    /**
     * Gets the string representation of the board.
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int Row = 0; Row < this.Rows; Row++) {
            for (int Col = 0; Col < this.Cols; Col++) {
                switch (this.board[Row][Col]) {
                    case A:
                        b.append("A ");
                    break;
                        
                    case B:
                        b.append("B ");
                    break;
                        
                    case NONE:
                        b.append("_ ");
                    break;
                }
            }
            b.append("\n");
        }
        
        return b.toString();
    }
    
    
}
