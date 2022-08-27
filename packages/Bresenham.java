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

    public void PlotLine(Integer x1, Integer y1, Integer x2, Integer y2, Graphics g, Color PointColor){
        int x, y, dx, dy;

		dx = x2 - x1;
		dy = y2 - y1;

		x = x1;
		y = y1;

		double m;
		if (x2 == x1)
			m = 1;
		else
			m = Math.abs((y2 - y1) / (x2 - x1));

		if (m < 1) {
			int p = 2 * dy - dx;
			while (x <= x2) {
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
			while (y <= y2) {
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
