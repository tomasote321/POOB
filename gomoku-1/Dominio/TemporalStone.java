package Dominio;
import javax.swing.*;
import java.awt.*;

public class TemporalStone extends Stone {
    public TemporalStone() {
        super(Color.CYAN, 20, "Temporal");
    }

    @Override
    public void applyVisualsToButton(JButton cellButton) {
        cellButton.setContentAreaFilled(false);
        cellButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Icon circleIcon = new CircleIcon(size, color, cellButton.getBackground());
        cellButton.setIcon(circleIcon);
    }
    
    @Override
    public boolean isSpecialStone() {
        return true;  // La piedra temporal siempre es especial
    }

}