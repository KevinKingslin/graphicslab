package packages;

import java.util.*;
import java.applet.*;
import java.awt.*;

public class Fire extends Applet {
	private Integer unit = 20;
	Set<String> ColorSet = new HashSet<String>();

	public Fire() {
		this.setSize(900, 900);
	}

	public void PlotPoint(int x, int y, Graphics g, Color C) {

		g.setColor(C);

		int PointWidth = unit;
		g.fillOval(x, y, PointWidth, PointWidth);
	}

	public void PlotLine(Integer x1, Integer y1, Integer x2, Integer y2, Graphics g, Color PointColor) {

		double dx, dy, steps;
		double x, y;

		dx = x2 - x1;
		dy = y2 - y1;
		steps = Math.max(dx, dy);

		double xinc = dx / steps;
		double yinc = dy / steps;

		x = x1;
		y = y1;

		g.setColor(PointColor);
		for (int i = 0; i <= steps; ++i) {
			if (!ColorSet.contains("(" + (int) Math.round(x) + "," + (int) Math.round(y) + ")")) {
				PlotPoint((int) Math.round(x), (int) Math.round(y), g, PointColor);
				ColorSet.add(("(" + (int) Math.round(x) + "," + (int) Math.round(y) + ")"));
			}
			x += xinc;
			y += yinc;
		}
	}

	public void paint(Graphics g) {
		g.clearRect(250, 200, 400, 400);

		double random = Math.random();
		g.setColor(Color.blue);
		g.fillRect(250, 600, 400, 300);

		// Red flame
		for (int x = 0, y = 0, count = 0; count <= 10; count++, x += 4, y += 10) {
			PlotLine(450 + x, 250 + y + (int) (random * 50), 450, 600, g, Color.red);
			PlotLine(450 - x, 250 + y + (int) (random * 50), 450, 600, g, Color.red);
		}

		for (int x = 0, y = 0, count = 0; count <= 15; count++, x += 1, y += 6) {
			PlotLine(490 + x, 350 + y + (int) (random * 50), 450, 600, g, Color.red);
			PlotLine(410 - x, 350 + y + (int) (random * 50), 450, 600, g, Color.red);
		}

		for (int x = 0, y = 0, count = 0; count <= 25; count++, x += 0, y += 4) {
			PlotLine(505 + x, 440 + y + (int) (random * 40), 450, 600, g, Color.red);
			PlotLine(395 - x, 440 + y + (int) (random * 40), 450, 600, g, Color.red);
		}

		ColorSet.clear();

		// Yellow flame
		for (int x = 0, y = 0, count = 0; count <= 5; count++, x += 4, y += 10) {
			PlotLine(450 + x, 430 + y + (int) (random * 50), 450, 600, g, Color.yellow);
			PlotLine(450 - x, 430 + y + (int) (random * 50), 450, 600, g, Color.yellow);
		}

		for (int x = 0, y = 0, count = 0; count <= 5; count++, x += 1, y += 6) {
			y += random * 6;
			PlotLine(470 + x, 480 + y + (int) (random * 50), 450, 600, g, Color.yellow);
			PlotLine(430 - x, 480 + y + (int) (random * 50), 450, 600, g, Color.yellow);
		}

		for (int x = 0, y = 0, count = 0; count <= 10; count++, x += 0, y += 4) {
			PlotLine(475 + x, 510 + y + (int) (random * 40), 450, 600, g, Color.yellow);
			PlotLine(425 - x, 510 + y + (int) (random * 40), 450, 600, g, Color.yellow);
		}

		ColorSet.clear();

		// Blue flame
		for (int x = 0, y = 0, count = 0; count <= 2; count++, x += 4, y += 10) {
			PlotLine(450 + x, 520 + y + (int) (random * 50), 450, 600, g, Color.blue);
			PlotLine(450 - x, 520 + y + (int) (random * 50), 450, 600, g, Color.blue);
		}

		for (int x = 0, y = 0, count = 2; count <= 1; count++, x += 1, y += 6) {
			y += random * 6;
			PlotLine(458 + x, 530 + y + (int) (random * 50), 450, 600, g, Color.blue);
			PlotLine(442 - x, 530 + y + (int) (random * 50), 450, 600, g, Color.blue);
		}

		for (int x = 0, y = 0, count = 0; count <= 1; count++, x += 0, y += 4) {
			PlotLine(457 + x, 536 + y + (int) (random * 40), 450, 600, g, Color.blue);
			PlotLine(441 - x, 536 + y + (int) (random * 40), 450, 600, g, Color.blue);
		}
	}
}
