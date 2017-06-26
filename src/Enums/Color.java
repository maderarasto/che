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
public enum Color {
    
    WHITE(0),
    BLACK(1);
    
    private final int index;
    
    private Color(int index) {
        this.index = index;
    }
}
