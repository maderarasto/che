/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

/**
 *
 * @author rasto
 */
public class ChessGame {
    
    private GameBoard board;
    private final java.util.Scanner input;
    
    private ChessGame() {
        board = new GameBoard();
        input = new java.util.Scanner(System.in);
    }
    
    private void run() {
        boolean isRunning = true;
        String txtPos;
        int x, y, toX, toY;
        
        printTitle();
        while (isRunning) {
            board.printGame();
            
            txtPos = getInputFromPlayer("Select a piece");
            x = getIndexFromString(txtPos, true);
            y = getIndexFromString(txtPos, false);
            txtPos = getInputFromPlayer("Make move");
            toX = getIndexFromString(txtPos, true);
            toY = getIndexFromString(txtPos, false);
        }
    }
    
    private void printTitle() {
        System.out.print("      ");
        System.out.print("\033[31;1mChess game\033[0m");
        System.out.print("      ");
        System.out.println();
    }
    
    private String getInputFromPlayer(String message) {
        System.out.print(message + "(1-8,A-H): ");
        String txtPos = input.nextLine().toLowerCase();
        while (txtPos.length() != 2 ||
                !(txtPos.charAt(0) >= '1' && txtPos.charAt(0) <= '8') ||
                !(txtPos.charAt(1) >= 'a' && txtPos.charAt(1) <= 'h')) {
            System.err.println("Wrong position!");
            System.out.print("Try again: ");
            txtPos = input.nextLine().toLowerCase();
        }
        
        return txtPos;
    }
    
    private static int getIndexFromString(String text, boolean firstX) {
        return firstX ? (int)(text.charAt(1) - 'a') 
                : (int)(text.charAt(0) - '1'); 
    }
    
    public static void main(String[] args) {
        new ChessGame().run();
    }
}
