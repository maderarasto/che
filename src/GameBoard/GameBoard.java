/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import ChessPieces.*;
import Enums.Color;

/**
 *
 * @author Rastislav Maděra
 */
public class GameBoard {
    
    
    
    public  static final int SIDE_SIZE  = 8;
    private static final int PLAYER_1   = 0;
    private static final int PLAYER_2   = 1;
    private static final int COORD_X = 0;
    private static final int COORD_Y = 1;
    
    private Square[][] gameBoard;
    private final Player[] players;
    
    private int actualPlayer;
    
    public GameBoard() {
        gameBoard = new Square[SIDE_SIZE][SIDE_SIZE];
        players = new Player[2];
        players[PLAYER_1] = new Player(Color.WHITE);
        players[PLAYER_2] = new Player(Color.BLACK);
        actualPlayer = PLAYER_1;
        
        for (int i = 0; i < SIDE_SIZE; i++) {
            for (int j = 0; j < SIDE_SIZE; j++) {
                gameBoard[i][j] = new Square(j, i);
            }
        }
        
        layoutPieces(players[PLAYER_1]);
        layoutPieces(players[PLAYER_2]);
    }
    
    private void layoutPieces(Player player) {
        for (int i = 0; i < player.getNumberOfPieces(); i++) {
            ChessPiece piece = player.getPieceFromList(i);
            gameBoard[piece.getY()][piece.getX()].setPiece(piece);
        }
    }  
    
    public boolean isValidSelection(int x, int y) {
        return gameBoard[y][x].isThereAnyPiece() && 
                gameBoard[y][x].getPiece().getColor() == players[actualPlayer].getColor();
    }    
    
    public Player getActualPlayer() {
        return players[actualPlayer];
    }
    
    public void makePlayerMove( int x, int y, int toX, int toY) 
        throws Exception {
        
        ChessPiece piece;
        boolean takingPiece = false;
        
        if (!gameBoard[y][x].getPiece().IsValidMove(gameBoard[toY][toX])) {
            throw new Exception("Invalid target");
        }
        if (gameBoard[y][x].IsTherePiece("Bishop", "Queen", "Rook") &&
                isThereObstacle(gameBoard[y][x], gameBoard[toY][toX])) {
            throw new Exception("You can't jump over the any piece!");
        }
        
        
        piece = gameBoard[toY][toX].getPiece();       
        gameBoard[toY][toX].setPiece(gameBoard[y][x].getPiece());   // Move a piece to new square
        gameBoard[y][x].setPiece(null); // Clear piece from previous square
        if (gameBoard[toY][toX].IsTherePiece("King")) {
                players[actualPlayer].updateKing(toX, toY);
        }
        
        
        if (checkKingSafety().getCount() > 0) {
            gameBoard[y][x].setPiece(gameBoard[toY][toX].getPiece());
            gameBoard[toY][toX].setPiece(piece);
            if (gameBoard[y][x].IsTherePiece("King")) {
                players[actualPlayer].updateKing(x, y);
            }
            
            throw new Exception("King would not be safe!");
        }
        
         
        
        if (takingPiece) {
            players[actualPlayer].IncreasePoints(piece);
        }
        
        players[actualPlayer].movePiece(gameBoard[toY][toX].getPiece(), 
                gameBoard[toY][toX]);
        actualPlayer = (++actualPlayer) % 2;    // Passing moves between 2 Players
        
    }
    
    public boolean isThereObstacle(Square square, Square target) {
        int length = square.getDistanceFromTarget(target) - 1;
        int dx = getMoveDirection(square, target, COORD_X);
        int dy = getMoveDirection(square, target, COORD_Y);
        
        for (int i = 0; i < length; i++) {
            int tempX = square.getX() + dx*(i + 1);
            int tempY = square.getY() + dy*(i + 1);
            
            if (gameBoard[tempY][tempX].isThereAnyPiece()) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean CheckmateDecision() {
        KingsAroundInfo kingInfo = checkKingSafety();
        int kingX = players[actualPlayer].getKingX();
        int kingY = players[actualPlayer].getKingY();
        
        if (kingInfo.getCount() > 0 ) {
            //players[actualPlayer].setCheckmate(true);
            
            if (!isThereAnySafeMove()) {
                if (kingInfo.getCount() <= 1) {
                    if (!players[actualPlayer].findPieceForValidMove(kingInfo.getAttacker())) {
                        if (!kingInfo.getAttacker().IsTherePiece("Knight")) {
                            if (!canPlayerBlockAttacker(kingInfo.getAttacker(), gameBoard[kingY][kingX])) {
                                return true;
                            }
                            
                            return false;
                        }
                        
                        return true;
                    }
                    
                    return true;
                }
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
    
    
    
    private boolean canPlayerBlockAttacker(Square attacker, Square target){
        int length = attacker.getDistanceFromTarget(target) - 1;    // Because the last piece is king
        int dX = getMoveDirection(attacker, target, COORD_X);
        int dY = getMoveDirection(attacker, target, COORD_Y);
        
        /* TODO: check player's pieces for valid move */
        for (int i = 0; i < length; i++) {
            if (players[actualPlayer].findPieceForValidMove(
                    gameBoard[attacker.getY() + dY*(i + 1)][attacker.getX() + dX*(i + 1)])) {
                return true;
            }
        }
        
        
        return false;
    }
    
    private int getMoveDirection(Square attacker, Square target, int coordinate){
        if (coordinate < 0 || coordinate > 1) {
            // TODO: Throw System Exception
        }
        
        int attackerCoord = coordinate == COORD_X ? attacker.getX() : attacker.getY();
        int targetCoord = coordinate == COORD_X ? target.getX() : target.getY();
        
        return attackerCoord < targetCoord ? 1 : attackerCoord > targetCoord ? -1 : 0;
    }
    
    
    
    private boolean isThereAnySafeMove() {
        int actualKingX = players[actualPlayer].getKingX();
        int actualKingY = players[actualPlayer].getKingY();
        boolean canBeSaved = false;
        
        for (int i = 1; i >= -1; i--) {
            for (int j = -1; j <= 1; j++) {
                int tempKingX = actualKingX + j;
                int tempKingY = actualKingY + i;
                ChessPiece tempPiece;
                
                if (tempKingX >= 0 && tempKingX <= 7 &&
                        tempKingY >=0 && tempKingY <= 7 && 
                        gameBoard[actualKingY][actualKingX].getPiece().IsValidMove(gameBoard[tempKingY][tempKingX])) {
                    tempPiece = gameBoard[tempKingY][tempKingX].getPiece();     // Store a piece to temporary variable
                    gameBoard[tempKingY][tempKingX].setPiece(
                            gameBoard[actualKingY][actualKingX].getPiece());
                    gameBoard[actualKingY][actualKingX].setPiece(null); 
                    players[actualPlayer].updateKing(tempKingX, tempKingY);
                    
                    if (checkKingSafety().getCount() == 0) {
                         
                        canBeSaved = true;
                    }
                    
                    gameBoard[actualKingY][actualKingX].setPiece(gameBoard[tempKingY][tempKingX].getPiece());
                    gameBoard[tempKingY][tempKingX].setPiece(tempPiece);
                    players[actualPlayer].updateKing(actualKingX, actualKingY);
                    
                    
                }
            }
        }
        
        return canBeSaved;
    }
    
    private KingsAroundInfo checkKingSafety() {      
        int numberOfAttacks = 0;
        int kingY = players[actualPlayer].getKingY();
        int kingX = players[actualPlayer].getKingX();
        Square pieceSquare = null;
        Player attacker = players[(actualPlayer + 1) % 2];
        
        
        for (int i = 0; i < attacker.getNumberOfPieces(); i++) {
            ChessPiece piece = attacker.getPieceFromList(i);
            
            if (piece.IsValidMove(gameBoard[kingY][kingX]) && 
                    (gameBoard[piece.getY()][piece.getX()].IsTherePiece("Knight") ||
                    !isThereObstacle(gameBoard[piece.getY()][piece.getX()], gameBoard[kingY][kingX]))) {
                numberOfAttacks++;
                pieceSquare = gameBoard[piece.getY()][piece.getX()];
            }
        }
        
        return new KingsAroundInfo(numberOfAttacks, pieceSquare);
        /*
        for (int i = 1; i >= -1; i--) {
            for (int j = -1; j <= 1; j++) {
                int count = 0;
                
                int row = kingY + i*(count + 1);
                int col = kingX + j*(count + 1);
                boolean pieceFound = false;
                
                while (!pieceFound && row >= 0 && row <= 7 && col >= 0 && col <= 7) {
                    if (gameBoard[row][col].isThereAnyPiece() && 
                        gameBoard[row][col].getPiece()
                            .IsValidMove(gameBoard[kingY][kingX])) { // IF PIECE IS ENEMY AND WOULD ATTACK KING
                        enemyPieceSquare = gameBoard[row][col];
                        pieceFound = true;
                        numberOfAttacks++;
                    } else if (gameBoard[row][col].isThereAnyPiece()) {
                        pieceFound = true;
                    }
                    
                    count++;
                    row = kingY + i*(count + 1);
                    col = kingX + j*(count + 1);
                }
            }
        }
        
        return new KingsAroundInfo(numberOfAttacks, enemyPieceSquare);*/
    }
     
    
    public void printGame() {
        System.out.println(players[PLAYER_2]);
        System.out.println("  | A   B   C   D   E   F   G   H |  ");
        System.out.println("--+---+---+---+---+---+---+---+---+--");
        
        for (int i = SIDE_SIZE; i > 0; i--) {
            System.out.format("%d |", i);
            
            for (int j = 0; j < SIDE_SIZE; j++) {
                System.out.format("%s%s ", j == 0 ? "" : "|", gameBoard[i-1][j]);
                
            }
            
            System.out.format("| %d\n", i);
            System.out.format("%s", i == 1 ? "" : 
                    "  |---+---+---+---+---+---+---+---|  \n");
        }
        
        System.out.println("--+---+---+---+---+---+---+---+---+--");
        System.out.println("  | A   B   C   D   E   F   G   H |  ");
        System.out.println(players[PLAYER_1]);
        System.out.println();
    }
    
}
