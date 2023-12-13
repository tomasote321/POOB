package Dominio;
import javax.swing.*;
import java.awt.*;

public class NormalStone extends Stone {
    public NormalStone() {
        super(Color.WHITE, 20, "Normal");
    }

    @Override
    public void applyVisualsToButton(JButton cellButton) {
        cellButton.setContentAreaFilled(false);
        cellButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Icon circleIcon = new CircleIcon(size, color, cellButton.getBackground());
        cellButton.setIcon(circleIcon);
    }
    
    @Override
    public boolean isOwnStone() {
        return true;  // La piedra normal siempre es propia
    }
}