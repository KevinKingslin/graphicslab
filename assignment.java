import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class assignment extends Applet implements ActionListener {
	private Integer unit = 40;
	private Button ZoomIn = new Button("Zoom In");
	private Button ZoomOut = new Button("Zoom Out");

	private Button DDA = new Button("DDA");
	private Button Bresenham = new Button("Bresenham");
	private Button MidPoint = new Button("Mid-Point");

	private Color BackgroundColor = new Color(255, 255, 255);
	private Color GridColor = new Color(100, 100, 100);
	private Color PointColor = new Color(255, 0, 0);

	public void init() {
		this.setSize(900, 900);
		this.setBackground(BackgroundColor);
		setLayout(null);

		ZoomIn.setBounds(400, 0, 80, 40);
		ZoomOut.setBounds(490, 0, 80, 40);

		DDA.setBounds(800, 0, 100, 50);
		Bresenham.setBounds(800, 50, 100, 50);
		MidPoint.setBounds(800, 100, 100, 50);

		add(ZoomIn);
		add(ZoomOut);
		add(DDA);
		add(Bresenham);
		add(MidPoint);

		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);
		DDA.addActionListener(this);
		Bresenham.addActionListener(this);
		MidPoint.addActionListener(this);
	}

	public void paint(Graphics g) {

		int height = getHeight();
		int width = getWidth();

		// Set origin
		int originX = (getX() + width) / 2;
		int originY = (getY() + height) / 2;

		g.setColor(Color.red);
		Font stringFont = new Font("Arial", 4, 18);
		g.setFont(stringFont);

		// Set x-axis
		g.drawLine(0, height / 2, width, height / 2);

		// Set y-axis
		g.drawLine(width / 2, 0, width / 2, height);

		g.setColor(GridColor);
		g.drawString("0", originX, originY);

		int coordinate = 1;

		for (int count = 1; count <= 50; count++, coordinate++) {
			// Set horizontal lines
			g.drawLine(width / 2 + (unit * count), 0, width / 2 + (unit * count), height);
			g.drawLine(width / 2 - (unit * count), 0, width / 2 - (unit * count), height);

			// Set vertical lines
			g.drawLine(0, height / 2 + (unit * count), width, height / 2 + (unit * count));
			g.drawLine(0, height / 2 - (unit * count), width, height / 2 - (unit * count));

			// Set x-axis coordinates
			g.drawString(String.valueOf(coordinate), originX + (unit * count), originY);
			g.drawString("-" + String.valueOf(coordinate), originX, originY + (unit * count));

			// Set y-axis coordinates
			g.drawString(String.valueOf(coordinate), originX, originY - (unit * count));
			g.drawString("-" + String.valueOf(coordinate), originX - (unit * count), originY);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Graphics g = getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		if (e.getSource() == ZoomIn)
			unit += 10;

		else if (e.getSource() == ZoomOut)
			unit -= 10;

		else if (e.getSource() == DDA)
			dda(3, 4, 5, 8);

		else if (e.getSource() == Bresenham)
			bresenham(3, 4, 5, 8);

		else if (e.getSource() == MidPoint)
			midpoint(3, 4, 5, 8);

		paint(g);
	}

	public void plotpoint(int x, int y, Color C) {
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;
		Graphics g = getGraphics();
		g.setColor(C);

		int PointWidth = unit / 4;
		g.fillOval(originX + (x * unit) - PointWidth / 2, originY - (y * unit) - PointWidth / 2, PointWidth,
				PointWidth);
	}

	public void dda(Integer x1, Integer y1, Integer x2, Integer y2) {
		double dx, dy, steps;
		double x, y;

		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);
		steps = Math.max(dx, dy);

		double xinc = dx / steps;
		double yinc = dy / steps;

		x = x1;
		y = y1;

		for (int i = 0; i <= steps; ++i) {
			plotpoint((int) Math.round(x), (int) Math.round(y), PointColor);

			x += xinc;
			y += yinc;
		}
	}

	public void bresenham(Integer x1, Integer y1, Integer x2, Integer y2) {
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
					plotpoint(x, y, PointColor);
					y += 1;
					p = p + 2 * dy - 2 * dx;
				} else {
					plotpoint(x, y, PointColor);
					p = p + 2 * dy;
				}
				x += 1;
			}
		} else {
			int p = 2 * dx - dy;
			while (y <= y2) {
				if (p >= 0) {
					plotpoint(x, y, PointColor);
					x += 1;
					p = p + 2 * dx - 2 * dy;
				} else {
					plotpoint(x, y, PointColor);
					p = p + 2 * dx;
				}
				y += 1;
			}
		}
	}

	public void midpoint(Integer x1, Integer y1, Integer x2, Integer y2) {
		int dx, dy, d, x, y;

		dx = x2 - x1;
		dy = y2 - y1;

		x = x1;
		y = y1;

		// Slope less than or equal to 1
		if (dy <= dx) {
			d = dy - (dx / 2);

			plotpoint(x, y, PointColor);
			while (x < x2) {
				x++;
				if (d < 0)
					d += dy;
				else {
					d += (dy - dx);
					y++;
				}
				plotpoint(x, y, PointColor);
			}
		}

		// Slope greater than 1
		else if (dx < dy) {
			d = dx - (dy / 2);

			plotpoint(x, y, PointColor);
			while (y < y2) {
				y++;
				if (d < 0)
					d += dx;
				else {
					d += (dx - dy);
					x++;
				}
				plotpoint(x, y, PointColor);
			}
		}
	}
}