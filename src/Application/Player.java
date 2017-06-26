/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ChessPieces.ChessPiece;
import Enums.Color;
import Enums.PieceIndex;

/**
 *
 * @author rasto
 */
public class Player {
    
    private int kingX;
    private int kingY;
    private int points;
    
    private final Color color;
    
    public Player(Color color) {
        this.color = color;
        this.points = 0;
        this.kingX = PieceIndex.KING.getIndex();
        this.kingY = color == Color.WHITE ? 0 : 7;
    }
    
    public void updateKing(int x, int y) {
        kingX = x;
        kingY = y;
    }
    
    public int getPoints() {
        return points;
    }
    
    public int getKingX() {
        return kingX;
    }    
    
    public int getKingY() {
        return kingY;
    }
    
    public void takeOponentPiece(Square square) {
        ChessPiece piece = square.getPiece();
        square.setPiece(null);
        points += piece.getValue();
    }
    
    public Color getColor() {
        return color;
    }
    
    public void movePiece(ChessPiece piece, Square square) {
        piece.setX(square.getX());
        piece.setY(square.getY());
    }
    
    @Override
    public String toString() {
        return String.format("Player %d: %d", color == Color.WHITE ? 1 : 2, 
                points);
    }
}
