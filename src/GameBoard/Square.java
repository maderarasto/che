package GameBoard;

import ChessPieces.ChessPiece;
import Enums.Color;

/**
 * This class represents one square in the game board. It contains methods
 * for getting information about square and for assign piece to it.
 *
 * @author Rastislav Maděra
 */
public class Square {
    /**
     * It represents coordinate X of square and is unchangable.
     */
    private final int x;
    
    /**
     * It represents coordinate Y of square and is unchangable.
     */
    private final int y;
    
    /**
     * It represents a piece, which is placed on the square.
     */
    private ChessPiece piece;
    
    
    /**
     * Class constructor. It creates square of gameboard with specific indexes
     * and sets the piece to null.\
     * 
     * @param x It specifies horizontal coordinate.
     * @param y It specifies vertical coordinate.
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
    }
    
    /**
     * It gets index of horizontal coordinate.
     * 
     * @return It returns horizontal index.
     */
    public int getX() {
        return x;
    }
    
    /**
     * It gets index of vertical coordinate.
     * 
     * @return It returns vertical index.
     */
    public int getY() {
        return y;
    }
    
    /**
     * It gets a piece placed on the square.
     * 
     * @return It returns the piece, which is placed on the square.
     */
    public ChessPiece getPiece() {
        return piece;
    }
    
    /**
     * It places the piece on the square.
     * 
     * @param piece It specifies which piece is placed.
     */
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
    
    /**
     * It checks if there is any piece on the square.
     * 
     * @return It returns piece.
     */
    public boolean isThereAnyPiece() {
        return piece != null;
    }
    
    /**
     * It checks if there is placed any of specific pieces on the square. 
     * The method receives variable number of arguments
     * 
     * @param pieceNames It specifies name of piece or pieces.
     * @return It return if any of pieces was found.
     */
    public boolean IsTherePiece(String... pieceNames) {
        for (String strPiece : pieceNames) {
            if (piece != null && 
                piece.getClass().getSimpleName().equals(strPiece)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * It calculated and gets distance from the target. It works diagonally,
     * vertically and horizontally.
     * 
     * @param target It specifies target square from which can be distance calculated.
     * @return It returns integer number of distance between squares.
     */
    public int getDistanceFromTarget(Square target) {
        int length = 0;
        
        if (Math.abs(x - target.getX()) == Math.abs(y - target.getY())) {
            length = Math.abs(x - target.getX());
        }
        else if (x == target.getX()) {
            length = Math.abs(y - target.getY());
        } else if (y == target.getY()) {
            length = Math.abs(x - target.getX());
        }
        
        return length;
    }
    
    /**
     * It gets a text representation of square based on placed piece and its color. 
     * If there is piece, then it returns string representation of the piece or else
     * returns space.
     * 
     * @return It returns string representation of the square.
     */
    @Override
    public String toString() {
        if (piece != null && piece.getColor() == Color.WHITE) {
            return "\033[30;1m" + piece + "\033[0m";
        } else if (piece != null && piece.getColor() == Color.BLACK) {
            return "\033[37;1m" + piece + "\033[0m";
        }
        
        return "  ";        
    }
}
