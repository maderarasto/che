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
public class Rook extends ChessPiece {

    public Rook(boolean isLeft, Color color) {
        super(isLeft ? PieceIndex.LEFT_ROOK.getIndex() : 
                PieceIndex.RIGHT_ROOK.getIndex(), color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.getPiece() != null && color != square.getPiece().getColor()) {
            if ((square.getY() > y || square.getY() < y) && square.getX() == x) {
                return true;
            } else if ((square.getX() > x || square.getX() < x) 
                    && square.getY() == y) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Ro";
    }
    
}
