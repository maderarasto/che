package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 * This class represents chess piece named Kingt. It contains methods
 * for validation a move and representation in window.
 * 
 * @author Rastislav Maděra
 */
public class King extends ChessPiece {

    /**
     * Class contructor. It creates a chess piece of type king on the position based 
     * on color.
     * 
     * @param color It specifies color of piece.
     */
    public King(Color color) {
        super(HighPieceColumn.KING.getIndex(), color);
    }
    
    /**
     * Class contructor. It creates a chess piece of type Bishop on the specific 
     * position based on color.
     * 
     * @param x It specifies x position.
     * @param y It specifies y position.
     * @param color It specifies color of piece
     */
    public King(int x, int y, Color color) {
        super(x, y, color);
    }
    
    /**
     * It checks if the piece has a valid move based on target square in board.
     * 
     * First It's checks if there is any chess piece and if there is then checks if
     * that piece doesn't have the same color
     * Finally it checks if the coordinates are correct. 
     * For king it checks if the target square is placed one square next 
     * in each direction.
.    * 
     * @param square It specifies target square.
     * @return It returns if the move is valid.
     */
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (square.getX()  == x - 1 && square.getY() == y + 1) ||
                (square.getX() == x     && square.getY() == y + 1) ||
                (square.getX() == x + 1 && square.getY() == y + 1) || 
                (square.getX() == x + 1 && square.getY() == y    ) || 
                (square.getX() == x + 1 && square.getY() == y - 1) ||
                (square.getX() == x     && square.getY() == y - 1) ||
                (square.getX() == x - 1 && square.getY() == y - 1) ||
                (square.getX() == x - 1 && square.getY() == y    );
    }
    
    /**
     * It returns text representation of King in Console Window.
     * 
     * @return It returns a text representation.
     */
    @Override
    public String toString() {
        return "Ki";
    }
    
}
