package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 * This class represents chess piece named Rook. It contains methods
 * for validation a move and representation in window.
 *
 * @author Rastislav Maděra
 */
public class Rook extends ChessPiece {

     /**
     * Class contructor. It creates a chess piece of type Rook on the position based
     * on color and side from the king.
     *
     * @param isLeft It specifies if the piece is located on the left side
     * @param color  It specifies color of the piece
     */
    public Rook(boolean isLeft, Color color) {
        super(isLeft ? HighPieceColumn.LEFT_ROOK.getIndex() : 
                HighPieceColumn.RIGHT_ROOK.getIndex(), color);
    }
    
    /**
     * Class contructor. It creates a chess piece of type Rook on the specific
     * position based on color.
     *
     * @param x     It specifies position x
     * @param y     It specifies position y
     * @param color It specifies color of the piece
     */
    public Rook(int x, int y, Color color) {
        super(x, y, color);
    }
    
    /**
     * It checks if the piece has a valid move based on target square in board.
     *
     * First It's checks if there is any chess piece and if there is then checks if
     * that piece doesn't have the same color
     * Finally it checks if the coordinates are correct.
     * For rook it checks if the target square is placed either horizontally or vertically.
     *
     * @param square It specifies target square.
     * @return It returns if the a move is valid.
     */
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (square.getX()  == x && square.getY() != y) ||
                (square.getY() == y && square.getX() != x);
    }

    /**
     * It returns text representation of Rook in Console Window.
     *
     * @return It returns a text representation.
     */
    @Override
    public String toString() {
        return "Ro";
    }
    
}
