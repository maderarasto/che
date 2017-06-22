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
public class Pawn extends ChessPiece {

    public Pawn(int x, int y, Color color)
    {
        super(x, y, color);
    }
    
    private boolean isAtInitialPos() {
        return y == ChessPiece.PAWNS_POSITION;
    }
    
    @Override
    public boolean IsValidMove(int toX, int toY) {
        if (isAtInitialPos() && toY == y + 2 && toX == x) {
            return true;
        } else if (toY == y + 1 && toX == x) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Pa";
    }
    
    
}
