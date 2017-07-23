/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import java.util.ArrayList;
import ChessPieces.*;
import Enums.*;

/**
 *
 * @author rasto
 */
public class Player {
    
    private int kingX;
    private int kingY;
    private int points;
    private int numberOfPieces;
    private boolean checkmate;
    
    private ArrayList<ChessPiece> pieces;
    
    private final Color color;
    
    public Player(Color color) {
        this.color = color;
        this.points = 0;
        this.numberOfPieces = 16;
        this.kingX = HighPieceColumn.KING.getIndex();
        this.kingY = color == Color.WHITE ? PieceRow.WHITE_HIGH_PIECE.getIndex() 
                : PieceRow.BLACK_HIGH_PIECE.getIndex();
        this.checkmate = false;
        this.pieces = new ArrayList<>();
        
        initPieces();
    }
    
    private void initPieces() {
        int rowPawns = color.isWhite() ? PieceRow.WHITE_PAWN_PIECE.getIndex() 
                : PieceRow.BLACK_PAWN_PIECE.getIndex();
        
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, rowPawns, color));
        }
        
        pieces.add(new Rook(true, color));
        pieces.add(new Knight(true, color));
        pieces.add(new Bishop(true, color));
        pieces.add(new Queen(color));
        pieces.add(new King(color));
        pieces.add(new Bishop(false, color));
        pieces.add(new Knight(false, color));
        pieces.add(new Rook(false, color));
    }
    
    public int getNumberOfPieces() {
        return numberOfPieces;
    }
    
    public ChessPiece getPieceFromList(int index) {
        return pieces.get(index);
    }
    
    public ChessPiece getPieceAtPosition(int x, int y) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        
        return null;
    }
    
    public void removePiece(int x, int y) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                pieces.remove(piece);
                numberOfPieces--;
            }
        }
    }
    
    public boolean findPieceForValidMove(Square target) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).IsValidMove(target)) {
                return true;
            }
        }
        
        return false;
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
    
    public void IncreasePoints(ChessPiece piece) {
        points += piece.getValue();
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean getCheckmate() {
        return checkmate;
    }
    
    public void setCheckmate(boolean check) {
        checkmate = check;
    }
    
    public void movePiece(ChessPiece piece, Square square) {
        piece.setX(square.getX());
        piece.setY(square.getY());
    }
    
    @Override
    public String toString() {
        return String.format("Player %d: %d", color.isWhite() ? 1 : 2, 
                points);
    }
}
