package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 * This class represents chess piece named Knight. It contains methods
 * for validation a move and representation in window.
 * 
 * @author Rastislav Maděra
 */
public class Knight extends ChessPiece {

    /**
     * Class contructor. It creates a chess piece of type Knight on the position based
     * on color and side from the king.
     *
     * @param isLeft It specifies if the piece is located on the left side
     * @param color  It specifies color of the piece
     */
    public Knight(boolean isLeft, Color color) {
        super(isLeft ? HighPieceColumn.LEFT_KNIGHT.getIndex() :
                HighPieceColumn.RIGHT_KNIGHT.getIndex(), color);
    }
    
    /**
     * Class contructor. It creates a chess piece of type Knight on the specific
     * position based on color.
     *
     * @param x     It specifies position x
     * @param y     It specifies position y
     * @param color It specifies color of the piece
     */
    public Knight(int x, int y, Color color) {
        super(x, y, color);
    }
    
    /**
     * It checks if the piece has a valid move based on target square in board.
     *
     * First It's checks if there is any chess piece and if there is then checks if
     * that piece doesn't have the same color
     * Finally it checks if the coordinates are correct.
     * For Knight it checks if the target square is placed diagonally.
     *
     * @param square It specifies target square.
     * @return It returns if the move is valid.
     */
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (square.getX()  == x - 2 && square.getY()  == y + 1) ||
                (square.getX() == x - 2 && square.getY() == y - 1) ||
                (square.getX() == x + 2 && square.getY() == y + 1) ||
                (square.getX() == x + 2 && square.getY() == y - 1) ||
                (square.getX() == x - 1 && square.getY() == y + 2) ||
                (square.getX() == x - 1 && square.getY() == y - 2) ||
                (square.getX() == x + 1 && square.getY() == y + 2) ||
                (square.getX() == x + 1 && square.getY() == y - 2);
    }

    /**
     * It returns text representation of Knight in Console Window.
     *
     * @return It returns a text representation.
     */
    @Override
    public String toString() {
        return "Kn";
    }
    
}
