package GameBoard;

import ChessPieces.*;
import Enums.Color;

/**
 * This class represents chessboard that contains 64 squares. Squares are devided
 * into 8 squares in 8 rows. It contains methods
 * for layouting pieces, checking state of checkmate and so on.
 *
 * @author Rastislav Maděra
 */
public class GameBoard {
    
    
    
    public  static final int SIDE_SIZE = 8;
    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;
    private static final int COORD_X = 0;
    private static final int COORD_Y = 1;
    
    /**
     * It represents collection of Squares. It is gameboard.
     */
    private Square[][] gameBoard;
    
    /**
     * It represents 2 players.
     */
    private final Player[] players;
    
    /**
     * It represents index of player who is just now on the move.
     */
    private int actualPlayer;
    
    /**
     * Class constructor. It initializes gameboard, players and then layout pieces.
     */
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
    
    /**
     * It layouts player's pieces on the board.
     * 
     * @param player It specifies the player.
     */
    private void layoutPieces(Player player) {
        for (int i = 0; i < player.getNumberOfPieces(); i++) {
            ChessPiece piece = player.getPieceFromList(i);
            gameBoard[piece.getY()][piece.getX()].setPiece(piece);
        }
    }  
    
    /** 
     * It validates if the actual player picked his own piece.
     * 
     * @param x It represents horizontal coordinate in the board.
     * @param y It represents vertical coordinate in the board.
     * @return It returns if the selection is correct.
     */
    public boolean isValidSelection(int x, int y) {
        return gameBoard[y][x].isThereAnyPiece() && 
                gameBoard[y][x].getPiece().getColor() == players[actualPlayer].getColor();
    }    
    
    /**
     * It gets player who is just now on the move.
     * 
     * @return 
     */
    public Player getActualPlayer() {
        return players[actualPlayer];
    }
    
    /**
     * It gets a next player.
     * 
     * @return 
     */
    public Player getNextPlayer() {
        return players[(actualPlayer+1)%2];
    }
    
    /**
     * <b>It makes validation of move and executes the move.</b>
     * 
     * <p>
     *   First it checks that it would be a valid move, if there isn't any obstacle and 
     * then tries to make the move. After that, it checks if the king is safe and if isn't 
     * then restore board to previous state. And finally pass the turn to next player.
     * </p>
     * 
     * @param x It specifies horizontal coordinate of picked piece.
     * @param y It specifies vertical coordinate of picked piece.
     * @param toX It specifies horizontal coordinate of selected target.
     * @param toY It specifies vertical coordinate of selected target
     * @throws Exception It throws 2 exceptions
     */
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
    
    /**
     * It checks if between two squares are any blocking chess piece.
     * 
     * First it gets distance between two squares and directions of the move.
     * Then it searchs if there is any blocking piece. If it finds then return true.
     * 
     * @param square It specifies a square of contolled piece.
     * @param target It specifies target square.
     * @return It returns if there is any blocking piece.
     */
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
    
    /**
     * It decides about checkmate.
     * Checkmate conditions:
     *   If the king have check
     *   If the king can't escape from the check
     *   If the next player can't take attacker 
     *     (does not apply if attackers are more than one)
     *   If the next player can't block attacker 
     *     (does not apply if attacker are knight)
     * 
     * @return It returns actual player, if he has checkmate, else returns null.
     */
    public Player CheckmateDecision() {
        KingsAroundInfo kingInfo = checkKingSafety();
        int kingX = players[actualPlayer].getKingX();
        int kingY = players[actualPlayer].getKingY();
        
        if (kingInfo.getCount() > 0 ) {
            
            if (!isThereAnySafeMove()) {
                if (kingInfo.getCount() <= 1) {
                    if (!players[actualPlayer].findPieceForValidMove(kingInfo.getAttacker())) {
                        if (!kingInfo.getAttacker().IsTherePiece("Knight")) {
                            if (!canPlayerBlockAttacker(kingInfo.getAttacker(), gameBoard[kingY][kingX])) {
                                return players[actualPlayer];
                            }
                            
                            return null;
                        }
                        
                        return players[actualPlayer];
                    }
                    
                    return players[actualPlayer];
                }
                
                return players[actualPlayer];
            }
            
            return null;
        }
        
        return null;
    }
    
    /**
     * It checks if the actual player can block king's attacker.
     * 
     * It iterates through gap between attacker and king, in each loop cycle
     * it tries to find player's piece that could block the attacker.
     * 
     * @param attacker It specifies a square of attacker.
     * @param target It specifies a square of king.
     * @return It returns if is possible blocking attacker.
     */
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
    
    /**
     * It gets direction for specific coordinate based on made movement.
     * 
     * @param attacker It specifies a square of the piece.
     * @param target It specifies a square of player's target.
     * @param coordinate It specifies coordinate for which is returned result.
     * @return It returns direction of specific coordinate.
     */
    private int getMoveDirection(Square attacker, Square target, int coordinate){
        if (coordinate < 0 || coordinate > 1) {
            // TODO: Throw System Exception
        }
        
        int attackerCoord = coordinate == COORD_X ? attacker.getX() : attacker.getY();
        int targetCoord = coordinate == COORD_X ? target.getX() : target.getY();
        
        return attackerCoord < targetCoord ? 1 : attackerCoord > targetCoord ? -1 : 0;
    }
    
    
    /**
     * It checks if king does have any possible escape move from chess.
     * 
     * It iterates through king's surrounding and tries to move king
     * on the new temporary position. If it's possible, then it tries to check
     * if there king would be safe.
     * At the end of the move it restores a default position of king and 
     * it returns result.
     * 
     * @return It returns if king have any safe escape move.
     */
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
    
    /**
     * It checks if the king is safe and returns how many pieces are occupying king
     * and last king's attacker.
     * 
     * It iterates through list of the actual oponent's pieces and it tries 
     * to validate moves on the king position. 
     * 
     * @return It returns object that contains result about king's surroundings.
     */
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
    }
     
    /**
     * It prints game board of actual turn.
     */
    public void printGame() {
        System.out.printf("%s: %d\n", players[PLAYER_2], players[PLAYER_2].getPoints());
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
        System.out.printf("%s: %d\n", players[PLAYER_1], players[PLAYER_1].getPoints());
        System.out.println();
    }
    
}
