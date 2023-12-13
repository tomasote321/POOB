package Dominio;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Casillas {
    protected int x;
    protected int y;
    protected boolean activated;

    public Casillas(int x, int y) {
        this.x = x;
        this.y = y;
        this.activated = false;
    }

    public abstract void applyVisualsToButton(JButton cellButton);

    public void activate() {
        this.activated = true;
    }

    public boolean isActivated() {
        return activated;
    }

    protected static class RectangleIcon implements Icon {
        protected final int width;
        protected final int height;
        protected final Color rectangleColor;
        protected final Color borderColor;

        public RectangleIcon(int width, int height, Color rectangleColor, Color borderColor) {
            this.width = width;
            this.height = height;
            this.rectangleColor = rectangleColor;
            this.borderColor = borderColor;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Dibuja el fondo del rectángulo
            g2d.setColor(rectangleColor);
            g2d.fill(new Rectangle2D.Double(x, y, width, height));

            // Dibuja el borde del rectángulo
            g2d.setColor(borderColor);
            g2d.draw(new Rectangle2D.Double(x, y, width, height));

            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return width;
        }

        @Override
        public int getIconHeight() {
            return height;
        }
    }
}
