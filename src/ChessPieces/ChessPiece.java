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
    
    public static final int HIGH_PIECES_POSITION = 0;
    public static final int PAWNS_POSITION = 1;
    
    protected int x;
    protected int y;
    
    public ChessPiece(int x) {
        this.x = x;
        this.y = HIGH_PIECES_POSITION;
    }
    
    public ChessPiece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
            
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public abstract boolean IsValidMove(int toX, int toY);
    
    @Override
    public abstract String toString();
}
