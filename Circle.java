import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Circle extends Applet implements MouseWheelListener {

    ArrayList<Circle> Circle = new ArrayList<Circle>();
    int originX, originY;
    int height, width;
    int unit = 3;

    public void init() {
        setBackground(Color.blue);
        addMouseWheelListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        height = getHeight();
        width = getWidth();
        originX = (getX() + width) / 2;
        originY = (getY() + height) / 2;
        drawGrid(g);

        drawShape(g);
    }

    public void drawShape(Graphics g) {
        drawCircle(g, 100, 0, 0);
        drawCircle(g, 46, 46, 27);
        drawCircle(g, 46, -46, 27);
        drawCircle(g, 46, 0, -54);
        drawCircle(g, 8, 0, 0);
        drawCircle(g, 22, 0, 78);
        drawCircle(g, 22, 65, -38);
        drawCircle(g, 22, -65, -38);
        drawCircle(g, 10, 33, 82);
        drawCircle(g, 10, -33, 82);
        drawCircle(g, 10, -88, -14);
        drawCircle(g, 10, 88, -14);
        drawCircle(g, 10, -54, -70);
        drawCircle(g, 10, 54, -70);
        drawCircle(g, 5, 50, 80);
        drawCircle(g, 5, -50, 80);
        drawCircle(g, 6, 94, 3);
        drawCircle(g, 6, -94, 3);
        drawCircle(g, 6, 45, -83);
        drawCircle(g, 6, -45, -83);
        drawCircle(g, 4, 58, 76);
        drawCircle(g, 4, -58, 76);
        drawCircle(g, 3, 95, 14);
        drawCircle(g, 3, -95, 14);
        drawCircle(g, 3, 36, -88);
        drawCircle(g, 3, -36, -88);
        drawCircle(g, 4, 0, 50);
        drawCircle(g, 4, 42, -24);
        drawCircle(g, 4, -42, -24);
        drawCircle(g, 2, 65, 72);
        drawCircle(g, 2, -65, 72);
        drawCircle(g, 3, 95, 22);
        drawCircle(g, 3, -95, 22);
        drawCircle(g, 2, 31, -92);
        drawCircle(g, 2, -31, -92);
        drawCircle(g, 4, 23, 92);
        drawCircle(g, 4, -23, 92);
        drawCircle(g, 4, 91, -30);
        drawCircle(g, 4, -91, -30);
        drawCircle(g, 4, 69, -64);
        drawCircle(g, 4, -69, -64);
    }

    // Function to draw origin
    public void drawOriginCircle(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(originX - 5, originY - 5, 10, 10);
    }

    // Function for plotting points
    public void plotPoint(Graphics g, int x, int y, Color C) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;

        g.setColor(C);

        g.fillRect(originX + (x * unit) - unit / 8, originY - (y * unit) - unit / 8, unit, unit);
    }

    // Function to draw the Grid
    public void drawGrid(Graphics g) {
        g.setColor(Color.red);

        // Set x-axis
        g.drawLine(0, height / 2, width, height / 2);

        // Set y-axis
        g.drawLine(width / 2, 0, width / 2, height);

        g.setColor(Color.black);

        for (int count = 1; count <= 200; count++) {
            // Set horizontal lines
            g.drawLine(width / 2 + (unit * count), 0, width / 2 + (unit * count), height);
            g.drawLine(width / 2 - (unit * count), 0, width / 2 - (unit * count), height);

            // Set vertical lines
            g.drawLine(0, height / 2 + (unit * count), width, height / 2 + (unit * count));
            g.drawLine(0, height / 2 - (unit * count), width, height / 2 - (unit * count));
        }
    }

    // Function for the mousewheel
    public void mouseWheelMoved(MouseWheelEvent e) {
        int z = e.getWheelRotation();
        if (unit + z >= 1 && unit + z <= 300) {
            unit -= z;
            repaint();
        }
    }

    public void drawCircle(Graphics g, int r, int x1, int y1) {
        Circle new1 = new Circle();
        Circle.add(new1);
        int x = 0;
        int y = r;
        double p = (double) 5 / 4 - r;
        plotPoint(g, x + x1, y + y1, Color.black);
        plotPoint(g, x + x1, -y + y1, Color.black);
        plotPoint(g, y + x1, x + y1, Color.black);
        plotPoint(g, -y + x1, x + y1, Color.black);
        while (x <= y) {
            if (p < 0) {
                x = x + 1;
                p = p + 2 * x + 1;
            } else {
                x = x + 1;
                y = y - 1;
                p = p + (2 * x) + 1 - (2 * y);
            }
            plotPoint(g, x + x1, y + y1, Color.black);
            plotPoint(g, y + x1, x + y1, Color.black);
            plotPoint(g, -x + x1, y + y1, Color.black);
            plotPoint(g, -y + x1, x + y1, Color.black);
            plotPoint(g, x + x1, -y + y1, Color.black);
            plotPoint(g, y + x1, -x + y1, Color.black);
            plotPoint(g, -x + x1, -y + y1, Color.black);
            plotPoint(g, -y + x1, -x + y1, Color.black);
        }
    }
}