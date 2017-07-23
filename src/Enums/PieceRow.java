/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author rasto
 */
public enum PieceRow {
    
    WHITE_HIGH_PIECE(0),
    WHITE_PAWN_PIECE(1),
    BLACK_PAWN_PIECE(6),
    BLACK_HIGH_PIECE(7);
    
    private final int index;
    
    private PieceRow(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
}
