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
public class Knight extends ChessPiece {

    public Knight(boolean isLeft) {
        super(isLeft ? Locations.LEFT_KNIGHT.ordinal() :
                Locations.RIGHT_KNIGHT.ordinal()
        );
    }
    
    @Override
    public boolean IsValidMove(int toX, int toY) {
        return false;
    }

    @Override
    public String toString() {
        return "Kn";
    }
    
}
