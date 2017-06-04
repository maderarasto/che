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
    private static final int GAME_SIDE_SIZE = 20;
    
    private ChessPiece[][] gameBoard;
    
    public GameBoard() {
        gameBoard = new ChessPiece[SIDE_SIZE][SIDE_SIZE];
        
        InitializeGame();
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
    
    public void printGame() {
        System.out.println("      \033[31;1mChess game\033[0m     ");
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
    }
}
