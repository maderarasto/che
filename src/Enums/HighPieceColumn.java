/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author Rastislav Maděra
 */
public enum HighPieceColumn {
    
    LEFT_ROOK(0),      
    LEFT_KNIGHT(1),    
    LEFT_BISHOP(2),    
    QUEEN(3),          
    KING(4),           
    RIGHT_BISHOP(5),   
    RIGHT_KNIGHT(6),
    RIGHT_ROOK(7);     
    
    private final int index;
    
    private HighPieceColumn(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return  index;
    }
}
