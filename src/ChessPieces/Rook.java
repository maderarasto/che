/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

/**
 *
 * @author Rastislav Maděra
 */
public class Rook extends ChessPiece {

    public Rook(boolean isLeft) {
        super(isLeft ? Locations.LEFT_ROOK.ordinal() : 
                Locations.RIGHT_ROOK.ordinal()
        );
    }
    
    @Override
    public boolean IsValidMove(int toX, int toY) {
        if ((toY > y || toY < y) && toX == x) {
            return true;
        } else if ((toX > x || toX < x) && toY == y) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Ro";
    }
    
}
