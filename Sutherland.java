import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Sutherland extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int unit = 2;
  int temp = 0;
  static final int INSIDE = 0; // 0000
  static final int LEFT = 1; // 0001
  static final int RIGHT = 2; // 0010
  static final int BOTTOM = 4; // 0100
  static final int TOP = 8; // 1000

  static final int x_max = 100;
  static final int y_max = 100;
  static final int x_min = -100;
  static final int y_min = -100;

  Button b1 = new Button("Zoom In");
  Button b2 = new Button("Zoom Out");
  Button b3 = new Button("Clip Line");

  public void init() {
    setBackground(Color.YELLOW);
    b1.setBackground(new Color(255, 229, 180));
    b2.setBackground(new Color(255, 229, 180));
    b3.setBackground(new Color(255, 229, 180));
    add(b1);
    add(b2);
    add(b3);
    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;
    // drawGrid(g);
    DDALine(g, x_min, y_min, x_max, y_min);
    DDALine(g, x_min, y_max, x_max, y_max);
    DDALine(g, x_min, y_min, x_min, y_max);
    DDALine(g, x_max, y_min, x_max, y_max);
    // cohenSutherlandClip(g,5, 5, 7, 7);
    if (temp == 0) {
      DDALine(g, 0, -150, 150, 0);
      DDALine(g, 0, -150, -150, 0);
      DDALine(g, 0, 150, 150, 0);
      DDALine(g, 0, 150, -150, 0);
    } else {
      cohenSutherlandClip(g, 0, -150, 150, 0);
      cohenSutherlandClip(g, 0, -150, -150, 0);
      cohenSutherlandClip(g, 0, 150, 150, 0);
      cohenSutherlandClip(g, 0, 150, -150, 0);
    }
    // Second Line segment
    // P21 = (7, 9), P22 = (11, 4)
    // cohenSutherlandClip(g,7, 9, 11, 4);

    // // Third Line segment
    // // P31 = (1, 5), P32 = (4, 1)
    // cohenSutherlandClip(g,1, 5, 4, 1);
  }

  // Function to draw origin
  public void drawOriginCircle(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(originX - 5, originY - 5, 10, 10);
  }

  // Function for plotting points
  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
        originX + (x * unit) - unit / 2,
        originY - (y * unit) - unit / 2,
        unit,
        unit);
  }

  // Function to draw X-axis
  public void drawXaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(0, originY - 2, width, 4);
  }

  // Function to draw Y-axis
  public void drawYaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(originX - 2, 0, 4, height);
  }

  // Function to draw the Grid
  public void drawGrid(Graphics g) {
    drawHorizontalLines(g);
    drawVeritcalLines(g);
  }

  // Function to draw the horizontal lines of the grid
  public void drawHorizontalLines(Graphics g) {
    g.setColor(Color.RED);
    for (int i = originX; i <= width; i += unit) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = originX; i >= 0; i -= unit) {
      g.drawLine(i, 0, i, height);
    }
  }

  // Function to draw the vertical lines of the grid
  public void drawVeritcalLines(Graphics g) {
    g.setColor(Color.RED);
    for (int i = originY; i <= height; i += unit) {
      g.drawLine(0, i, width, i);
      // add coordinate text
    }
    for (int i = originY; i >= 0; i -= unit) {
      g.drawLine(0, i, width, i);
    }
  }

  // Function for the buttons
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1)
      zoom(1);
    if (e.getSource() == b2)
      zoom(-1);
    if (e.getSource() == b3) {
      if (temp == 0)
        temp = 1;
      else
        temp = 0;
      repaint();
    }
  }

  // Function for the mousewheel
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  // Function for the zoom in feature
  public void zoom(int i) {
    if (unit + i >= 1 && unit + i <= 300) {
      unit += i;
      repaint();
    }
  }

  static int computeCode(int x, int y) {
    // initialized as being inside
    int code = INSIDE;

    if (x < x_min)
      code |= LEFT;
    else if ( // to the left of rectangle
    x > x_max)
      code |= RIGHT; // to the right of rectangle
    if (y < y_min)
      code |= BOTTOM;
    else if ( // below the rectangle
    y > y_max)
      code |= TOP; // above the rectangle

    return code;
  }

  public void cohenSutherlandClip(Graphics g, int x1, int y1, int x2, int y2) {
    // Compute region codes for P1, P2
    int code1 = computeCode(x1, y1);
    int code2 = computeCode(x2, y2);

    // Initialize line as outside the rectangular window
    boolean accept = false;

    while (true) {
      if ((code1 == 0) && (code2 == 0)) {
        // If both endpoints lie within rectangle
        accept = true;
        break;
      } else if ((code1 & code2) != 0) {
        // If both endpoints are outside rectangle,
        // in same region
        break;
      } else {
        // Some segment of line lies within the
        // rectangle
        int code_out;
        int x = 0, y = 0;

        // At least one endpoint is outside the
        // rectangle, pick it.
        if (code1 != 0)
          code_out = code1;
        else
          code_out = code2;

        // Find intersection point;
        // using formulas y = y1 + slope * (x - x1),
        // x = x1 + (1 / slope) * (y - y1)
        if ((code_out & TOP) != 0) {
          // point is above the clip rectangle
          x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
          y = y_max;
        } else if ((code_out & BOTTOM) != 0) {
          // point is below the rectangle
          x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
          y = y_min;
        } else if ((code_out & RIGHT) != 0) {
          // point is to the right of rectangle
          y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
          x = x_max;
        } else if ((code_out & LEFT) != 0) {
          // point is to the left of rectangle
          y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
          x = x_min;
        }

        // Now intersection point x, y is found
        // We replace point outside rectangle
        // by intersection point
        if (code_out == code1) {
          x1 = x;
          y1 = y;
          code1 = computeCode(x1, y1);
        } else {
          x2 = x;
          y2 = y;
          code2 = computeCode(x2, y2);
        }
      }
    }
    if (accept) {
      // System.out.println(
      // "Line accepted from " + x1 + ", " + y1 + " to " + x2 + ", " + y2
      // );
      DDALine(g, x1, y1, x2, y2);
      // Here the user can add code to display the
      // rectangle along with the accepted (portion
      // of) lines
    } else
      System.out.println("Line rejected");
  }

  void DDALine(Graphics g, int x0, int y0, int x1, int y1) {
    int dx = (x1 - x0);
    int dy = (y1 - y0);

    int step;
    if (Math.abs(dx) > Math.abs(dy)) {
      step = Math.abs(dx);
    } else {
      step = Math.abs(dy);
    }

    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;
    float x = (float) x0;
    float y = (float) y0;

    for (int i = 0; i < step; i++) {
      plotPoint(g, Math.round(x), Math.round(y), Color.red);
      x += x_incr;
      y += y_incr;
    }
  }
}