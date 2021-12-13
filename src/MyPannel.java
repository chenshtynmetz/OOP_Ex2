import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;

public class MyPannel extends JPanel {
    Directed_WeightedGraph graph;
    double xScale;
    double yScale;
    double Xmax;
    double Xmin;
    double Ymax;
    double Ymin;
    double factors1 = 1;

    public MyPannel(Directed_WeightedGraph g) {
        super();
        this.setBackground(Color.white);
        this.graph = g;
        scale();
        this.repaint();
    }

    public void init(Directed_WeightedGraph g) {
        this.graph = g;
        scale();
        this.repaint();
    }

    public void scale() {
        Xmax = Double.MIN_VALUE;
        Xmin = Double.MAX_VALUE;
        Ymax = Double.MIN_VALUE;
        Ymin = Double.MAX_VALUE;
        Iterator node_iter = graph.nodeIter();
        while (node_iter.hasNext()) {
            Node_Data temp = (Node_Data) node_iter.next();
            if (Xmax < temp.getLocation().x()) {
                Xmax = temp.getLocation().x();
            }
            if (Xmin > temp.getLocation().x()) {
                Xmin = temp.getLocation().x();
            }
            if (Ymax < temp.getLocation().y()) {
                Ymax = temp.getLocation().y();
            }
            if (Ymin > temp.getLocation().y()) {
                Ymin = temp.getLocation().y();
            }
        }
        double xAbs = Math.abs(Xmax - Xmin);
        double yAbs = Math.abs(Ymax - Ymin);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xScale = screenSize.width / xAbs;
        yScale = screenSize.height / yAbs;
        xScale = xScale * 0.7;
        yScale = yScale * 0.7;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        Iterator eiter = this.graph.edgeIter();
        while (eiter.hasNext()) {
            Edge_Data temp = (Edge_Data) eiter.next();
            g.setColor(Color.black);
            double Xsrc = (this.graph.getNode(temp.getSrc()).getLocation().x() - Xmin) * xScale * 0.98 + 30;
            double Ysrc = (this.graph.getNode(temp.getSrc()).getLocation().y() - Ymin) * yScale * 0.98 + 30;
            double Xdst = (this.graph.getNode(temp.getDest()).getLocation().x() - Xmin) * xScale * 0.98 + 30;
            double Ydst = (this.graph.getNode(temp.getDest()).getLocation().y() - Ymin) * yScale * 0.98 + 30;
            int x1 = (int) Xsrc;
            int x2 = (int) Xdst;
            int y1 = (int) Ysrc;
            int y2 = (int) Ydst;
            g1.setStroke(new BasicStroke(1));
            g1.draw(new Line2D.Double(x1, y1, x2, y2));
            double t = Math.atan2(y2 - y1, x2 - x1);
            g.setColor(Color.green);
            arrow(g1, t, x2, y2);
        }
        Iterator<NodeData> iter = this.graph.nodeIter();
        while (iter.hasNext()) {
            NodeData temp = iter.next();
            g.setColor(Color.orange);
            if (temp != null) {
                Geo_Location loc = (Geo_Location) temp.getLocation();
                double x = (loc.x() - Xmin) * xScale * 0.97 + 30;
                double y = (loc.y() - Ymin) * yScale * 0.97 + 30;
                g.fillOval((int) (x - 2), (int) (y - 2), 20, 20);
                this.repaint();
            }
        }
    }

    private void arrow(Graphics2D g1, double t, double x1, double y1) {
        double b = 12;
        double pi = Math.PI / 6;
        double x2 = x1 - b * Math.cos(t + pi);
        double y2 = y1 - b * Math.sin(t + pi);
        double x3 = x1 - b * Math.cos(t - pi);
        double y3 = y1 - b * Math.sin(t - pi);
        int[] xpoints = {(int) x1, (int) x2, (int) x3};
        int[] ypoints = {(int) y1, (int) y2, (int) y3};
        g1.fillPolygon(xpoints, ypoints, 3);

    }

}



