/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessPieces;

/**
 *
 * @author Rastislav Maděra
 */
public enum Locations {
    
    LEFT_ROOK(0),      
    LEFT_BISHOP(1),    
    LEFT_KNIGHT(2),    
    QUEEN(3),          
    KING(4),           
    RIGHT_KNIGHT(5),   
    RIGHT_BISHOP(6),   
    RIGHT_ROOK(7);     
    
    private final int index;
    
    private Locations(int index) {
        this.index = index;
    }
}
