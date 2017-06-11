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
        
        printTitle();
        while (isRunning) {
            // TODO: print game
            // TODO: get inputs from the players
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
    
    public static void main(String[] args) {
        new ChessGame().run();
    }
}
