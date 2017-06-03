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
public class Bishop extends ChessPiece {

    public Bishop(boolean isLeft) {
        super(isLeft ? Locations.LEFT_BISHOP.ordinal() :
                Locations.RIGHT_BISHOP.ordinal()
        );
    }
    
    @Override
    public boolean MakeMove() {
        return false;
    }

    @Override
    public String toString() {
        return "Bi";
    }
    
}
