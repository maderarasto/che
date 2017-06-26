/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import Application.Square;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class Pawn extends ChessPiece {

    private int direction;
    
    public Pawn(int x, int y, Color color)
    {
        super(x, y, color);
        direction = color == Color.WHITE ? 1 : -1;
    }
    
    private boolean isAtInitialPos() {
        int initPos = color == Color.WHITE ? 1 : 6;
        return y == initPos;
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.getPiece() == null || color != square.getPiece().getColor()) {
            if (isAtInitialPos() && square.getY() == y + 2*direction && 
                    square.getX() == x) {
                return true;
            } else if (square.getY() == y + 1*direction && square.getX() == x) {
                return true;
            } else if (square.getPiece() != null && square.getPiece().getColor() != color && 
                    (square.getX() == x - 1*direction || 
                    square.getX() == x + 1*direction) && 
                    square.getY() == y + 1*direction)
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Pa";
    }
    
    
}
