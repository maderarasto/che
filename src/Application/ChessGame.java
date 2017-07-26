 package Application;

import GameBoard.GameBoard;
import Enums.Color;

/**
 *
 * @author Rastislav Madera
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
            
            System.out.format("PLAYER%d ", board.getActualPlayer().getColor() == Color.WHITE ?
                    1 : 2);
            txtPos = getInputFromPlayer("Select a piece");
            
            if (txtPos.equals("quit")) {
                System.out.println("GAME OVER");
                break;  
            }
            
            x = getIndexFromString(txtPos, true);
            y = getIndexFromString(txtPos, false);
            if (!board.isValidSelection(x, y)) {
                System.err.println("It isn't a valid selection. Try Again!");
                continue;
            }
            
            
            txtPos = getInputFromPlayer("Make move");
            toX = getIndexFromString(txtPos, true);
            toY = getIndexFromString(txtPos, false);
            
            try {
                board.makePlayerMove(x, y, toX, toY);
            } catch (Exception ex) {
                System.err.format("Error: %s\n", ex.getMessage());
                System.out.println("Try Again!");
                continue;
            }
            

        isRunning = !board.CheckmateDecision();
            board.printGame();
        }
        
        //board.printGame();
        System.out.println("GAME OVER!");
    }
    
    private void printTitle() {
        System.out.print("              ");
        System.out.print("\033[31;1mChess game\033[0m");
        System.out.println();
    }
    
    private String getInputFromPlayer(String message) {
        System.out.print(message + "(1-8,A-H): ");
        String txtPos = input.nextLine().toLowerCase();
        
        if (txtPos.equals("quit")) {
            return txtPos;
        }
        
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
