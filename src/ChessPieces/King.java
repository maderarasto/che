/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

/**
 *
 * @author rasto
 */
public class King extends ChessPiece {

    public King(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean MakeMove() {
        return false;
    }

    @Override
    public String toString() {
        return "Ki";
    }
    
}
