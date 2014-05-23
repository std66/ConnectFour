/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomisoft.unideb.connectfour;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Main window class of the game.
 * 
 * @author Sinku Tamás
 */
public class frmMainWindow extends javax.swing.JFrame {

	/**
	 * The game controller.
	 */
	CGame GameController = new CGame(java.util.logging.Logger.getLogger(frmMainWindow.class.getName()));
	
	/**
	 * The game field displayer text boxes.
	 */
	private JTextField[][] Field;
	
	/**
	 * Place buttons.
	 */
	private JButton[] Button;

	/**
	 * Number of rows.
	 */
	public int Rows = 5;
	
	/**
	 * Number of columns.
	 */
	public int Cols = 5;

	/**
	 * Creates new form frmMainWindow.
	 */
	public frmMainWindow() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miNewGame = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Game");

        miNewGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        miNewGame.setText("New");
        miNewGame.setName("miNewGame"); // NOI18N
        miNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewGameActionPerformed(evt);
            }
        });
        jMenu1.add(miNewGame);
        jMenu1.add(jSeparator1);

        miExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        miExit.setText("Exit");
        miExit.setName(""); // NOI18N
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * New game menu item onclick event.
	 * 
	 * @param evt the event arguments
	 */
    private void miNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewGameActionPerformed
		this.GameController.NewGame();

		Board b = this.GameController.getBoard();
		this.Rows = b.getRows();
		this.Cols = b.getCols();
		this.InitializeField(Rows, Cols);
    }//GEN-LAST:event_miNewGameActionPerformed

	/**
	 * Exit menu onclick event.
	 * 
	 * @param evt the event arguments
	 */
    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
		System.exit(0);
    }//GEN-LAST:event_miExitActionPerformed

	/**
	 * Help menu item onclick event.
	 * 
	 * @param evt the event arguments
	 */
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        JOptionPane.showMessageDialog(this, "TomiSoft Scrabble\nCopyright (C) 2014:"
				+ "TomiSoft\n\nVersion: 1.0");
    }//GEN-LAST:event_jMenu2MouseClicked

	/**
	 * Updates the game field.
	 */
	public void UpdateField() {
		for (int Row = 0; Row < this.GameController.getBoard().getRows(); Row++) {
			for (int Col = 0; Col < this.GameController.getBoard().getCols(); Col++) {
				Player p = this.GameController.getBoard().getFieldState(Row, Col);

				switch (p) {
					case A:
						this.Field[Row][Col].setText("A");
						break;

					case B:
						this.Field[Row][Col].setText("B");
						break;
				}
			}
		}
	}

	/**
	 * Initializes the game field.
	 * 
	 * @param Rows the number of rows
	 * @param Cols the number of columns
	 */
	public void InitializeField(int Rows, int Cols) {
		this.DeleteControls();
		
		this.setLayout(new GridLayout(Rows + 1, Cols));

		this.Field = new JTextField[Rows][Cols];
		this.Button = new JButton[Cols];

		//Lerakjuk az új vezérlőket
		for (int Row = 0; Row < Rows; Row++) {
			for (int Col = 0; Col < Cols; Col++) {
				JTextField f = new JTextField();
				f.setName("Field" + Row + Col);
				f.setEditable(false);
				f.setHorizontalAlignment(JTextField.CENTER);

				this.add(f);

				this.Field[Row][Col] = f;
			}
		}

		//Lerakjuk a nyomógombokat
		for (int Col = 0; Col < Cols; Col++) {
			this.Button[Col] = new JButton("Place");

			final int CurrentCol = Col;
			final CGame gc = this.GameController;
			final JFrame frm = this;
			this.Button[Col].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (gc.Move(CurrentCol)) {
						UpdateField();
						
						Player winner = gc.getBoard().GetWinnerPlayer();
						if (winner != Player.NONE) {
							JOptionPane.showMessageDialog(frm, "Player "+winner.toString()+" has won");
							DeleteControls();
						}
					}
					else {
						JOptionPane.showMessageDialog(frm, "Cannot place your move here");
					}
				}
			});

			this.add(this.Button[Col]);
		}

		this.pack();
	}
	
	/**
	 * Deletes the controls on the form.
	 */
	public void DeleteControls() {
		if (this.Field != null) {
			for (int Row = 0; Row < this.Rows; Row++) {
				for (int Col = 0; Col < this.Cols; Col++) {
					this.remove(this.Field[Row][Col]);
				}
			}
		}
		
		if (this.Button != null) {
			for (JButton btn : this.Button) {
				this.remove(btn);
			}
		}
		
		this.invalidate();
	}

	/**
	 * Starts the application.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException |
				 IllegalAccessException | UnsupportedLookAndFeelException ex) {
			Logger l = java.util.logging.Logger.getLogger(frmMainWindow.class.getName());
			l.log(
				java.util.logging.Level.SEVERE,
				null,
				"Could not start UI"
			);
		} 
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			/**
			 * Runs the application.
			 */
			@Override
			public void run() {
				frmMainWindow wnd = new frmMainWindow();
				wnd.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miNewGame;
    // End of variables declaration//GEN-END:variables
}
