/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class King extends ChessPiece {

    public King(Color color) {
        super(HighPieceColumn.KING.getIndex(), color);
    }
    
    public King(int x, int y, Color color) {
        super(x, y, color);
    }
    
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

    @Override
    public String toString() {
        return "Ki";
    }
    
}
