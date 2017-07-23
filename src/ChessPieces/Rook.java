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
public class Rook extends ChessPiece {

    public Rook(boolean isLeft, Color color) {
        super(isLeft ? HighPieceColumn.LEFT_ROOK.getIndex() : 
                HighPieceColumn.RIGHT_ROOK.getIndex(), color);
    }
    
    public Rook(int x, int y, Color color) {
        super(x, y, color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (square.getX()  == x && square.getY() != y) ||
                (square.getY() == y && square.getX() != x);
    }

    @Override
    public String toString() {
        return "Ro";
    }
    
}
