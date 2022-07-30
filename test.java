import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {
	private Button ZoomIn = new Button("Zoom In");
	private Button ZoomOut = new Button("Zoom Out");
	private Integer unit = 40;

	public void init() {
		this.setSize(600, 600);

		add(ZoomIn);
		add(ZoomOut);

		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);
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

		g.setColor(Color.black);
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
		if (e.getSource() == ZoomIn) {
			Graphics g = getGraphics();
			g.clearRect(0, 0, getWidth(), getHeight());
			unit += 20;
			paint(g);
		} else {

		}
	}
}