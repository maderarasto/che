package GameBoard;

/**
 * This class represents surrounding of king. It is created as result of king's safety.
 * It contains number of king's attackers and square of last attacker.
 *
 * @author Rastislav Maděra
 */
public class KingsAroundInfo {
    
    /**
     * It represents number of king's attackers
     */
    private final int count;
    
    /**
     * It represents' a square of last attacker
     */
    private final Square attacker;

    /**
     * Class constructor. It creates a result of king's safety based
     * on number of attackers and last attacker
     * 
     * @param count It specifies number of attackers.
     * @param attackerSquare It specifies square of last attacker.
     */
    public KingsAroundInfo(int count, Square attackerSquare) {
        this.count = count;
        this.attacker = attackerSquare;
    }
    
    /**
     * It gets number of king's attackers.
     * 
     * @return It returns number of king's attackers.
     */
    public int getCount() {
        return count;
    }
    
    /**
     * It gets last attacker.
     * 
     * @return It returns square of last attacker.
     */
    public Square getAttacker() {
        return attacker;
    }
}
