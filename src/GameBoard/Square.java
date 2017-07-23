/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import ChessPieces.ChessPiece;
import Enums.Color;

/**
 *
 * @author rasto
 */
public class Square {
    
    private final int x;
    private final int y;
    private ChessPiece piece;
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public ChessPiece getPiece() {
        return piece;
    }
    
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
    
    public boolean isThereAnyPiece() {
        return piece != null;
    }
    
    public boolean IsTherePiece(String... pieceNames) {
        for (String strPiece : pieceNames) {
            if (piece != null && 
                piece.getClass().getSimpleName().equals(strPiece)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int getDistanceFromTarget(Square target) {
        int length = 0;
        
        if (Math.abs(x - target.getX()) == Math.abs(y - target.getY())) {
            length = Math.abs(x - target.getX());
        }
        else if (x == target.getX()) {
            length = Math.abs(y - target.getY());
        } else if (y == target.getY()) {
            length = Math.abs(x - target.getX());
        }
        
        return length;
    }
    
    @Override
    public String toString() {
        if (piece != null && piece.getColor() == Color.WHITE) {
            return "\033[30;1m" + piece + "\033[0m";
        } else if (piece != null && piece.getColor() == Color.BLACK) {
            return "\033[37;1m" + piece + "\033[0m";
        }
        
        return "  ";        
    }
}
