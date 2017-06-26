/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import Enums.PieceIndex;
import Application.Square;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class Knight extends ChessPiece {

    public Knight(boolean isLeft, Color color) {
        super(isLeft ? PieceIndex.LEFT_KNIGHT.getIndex() :
                PieceIndex.RIGHT_KNIGHT.getIndex(), color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.getPiece() == null || color != square.getPiece().getColor()) {
            if ((square.getX() == x - 2 || square.getX() == x + 2) && 
                    (square.getY() == y + 1 || square.getY() == y - 1)) {
                return true;
            } else if ((square.getX() == x - 1 || square.getX() == x + 1) && 
                    (square.getY() == y + 2 || square.getY() == y - 2)) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Kn";
    }
    
}
