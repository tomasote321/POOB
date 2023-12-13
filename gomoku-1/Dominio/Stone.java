package Dominio;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Stone {
    protected Color color;
    protected int size;  // Tamaño del círculo
    protected String type;
    protected int puntaje;

    public Stone(Color color, int size, String type) {
        this.color = color;
        this.size = size;
        this.type = type;
        this.puntaje = 0;
    }

    public abstract void applyVisualsToButton(JButton cellButton);
    
        public static Stone createNormalStone() {
        return new NormalStone();
    }

    public static Stone createTemporalStone() {
        return new TemporalStone();
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void incrementarPuntaje(int puntos) {
        puntaje += puntos;
    }
    
    public boolean isEnemyStone() {
        return false;  // Todas las piedras por defecto no son piedras enemigas
    }

    public boolean isOwnStone() {
        return false;  // Todas las piedras por defecto no son piedras propias
    }

    public boolean isSpecialStone() {
        return false;  // Todas las piedras por defecto no son piedras especiales
    }

    protected static class CircleIcon implements Icon {
        protected final int size;
        protected final Color circleColor;
        protected final Color backgroundColor;

        public CircleIcon(int size, Color circleColor, Color backgroundColor) {
            this.size = size;
            this.circleColor = circleColor;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Dibuja el fondo transparente
            g2d.setColor(backgroundColor);
            g2d.fillRect(x, y, size, size);

            // Dibuja un círculo dentro del área del ícono
            g2d.setColor(circleColor);
            g2d.fill(new Ellipse2D.Double(x, y, size, size));

            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }
}


