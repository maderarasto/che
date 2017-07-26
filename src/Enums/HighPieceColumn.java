package Enums;

/**
 * This enum represents horizontal coordination of chess pieces. It contains 
 * list of horizontal indexes and method to get their integer constant.
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
