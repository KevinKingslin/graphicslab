package packages;

import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class DDA extends Applet {

	public DDA() {
		this.setSize(900, 900);
	}

	public void PlotPoint(int x, int y, Integer unit, Graphics g, Color C) {
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;

		g.setColor(C);

		int PointWidth = unit / 4;
		g.fillOval(originX + (x * unit) - PointWidth / 2, originY - (y * unit) - PointWidth / 2, PointWidth,
				PointWidth);
	}

	public void PlotLine(Point P1, Point P2, Integer unit, Graphics g, Color PointColor) {

		double dx, dy, steps;
		double x, y;

		dx = Math.abs(P2.x - P1.x);
		dy = Math.abs(P2.y - P1.y);
		steps = Math.max(dx, dy);

		double xinc = dx / steps;
		double yinc = dy / steps;

		x = P1.x;
		y = P1.y;

		g.setColor(PointColor);
		for (int i = 0; i <= steps; ++i) {
			PlotPoint((int) Math.round(x), (int) Math.round(y), unit, g, PointColor);

			x += xinc;
			y += yinc;
		}
	}
}
