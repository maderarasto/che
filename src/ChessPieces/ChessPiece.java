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
public abstract class ChessPiece {
    
    protected int x;
    protected int y;
    
    public ChessPiece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
            
    public int getY() {
        return y;
    }
    
    public abstract boolean MakeMove();
    
    @Override
    public abstract String toString();
}
