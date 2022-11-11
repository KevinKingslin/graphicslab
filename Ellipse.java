import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Ellipse extends Applet implements MouseWheelListener {
    ArrayList<Circle> Circle = new ArrayList<Circle>();
    int originX, originY;
    int height, width;
    int unit = 10;

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
        midptellipse(g, 10, 15, 0, 0);
    }

    public void plotPoint(Graphics g, int x, int y, Color C) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;

        g.setColor(C);

        g.fillRect(originX + (x * unit) - unit / 8, originY - (y * unit) - unit / 8, unit, unit);
    }

    public void midptellipse(Graphics g, int rx, int ry, int xc, int yc) {
        int dx, dy, d1, d2, x, y;
        x = 0;
        y = ry;

        d1 = (ry * ry) - (rx * rx * ry) + (rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        // For region 1
        while (dx < dy) {

            // Print points based on 4-way symmetry
            plotPoint(g, x + xc, y + yc, Color.black);
            plotPoint(g, -x + xc, y + yc, Color.black);
            plotPoint(g, x + xc, -y + yc, Color.black);
            plotPoint(g, -x + xc, -y + yc, Color.black);

            // Checking and updating value of
            // decision parameter based on algorithm
            if (d1 < 0) {
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            } else {
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Decision parameter of region 2
        d2 = ((ry * ry) * ((x) * (x)))
                + ((rx * rx) * ((y - 1) * (y - 1)))
                - (rx * rx * ry * ry);

        // Plotting points of region 2
        while (y >= 0) {

            // printing points based on 4-way symmetry
            plotPoint(g, x + xc, y + yc, Color.black);
            plotPoint(g, -x + xc, y + yc, Color.black);
            plotPoint(g, x + xc, -y + yc, Color.black);
            plotPoint(g, -x + xc, -y + yc, Color.black);

            // Checking and updating parameter
            // value based on algorithm
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
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

    public void mouseWheelMoved(MouseWheelEvent e) {
        int z = e.getWheelRotation();
        if (unit + z >= 1 && unit + z <= 300) {
            unit -= z;
            repaint();
        }
    }
}
