package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 * This class represents chess piece named Queen. It contains methods
 * for validation a move and representation in window.
 *
 * @author Rastislav Maděra
 */
public class Queen extends ChessPiece {

     /**
     * Class contructor. It creates a chess piece of type Queen on the position based
     * on color.
     *
     * @param color  It specifies color of the piece
     */
    public Queen(Color color) {
        super(HighPieceColumn.QUEEN.getIndex(), color);
    }
    
    /**
     * Class contructor. It creates a chess piece of type Queen on the specific
     * position based on color.
     *
     * @param x     It specifies position x
     * @param y     It specifies position y
     * @param color It specifies color of the piece
     */
    public Queen(int x, int y, Color color) {
        super(x, y, color);
    }
    
    /**
     * It checks if the piece has a valid move based on target square in board.
     *
     * First It's checks if there is any chess piece and if there is then checks if
     * that piece doesn't have the same color
     * Finally it checks if the coordinates are correct.
     * For queen it checks if the target square is placed either diagonally, 
     * vertically or horizontally.
     *
     * @param square It specifies target square.
     * @return It returns if the move is valid.
     */
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece()) {
            if (square.getPiece().getColor() == color) {
                return false;
            }
        }
        
        return  (square.getX() == x && square.getY() != y) ||
                (square.getY() == y && square.getX() != x) || 
                (square.getX() != x && square.getY() != y &&
                java.lang.Math.abs(square.getX() - x) == 
                java.lang.Math.abs(square.getY() - y));
    }

     /**
     * It returns text representation of Queen in Console Window.
     *
     * @return It returns a text representation.
     */
    @Override
    public String toString() {
        return "Qu";
    }
}
