package packages;

import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class Midpoint extends Applet {
	private Integer unit = 40;

	public Midpoint() {
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

	public void PlotLine(Integer x1, Integer y1, Integer x2, Integer y2, Graphics g, Color PointColor) {
		int dx, dy, d, x, y;

		dx = x2 - x1;
		dy = y2 - y1;

		x = x1;
		y = y1;

		// Slope less than or equal to 1
		if (dy <= dx) {
			d = dy - (dx / 2);

			PlotPoint(x, y, g, PointColor);
			while (x < x2) {
				x++;
				if (d < 0)
					d += dy;
				else {
					d += (dy - dx);
					y++;
				}
				PlotPoint(x, y, g, PointColor);
			}
		}

		// Slope greater than 1
		else if (dx < dy) {
			d = dx - (dy / 2);

			PlotPoint(x, y, g, PointColor);
			while (y < y2) {
				y++;
				if (d < 0)
					d += dx;
				else {
					d += (dx - dy);
					x++;
				}
				PlotPoint(x, y, g, PointColor);
			}
		}
	}
}
