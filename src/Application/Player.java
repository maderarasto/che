/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import ChessPieces.ChessPiece;
import Enums.Color;

/**
 *
 * @author rasto
 */
public class Player {
    
    private int points;
    private final Color color;
    
    public Player(Color color) {
        this.color = color;
        this.points = 0;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void IncreasePoints(ChessPiece piece) {
        // Increase points by value of piece
    }
    
    public Color getColor() {
        return color;
    }
    
    public void movePiece(ChessPiece piece, int toX, int toY) {
        piece.setX(toX);
        piece.setY(toY);
    }
}
