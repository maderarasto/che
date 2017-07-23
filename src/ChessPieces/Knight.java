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
public class Knight extends ChessPiece {

    public Knight(boolean isLeft, Color color) {
        super(isLeft ? HighPieceColumn.LEFT_KNIGHT.getIndex() :
                HighPieceColumn.RIGHT_KNIGHT.getIndex(), color);
    }
    
    public Knight(int x, int y, Color color) {
        super(x, y, color);
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (square.getX()  == x - 2 && square.getY()  == y + 1) ||
                (square.getX() == x - 2 && square.getY() == y - 1) ||
                (square.getX() == x + 2 && square.getY() == y + 1) ||
                (square.getX() == x + 2 && square.getY() == y - 1) ||
                (square.getX() == x - 1 && square.getY() == y + 2) ||
                (square.getX() == x - 1 && square.getY() == y - 2) ||
                (square.getX() == x + 1 && square.getY() == y + 2) ||
                (square.getX() == x + 1 && square.getY() == y - 2);
    }

    @Override
    public String toString() {
        return "Kn";
    }
    
}
