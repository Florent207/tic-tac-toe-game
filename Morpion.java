import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Morpion extends JFrame implements ActionListener{

	JPanel bandeauPano = new JPanel();
	JPanel plateau = new JPanel();
	JLabel bandeau = new JLabel();
	JButton[] btn = new JButton[9];
	Random rand = new Random();
	
	int egalite = 0;
	boolean vsComputer = false;
	boolean found = false;
	boolean xturn = true;
	boolean isPlaying = true;
		

	public Morpion() {
		super( "Tic Tac Toe" );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// windows size 
		this.setSize(400, 450);
		// window centred to the desk
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		// layout for dialogue
		bandeauPano.setLayout(new BorderLayout());   
		bandeauPano.setBounds(0, 0, 400, 100);
        this.add(bandeauPano, BorderLayout.NORTH);
				
		// headband
		bandeau.setBackground(new Color(55, 100, 170));
		bandeau.setForeground(new Color(250, 250, 250));
		bandeau.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		bandeau.setHorizontalAlignment(JLabel.CENTER);
		bandeau.setOpaque(true);
		bandeauPano.add(bandeau);
		
		// display buttons
        plateau.setLayout(new GridLayout(3, 3, 1, 1));   
		this.add(plateau, BorderLayout.CENTER);
		
		// create buttons
		for (int i = 0; i < 9; i++) {
			btn[i] = new JButton();
			plateau.add(btn[i]);
            btn[i].setFont(new Font("Ink Free", Font.BOLD, 75));
            btn[i].setFocusable(false);
            btn[i].addActionListener(this);
		}
		
		vsComputer = false;
		setVisible(true);
		beginWindow("How do you want to play?");
	}

	public void beginWindow(String s) {
		this.bandeau.setText("Hi there !");
        Object[] option={"vs Computer (O)","2 players"};
        int n=JOptionPane.showOptionDialog(this,s,"Start",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
        	vsComputer = true;
        	startGame();            
        }
        else{
        	vsComputer = false;
            startGame();
        }
	}
    
	public void startGame() {
		int chance = rand.nextInt(100);
		if (vsComputer == true) {
			if (chance%2 == 0) {
	            xturn = true;
	            bandeau.setText("It's X's turn");
			}
			else {
	        	xturn = false;
	            bandeau.setText("It's O's turn");
	            computer();
	        } 
		}

		else {
			if (chance%2 == 0) {
	            xturn = true;
	            bandeau.setText("It's X's turn");
			}
			else {
        	xturn = false;
            bandeau.setText("It's O's turn");
			} 
		}
	}

	public void checkbtn() {
		int cases =rand.nextInt(9);
		for (int i = 0; i < 9; i++) {
			if ((i == cases) && (btn[i].getText() == "")) {
				found = true;
				btn[i].setForeground(new Color(0, 0, 255));
	            btn[i].setText("O");
	            computer();
			}
			else if ((i == cases) && (btn[i].getText() != "" )) {
				found = false;
				checkbtn();
			}
		} checkGame();
	}
		
	public void computer() {
		if (found == false) {
			checkbtn();
		}
		else {
            xturn = true;
            bandeau.setText("It's X's turn");
            egalite++;
            found = false;
		} 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (vsComputer == false) {
			for (int i = 0; i < 9; i++) {
	            if (e.getSource() == btn[i]) {
	                if (xturn) {
	                    if (btn[i].getText() == "") {
	                        btn[i].setForeground(new Color(255, 0, 0));
	                        btn[i].setText("X");
	                        xturn = false;
	                        bandeau.setText("It's O's turn");
	                        egalite++;
	                    }
	                } 
	                else {
	                    if (btn[i].getText() == "") {
	                        btn[i].setForeground(new Color(0, 0, 255));
	                        btn[i].setText("O");
	                        xturn = true;
	                        bandeau.setText("It's X's turn");
	                        egalite++;
	                    }
	                } 
	            } checkGame();
			}
		} 
		else if (vsComputer == true) {
			for (int i = 0; i < 9; i++) {
	            if (e.getSource() == btn[i]) {
	                if (xturn) {
	                    if (btn[i].getText() == "") {
	                        btn[i].setForeground(new Color(255, 0, 0));
	                        btn[i].setText("X");
	                        xturn = false;
	                        bandeau.setText("It's O's turn");
	                        egalite++;
	                        checkGame();
	                        computer();
	                    }
	                } 
	            } 
			}
		} 
	}
	
	public void checkGame() {
		if ((btn[0].getText() == "X") && (btn[1].getText() == "X") && (btn[2].getText() == "X")) {
			xWin(0,1,2);
		}
		else if ((btn[3].getText() == "X") && (btn[4].getText() == "X") && (btn[5].getText() == "X")) {
			xWin(3,4,5);
		}
		else if ((btn[6].getText() == "X") && (btn[7].getText() == "X") && (btn[8].getText() == "X")) {
			xWin(6,7,8);
		}
		else if ((btn[0].getText() == "X") && (btn[3].getText() == "X") && (btn[6].getText() == "X")) {
			xWin(0,3,6);
		}
		else if ((btn[1].getText() == "X") && (btn[4].getText() == "X") && (btn[7].getText() == "X")) {
			xWin(1,4,7);
		}
		else if ((btn[2].getText() == "X") && (btn[5].getText() == "X") && (btn[8].getText() == "X")) {
			xWin(2,5,8);
		}
		else if ((btn[0].getText() == "X") && (btn[4].getText() == "X") && (btn[8].getText() == "X")) {
			xWin(0,4,8);
		}
		else if ((btn[2].getText() == "X") && (btn[4].getText() == "X") && (btn[6].getText() == "X")) {
			xWin(2,4,6);
		}
		
		else if ((btn[0].getText() == "O") && (btn[1].getText() == "O") && (btn[2].getText() == "O")) {
			oWin(0,1,2);
		}
		else if ((btn[3].getText() == "O") && (btn[4].getText() == "O") && (btn[5].getText() == "O")) {
			oWin(3,4,5);
		}
		else if ((btn[6].getText() == "O") && (btn[7].getText() == "O") && (btn[8].getText() == "O")) {
			oWin(6,7,8);
		}
		else if ((btn[0].getText() == "O") && (btn[3].getText() == "O") && (btn[6].getText() == "O")) {
			oWin(0,3,6);
		}
		else if ((btn[1].getText() == "O") && (btn[4].getText() == "O") && (btn[7].getText() == "O")) {
			oWin(1,4,7);
		}
		else if ((btn[2].getText() == "O") && (btn[5].getText() == "O") && (btn[8].getText() == "O")) {
			oWin(2,5,8);
		}
		else if ((btn[0].getText() == "O") && (btn[4].getText() == "O") && (btn[8].getText() == "O")) {
			oWin(0,4,8);
		}
		else if ((btn[2].getText() == "O") && (btn[4].getText() == "O") && (btn[6].getText() == "O")) {
			oWin(2,4,6);
		}
		
		else if (egalite == 9) {
			bandeau.setText("Equal !");
			gameOver("Equal");
		} 
	}
	
	public void xWin(int x, int y, int z) {
		if (isPlaying == true) {
			btn[x].setBackground(Color.RED);
	        btn[y].setBackground(Color.RED);
	        btn[z].setBackground(Color.RED);

	        for (int i = 0; i < 9; i++) {
	            btn[i].setEnabled(false);
	        }
	        bandeau.setText("End of game");
	        gameOver("X win !");
	        isPlaying = false;
		}        
	}
	
	public void oWin(int x, int y, int z) {
		if (isPlaying == true) {
			btn[x].setBackground(Color.RED);
			btn[y].setBackground(Color.RED);
			btn[z].setBackground(Color.RED);

	        for (int i = 0; i < 9; i++) {
	            btn[i].setEnabled(false);
	        }
	        bandeau.setText("End of game");
	        gameOver("O win !");
	        isPlaying = false;
		}
	}
	
	public void gameOver(String s){
        egalite = 0;
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(this, s,"Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
            this.dispose();
            new Morpion();
        }
        else{
            this.dispose();
        }
    }
	
	public static void main(String[] args) throws Exception{
		// Start game
		Morpion game = new Morpion();
	}
}