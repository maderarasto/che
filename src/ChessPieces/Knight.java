/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class Knight extends ChessPiece {

    public Knight(boolean isLeft, Color color) {
        super(isLeft ? Location.LEFT_KNIGHT.ordinal() :
                Location.RIGHT_KNIGHT.ordinal(), color);
    }
    
    @Override
    public boolean IsValidMove(int toX, int toY) {
        if ((toX == x - 2 || toX == x + 2) && (toY == y + 1 || toY == y - 1)) {
            return true;
        } else if ((toX == x - 1 || toX == x + 1) && (toY == y + 2 || toY == y - 2)) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Kn";
    }
    
}
