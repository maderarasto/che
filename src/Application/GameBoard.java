/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ChessPieces.*;

/**
 *
 * @author Rastislav Maděra
 */
public class GameBoard {
    
    private static final int SIDE_SIZE = 8;
    //private static final int GAME_SIDE_SIZE = 20;
    private java.util.Scanner input;
    
    private ChessPiece[][] gameBoard;
    
    public GameBoard() {
        gameBoard = new ChessPiece[SIDE_SIZE][SIDE_SIZE];
        
        InitializeGame();
        input = new java.util.Scanner(System.in);
    }
    
    private void InitializeGame() {
        for (int j = 0; j < SIDE_SIZE; j++) {
            gameBoard[ChessPiece.PAWNS_POSITION][j] =
                    new Pawn(j, ChessPiece.PAWNS_POSITION);
        }
        
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.LEFT_ROOK.ordinal()] =
                new Rook(true);
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.LEFT_BISHOP.ordinal()] =
                new Bishop(true);
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.LEFT_KNIGHT.ordinal()] =
                new Knight(true);
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.QUEEN.ordinal()] =
                new Queen();
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.KING.ordinal()] =
                new King();
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.RIGHT_KNIGHT.ordinal()] =
                new Knight(false);
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.RIGHT_BISHOP.ordinal()] =
                new Bishop(false);
        gameBoard[ChessPiece.HIGH_PIECES_POSITION][Locations.RIGHT_ROOK.ordinal()] =
                new Rook(false);
    }
    
    public void run() {
        boolean isRunning = true;
        
        printTitle();
        while (isRunning) {
            printGame();
            makePlayerMove();
        }
    }
    
    private String getInputFromPlayer(String text) {
        System.out.print(text + "(1-8,A-H): ");
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
    
    private void makePlayerMove() {
        int x, y, toX, toY;
        String txtPos;
        
        txtPos = getInputFromPlayer("Select a piece");
        x = getIndexFromString(txtPos, true);
        y = getIndexFromString(txtPos, false);
        txtPos = getInputFromPlayer("Make move");
        toX = getIndexFromString(txtPos, true);
        toY = getIndexFromString(txtPos, false);
        
        while (!gameBoard[y][x].IsValidMove(toX, toY)) {
            System.err.println("Invalid move!");
            txtPos = getInputFromPlayer("Try again");
            toX = getIndexFromString(txtPos, true);
            toY = getIndexFromString(txtPos, false);
        }
        
        gameBoard[toY][toX] = gameBoard[y][x];
        gameBoard[y][x] = null;
    }
    
    private void printTitle() {
        System.out.print("      ");
        System.out.print("\033[31;1mChess game\033[0m");
        System.out.print("      ");
        System.out.println();
    }
    
    private void printGame() {
        System.out.println("  |A B C D E F G H |  ");
        System.out.println("--+----------------+--");
        
        for (int i = SIDE_SIZE; i > 0; i--) {
            System.out.format("%d |", i);
            
            for (int j = 0; j < SIDE_SIZE; j++) {
                if (gameBoard[i - 1][j] != null) {
                    System.out.print(gameBoard[i - 1][j]);
                } else {
                    System.out.print("  ");
                }
                
            }
            
            System.out.format("| %d", i);
            System.out.println();
        }
        
        System.out.println("--+----------------+--");
        System.out.println("  |A B C D E F G H |  ");
        System.out.println();
    }
}
