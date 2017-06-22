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
public class King extends ChessPiece {

    public King(Color color) {
        super(Location.KING.ordinal(), color);
    }
    
    @Override
    public boolean IsValidMove(int toX, int toY) {
        if (toX == x - 1 && toY == y + 1) {
            return true;
        } else if (toX == x && toY == y + 1) {
            return true;
        } else if (toX == x + 1 && toY == y + 1) {
            return true;
        } else if (toX == x + 1 && toY == y) {
            return true;
        } else if (toX == x + 1 && toY == y - 1) {
            return true;
        } else if (toX == x && toY == y - 1) {
            return true;
        } else if (toX == x - 1 && toY == y - 1) {
            return true;
        } else if (toX == x - 1 && toY == y) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Ki";
    }
    
}
