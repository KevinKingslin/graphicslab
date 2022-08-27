package packages;

import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class Bresenham extends Applet {
	private Integer unit = 40;

	public Bresenham() {
		this.setSize(900, 900);
	}

	public void PlotPoint(int x, int y, Graphics g, Color C) {
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;

		g.setColor(C);

		int PointWidth = unit / 4;
		g.fillOval(originX + (x * unit) - PointWidth / 2, originY - (y * unit) - PointWidth / 2, PointWidth,
				PointWidth);
	}

    public void PlotLine(Point P1, Point P2, Graphics g, Color PointColor){
        int x, y, dx, dy;

		dx = P2.x - P1.x;
		dy = P2.y - P1.y;

		x = P1.x;
		y = P1.y;

		double m;
		if (P2.x == P1.x)
			m = 1;
		else
			m = Math.abs((P2.y - P1.y) / (P2.x - P1.x));

		if (m < 1) {
			int p = 2 * dy - dx;
			while (x <= P2.x) {
				if (p >= 0) {
					PlotPoint(x, y, g, PointColor);
					y += 1;
					p = p + 2 * dy - 2 * dx;
				} else {
					PlotPoint(x, y, g, PointColor);
					p = p + 2 * dy;
				}
				x += 1;
			}
		} else {
			int p = 2 * dx - dy;
			while (y <= P2.y) {
				if (p >= 0) {
					PlotPoint(x, y, g, PointColor);
					x += 1;
					p = p + 2 * dx - 2 * dy;
				} else {
					PlotPoint(x, y, g, PointColor);
					p = p + 2 * dx;
				}
				y += 1;
			}
		}
    }
}
