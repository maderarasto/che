package ChessPieces;

import GameBoard.Square;
import Enums.Color;
import Enums.PieceRow;

/**
 * This class represents chess piece named Pawn. It contains methods
 * for validation a move and representation in window.
 *
 * @author Rastislav Maděra
 */
public class Pawn extends ChessPiece {

    /**
     * It represents if the Pawn is played from top or bottom. 
     * Direction is based on color. From bottom: 1, from top: -1.
     */
    private final int direction;
    
     /**
     * Class contructor. It creates a chess piece of type Pawn on the specific
     * position based on color.
     *
     * @param x     It specifies position x
     * @param y     It specifies position y
     * @param color It specifies color of the piece
     */
    public Pawn(int x, int y, Color color)
    {
        super(x, y, color);
        direction = color.isWhite() ? 1 : -1;
    }
    
    /**
     * It checks if Pawn is at initial position.
     * 
     * @return It returns if Pawn stands on start position.
     */
    private boolean isAtInitialPos() {
        int initPos = color.isWhite() ? PieceRow.WHITE_PAWN_PIECE.getIndex() 
                : PieceRow.BLACK_PAWN_PIECE.getIndex();
        return y == initPos;
    }
    
    /**
     * It checks if the piece has a valid move based on target square in board.
     *
     * First It's checks if there is any chess piece and if there is then checks if
     * that piece doesn't have the same color
     * Finally it checks if the coordinates are correct.
     * For pawn it checks if the Pawn stands on the initial position, then it can be
     * moved by 2 squares verticaly in its direction.
     * If Pawn is out of its initial position, then can be moved by one square vertically
     * in its direction.
     * 
     * If it detects that square located diagonally by one square have oponent's piece then
     * the Pawn can take it
     * 
     * @param square It specifies target square.
     * @return It returns if the move is valid.
     */
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (isAtInitialPos() && square.getY() == y + 2*direction && square.getX() == x) ||
                (square.getY() == y + direction && square.getX() == x    ) ||
                (square.getX() == x + direction && square.getY() == y + 1) ||
                (square.getX() == x - direction && square.getY() == y + 1);
    }

    /**
     * It returns text representation of Pawn in Console Window.
     *
     * @return It returns a text representation.
     */
    @Override
    public String toString() {
        return "Pa";
    } 
}
