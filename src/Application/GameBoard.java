/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Enums.PieceIndex;
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
        players[PLAYER_1] = new Player(Color.WHITE);
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
        int rowOthers = color == Color.WHITE ? 0 : 7;
        int rowPawns = color == Color.WHITE ? 1 : 6;
        
        for (int i = 0; i < 8; i++) {
            gameBoard[rowPawns][i].setPiece(new Pawn(i, rowPawns, color));
        }
        
        gameBoard[rowOthers][PieceIndex.LEFT_ROOK.getIndex()].setPiece(
                new Rook(true, color));
        gameBoard[rowOthers][PieceIndex.LEFT_BISHOP.getIndex()].setPiece(
                new Bishop(true, color));
        gameBoard[rowOthers][PieceIndex.LEFT_KNIGHT.getIndex()].setPiece(
                new Knight(true, color));
        gameBoard[rowOthers][PieceIndex.QUEEN.getIndex()].setPiece(
                new Queen(color));
        gameBoard[rowOthers][PieceIndex.KING.getIndex()].setPiece(
                new King(color));
        gameBoard[rowOthers][PieceIndex.RIGHT_KNIGHT.getIndex()].setPiece(
                new Knight(false, color));
        gameBoard[rowOthers][PieceIndex.RIGHT_BISHOP.getIndex()].setPiece(
                new Bishop(false, color));
        gameBoard[rowOthers][PieceIndex.RIGHT_ROOK.getIndex()].setPiece(
                new Rook(false, color));
    }   
    
    
    public void makePlayerMove(int x, int y, int toX, int toY) 
        throws Exception {
        
        ChessPiece piece = null;
        
        if (!gameBoard[y][x].getPiece().IsValidMove(gameBoard[toY][toX])) {
            throw new Exception("Invalid target");
        }
        
        if (gameBoard[toY][toX].getPiece() != null && gameBoard[toY][toX].getPiece().getColor() != players[actualPlayer].getColor())
        {
            piece = gameBoard[toY][toX].getPiece();
            players[actualPlayer].takeOponentPiece(gameBoard[toY][toX]);
        }
        
        gameBoard[toY][toX].setPiece(gameBoard[y][x].getPiece());   // Move a piece to new square
        gameBoard[y][x].setPiece(null); // Clear piece from previous square
        
        players[actualPlayer].movePiece(gameBoard[toY][toX].getPiece(), 
                gameBoard[toY][toX]);
        actualPlayer = (++actualPlayer) % 2;    // Passing moves between 2 Players
    }
     /*
    
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
        System.out.println(players[PLAYER_2]);
        System.out.println("  | A   B   C   D   E   F   G   H |  ");
        System.out.println("--+---+---+---+---+---+---+---+---+--");
        
        for (int i = SIDE_SIZE; i > 0; i--) {
            System.out.format("%d |", i);
            
            for (int j = 0; j < SIDE_SIZE; j++) {
                System.out.format("%s%s ", j == 0 ? "" : "|", gameBoard[i-1][j]);
                
            }
            
            System.out.format("| %d\n", i);
            System.out.format("%s", i == 1 ? "" : 
                    "  |---+---+---+---+---+---+---+---|  \n");
        }
        
        System.out.println("--+---+---+---+---+---+---+---+---+--");
        System.out.println("  | A   B   C   D   E   F   G   H |  ");
        System.out.println(players[PLAYER_1]);
        System.out.println();
    }
    
}
