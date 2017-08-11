package GameBoard;

import java.util.ArrayList;
import ChessPieces.*;
import Enums.*;

/**
 * This class represents a player. It contains location of his king,
 * list of actual pieces.
 * 
 * @author Rastislav Maděra
 */
public class Player {
    
    /**
     * It represents horizontal coordinate of king
     */
    private int kingX;
    
    /**
     * * It represents vertical coordinate of king
     */
    private int kingY;
    
    /**
     * It represents points. Poaint can be received for taking oponent's pieces.
     */
    private int points;
    
    /**
     * It represents actual number of pieces.
     */
    private int numberOfPieces;
    private boolean checkmate;
    
    /**
     * It represents list of actual pieces.
     */
    private ArrayList<ChessPiece> pieces;
    
    /**
     * It represents color of player's chess pieces.
     */
    private final Color color;
    
    /**
     * <b>Class constructor.</b>
     * <p>
     *   It creates a player based on color of his pieces and it initializes his pieces. 
     * </p>
     * @param color 
     */
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
    
    /**
     * It initializes player's pieces based on player's color.
     */
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
    
    /**
     * It gets number of actual pieces.
     * 
     * @return It return integer number of pieces in list.
     */
    public int getNumberOfPieces() {
        return numberOfPieces;
    }
    
    /**
     * It gets a piece from the list based on index.
     * 
     * @param index It specifies index of the piece in list.
     * @return It returns piece from the list.
     */
    public ChessPiece getPieceFromList(int index) {
        return pieces.get(index);
    }
    
    /**
     * It gets piece based on position in board.
     * 
     * @param x It specifies horizontal coordinate in the board.
     * @param y It specifies vertical coordinate int he board.
     * @return It return finded piece at specific position.
     */
    public ChessPiece getPieceAtPosition(int x, int y) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        
        return null;
    }
    
    /**
     * It removes the piece from the list based on specific position.
     * 
     * @param x It specifies horizontal coordinate in the board.
     * @param y It specifies vertical coordinate in the board.
     */
    public void removePiece(int x, int y) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                pieces.remove(piece);
                numberOfPieces--;
            }
        }
    }
    
    /**
     * It tries to find piece which could make move on the specific square.
     * 
     * @param target It specifies a square of the target.
     * @return It returns if any piece can make move to the specific square.
     */
    public boolean findPieceForValidMove(Square target) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).IsValidMove(target)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * It updates both positions of king.
     * 
     * @param x It specifies a new horizontal coordinate of king.
     * @param y It specifies a new vertical coordinate of king.
     */
    public void updateKing(int x, int y) {
        kingX = x;
        kingY = y;
    }
    
    /**
     * It gets point of player.
     * 
     * @return It returns integer number of points.
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * It gets horizontal coordinate of king.
     * 
     * @return It returns horizontal index of king in the board.
     */
    public int getKingX() {
        return kingX;
    }    
    
    /**
     * It gets vertical coordinate of king.
     * 
     * @return It returns vertical index of king in the board. 
     */
    public int getKingY() {
        return kingY;
    }
    
    /**
     * It increases piace by value of taken oponent's piece.
     * 
     * @param piece It specifies a piece which was taken.
     */
    
    /**
     * It gets a color of player's pieces.
     * @return 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * It updates positions of specific piece.
     * 
     * @param piece It specifies a piece which shall be updated.
     * @param square It specifies a square, in which is the piece moved.
     */
    public void movePiece(ChessPiece piece, Square square) {
        piece.setX(square.getX());
        piece.setY(square.getY());
    }
    
    /**
     * It gets text representation of the player.
     * 
     * @return It return string representation.
     */
    @Override
    public String toString() {
        return String.format("Player %d", color.isWhite() ? 1 : 2);
    }
}
