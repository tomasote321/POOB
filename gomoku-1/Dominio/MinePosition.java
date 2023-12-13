package Dominio;

import javax.swing.*;
import java.awt.*;

public class MinePosition extends Casillas {
    public MinePosition(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyVisualsToButton(JButton cellButton) {
        if (activated) {
            cellButton.setContentAreaFilled(false);
            Icon rectangleIcon = new RectangleIcon(20, 20, Color.RED, Color.BLACK);
            cellButton.setIcon(rectangleIcon);
        } else {
            cellButton.setContentAreaFilled(false);
            Icon rectangleIcon = new RectangleIcon(20, 20, Color.GRAY, Color.BLACK);
            cellButton.setIcon(rectangleIcon);
        }
    }
}
