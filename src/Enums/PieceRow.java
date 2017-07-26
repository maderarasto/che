package Enums;

/**
 * This enum represents vertical coordinates of chess pieces. It contains 
 * list of vertical indexes and method to get their integer constant.
 *
 * @author Rastislav Maděra
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
