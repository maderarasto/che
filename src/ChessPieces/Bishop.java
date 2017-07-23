/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import Enums.HighPieceColumn;
import GameBoard.Square;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class Bishop extends ChessPiece {

    public Bishop(boolean isLeft, Color color) {
        super(isLeft ? HighPieceColumn.LEFT_BISHOP.getIndex() : 
                HighPieceColumn.RIGHT_BISHOP.getIndex(), color);
        
    }
    
    public Bishop(int x, int y, Color color) {
        super(x, y, color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().color == color) {
            return false;
        }
        
        return  square.getX() != x && square.getY() != y && 
            java.lang.Math.abs(square.getX() - x) == java.lang.Math.abs(square.getY() - y);
    }

    @Override
    public String toString() {
        return "Bi";
    }
}