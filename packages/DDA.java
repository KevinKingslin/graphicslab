package packages;

import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class DDA extends Applet {
	private Integer unit = 40;

	public DDA() {
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

		double dx, dy, steps;
		double x, y;

		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);
		steps = Math.max(dx, dy);

		double xinc = dx / steps;
		double yinc = dy / steps;

		x = x1;
		y = y1;

		g.setColor(PointColor);
		for (int i = 0; i <= steps; ++i) {
			PlotPoint((int) Math.round(x), (int) Math.round(y), g, PointColor);

			x += xinc;
			y += yinc;
		}
	}
}