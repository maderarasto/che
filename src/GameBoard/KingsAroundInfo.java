/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

/**
 *
 * @author rasto
 */
public class KingsAroundInfo {
        
    private final int count;
    private final Square attacker;

    public KingsAroundInfo(int count, Square attackerSquare) {
        this.count = count;
        this.attacker = attackerSquare;
    }
        
    public int getCount() {
        return count;
    }
    
    public Square getAttacker() {
        return attacker;
    }
}
