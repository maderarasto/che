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
public class King extends ChessPiece {

    public King(Color color) {
        super(PieceIndex.KING.getIndex(), color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (color != square.getPiece().getColor()) {
            if (square.getX() == x - 1 && square.getY() == y + 1) {
                return true;
            } else if (square.getX() == x && square.getY() == y + 1) {
                return true;
            } else if (square.getX() == x + 1 && square.getY() == y + 1) {
                return true;
            } else if (square.getX() == x + 1 && square.getY() == y) {
                return true;
            } else if (square.getX() == x + 1 && square.getY() == y - 1) {
                return true;
            } else if (square.getX() == x && square.getY() == y - 1) {
                return true;
            } else if (square.getX() == x - 1 && square.getY() == y - 1) {
                return true;
            } else if (square.getX() == x - 1 && square.getY() == y) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Ki";
    }
    
}
