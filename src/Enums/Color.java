package Enums;

/**
 * This enum represents colors of chess pieces. It contains list of colors and
 * method to check if the color is white.
 *
 * @author Rastislav Maděra
 */
public enum Color {
    
    WHITE(0),
    BLACK(1);
    
    private final int index;
    
    private Color(int index) {
        this.index = index;
    }
    
    public boolean isWhite() {
        return index == 0;
    }
}
