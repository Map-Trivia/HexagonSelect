import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.*;

class Main extends MouseAdapter implements Runnable{
    
  public static final int MULTIPLIER = 4;
  public static final int SIDE_LENGTH = 17 * MULTIPLIER;
  public static final double SIDE_SIDE_LENGTH = 29.5 * MULTIPLIER;
  public static final int PERIMETER = 102 * MULTIPLIER;
  public static final int AREA = 750 * MULTIPLIER;
  public static final int VERTEX_VERTEX_LENGTH = 34 * MULTIPLIER;
  public static final int X_OFFSET = 15 * MULTIPLIER;
  public static final int Y_OFFSET = 8 * MULTIPLIER;
  protected Point[] pts;
  protected Polygon p;
  private JPanel panel;
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Main());
  }

  protected void redraw(Graphics g) {
    g.setColor(Color.BLACK);   
    g.drawPolygon(p);
    }

  @Override
    public void run() {
    JFrame frame = new JFrame("Hello world");
    frame.setSize(300, 300);
    frame.setLocation(5, 5);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    completeHex(new Point(0,0));
    
    panel = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
          
          // first, we should call the paintComponent method we are
          // overriding in JPanel
          super.paintComponent(g);

          // redraw our graphics items
          redraw(g);
      }
    };
    frame.add(panel);
    panel.addMouseListener(this);

    frame.show();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    completeHex(e.getPoint());
    panel.repaint();
  }
  
  /**
   * Adds points to p to create a hexagon in this shape
   * / \ 
   * | |
   * \ /
   */
  private void completeHex(Point center) {
    // Init points in array, so they can be used again
    pts[0] = new Point(center.x, center.y);
    pts[1] = new Point(center.x + X_OFFSET, center.y + Y_OFFSET);
    pts[2] = new Point(center.x + X_OFFSET, center.y - Y_OFFSET);
    pts[3] = new Point(center.x, center.y - SIDE_LENGTH);
    pts[4] = new Point(center.x - X_OFFSET, center.y - Y_OFFSET);
    pts[5] = new Point(center.x - X_OFFSET, center.y + Y_OFFSET);

    p.addPoint(pts[0].x, pts[0].y);

    p.addPoint(pts[1].x, pts[1].y);

    p.addPoint(pts[2].x, pts[2].y);
    p.addPoint(pts[3].x, pts[3].y);

    p.addPoint(pts[4].x, pts[4].y);
    p.addPoint(pts[5].x, pts[5].y);
  }
  
  
}