package Dominio;
import javax.swing.*;
import java.awt.*;

public class PesadaStone extends Stone {
    public PesadaStone() {
        super(Color.GRAY, 20, "Pesada");
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
        return true;  // La piedra pesada siempre es propia
    }
}