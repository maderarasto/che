/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ChessPieces.*;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class GameBoard {
    
    public  static final int SIDE_SIZE  = 8;
    private static final int PLAYER_1   = 0;
    private static final int PLAYER_2   = 1;
    
    private Square[][] gameBoard;
    private Player[] players;
    
    private int actualPlayer;
    
    public GameBoard() {
        gameBoard = new Square[SIDE_SIZE][SIDE_SIZE];
        players = new Player[2];
        players[PLAYER_1] = new Player(Color.BLUE);
        players[PLAYER_2] = new Player(Color.BLACK);
        actualPlayer = PLAYER_1;
        
        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                gameBoard[i][j] = new Square(j, i);
            }
        }
        
        layoutPieces(players[PLAYER_1].getColor());
        layoutPieces(players[PLAYER_2].getColor());
    }
    
    private void layoutPieces(Color color) {
        int rowOthers = color == Color.BLUE ? 0 : 7;
        int rowPawns = color == Color.BLUE ? 1 : 6;
        
        for (int i = 0; i < 8; i++) {
            gameBoard[rowPawns][i].setPiece(new Pawn(i, rowPawns, color));
        }
        
        gameBoard[rowOthers][Location.LEFT_ROOK.ordinal()].setPiece(new Rook(true, color));
        gameBoard[rowOthers][Location.LEFT_BISHOP.ordinal()].setPiece(new Bishop(true, color));
        gameBoard[rowOthers][Location.LEFT_KNIGHT.ordinal()].setPiece(new Knight(true, color));
        gameBoard[rowOthers][Location.QUEEN.ordinal()].setPiece(new Queen(color));
        gameBoard[rowOthers][Location.KING.ordinal()].setPiece(new King(color));
        gameBoard[rowOthers][Location.RIGHT_KNIGHT.ordinal()].setPiece(new Knight(false, color));
        gameBoard[rowOthers][Location.RIGHT_BISHOP.ordinal()].setPiece(new Bishop(false, color));
        gameBoard[rowOthers][Location.RIGHT_ROOK.ordinal()].setPiece(new Rook(false, color));
    }   
    
    /*
    public void makePlayerMove(int x, int y, int toX, int toY) 
        throws Exception {
        
        if (!gameBoard[y][x].IsValidMove(toX, toY)) {
            throw new Exception("Invalid arguments");
        }
        
        gameBoard[toY][toX] = gameBoard[y][x];
        gameBoard[y][x] = null;
        gameBoard[toY][toX].setX(toX);
        gameBoard[toY][toX].setY(toY);
        updateKingPosition(toX, toY);
        

        if (isKingAttacked()) {
            gameBoard[y][x] = gameBoard[toY][toX];
            gameBoard[toY][toX] = null;
            gameBoard[y][x].setX(x);
            gameBoard[y][x].setY(y);
            updateKingPosition(x, y);
            throw new Exception("King would not be safe!");
        }
    }
    
    private void updateKingPosition(int toX, int toY) {
        if (gameBoard[toY][toX].getClass().getSimpleName().equals("King")) {
            kingX = toX;
            kingY = toY;
        }
    }
    
    private boolean isKingAttacked() {      
        for (int i = 1; i >= -1; i--) {
            for (int j = -1; j <= 1; j++) {
                int count = 0;
                
                int row = kingY + i*(count + 1);
                int col = kingX + j*(count + 1);
                boolean pieceFound = false;
                
                while (!pieceFound && row >= 0 && row < 8 && col >= 0 && col < 8) {
                    if (gameBoard[row][col] != null && 
                            gameBoard[row][col].IsValidMove(kingX, kingY)) { // IF PIECE IS ENEMY AND WOULD ATTACK KING
                        return true;
                    } else if (gameBoard[row][col] != null) {
                        pieceFound = true;
                    }
                    
                    count++;
                    row = kingY + i*(count + 1);
                    col = kingX + j*(count + 1);
                }
            }
        }
        
        return false;
    }
    */    
    
    public void printGame() {
        System.out.println("  |A B C D E F G H |  ");
        System.out.println("--+----------------+--");
        
        for (int i = SIDE_SIZE; i > 0; i--) {
            System.out.format("%d |", i);
            
            for (int j = 0; j < SIDE_SIZE; j++) {
                System.out.print(gameBoard[i-1][j]);
                
            }
            
            System.out.format("| %d", i);
            System.out.println();
        }
        
        System.out.println("--+----------------+--");
        System.out.println("  |A B C D E F G H |  ");
        System.out.println();
    }
    
}
