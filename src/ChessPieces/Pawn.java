/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

import GameBoard.Square;
import Enums.Color;
import Enums.PieceRow;

/**
 *
 * @author Rastislav Maděra
 */
public class Pawn extends ChessPiece {

    private final int direction;
    
    public Pawn(int x, int y, Color color)
    {
        super(x, y, color);
        direction = color.isWhite() ? 1 : -1;
    }
    
    private boolean isAtInitialPos() {
        int initPos = color.isWhite() ? PieceRow.WHITE_PAWN_PIECE.getIndex() 
                : PieceRow.BLACK_PAWN_PIECE.getIndex();
        return y == initPos;
    }
    
    @Override
    public boolean IsValidMove(Square square) {
        if (square.isThereAnyPiece() && square.getPiece().getColor() == color) {
            return false;
        }
        
        return (isAtInitialPos() && square.getY() == y + 2*direction && square.getX() == x) ||
                (square.getY() == y + direction && square.getX() == x    ) ||
                (square.getX() == x + direction && square.getY() == y + 1) ||
                (square.getX() == x - direction && square.getY() == y + 1);
    }

    @Override
    public String toString() {
        return "Pa";
    } 
}
