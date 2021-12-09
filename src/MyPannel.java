import api.*;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.util.Iterator;

public class MyPannel extends JPanel {
    Directed_WeightedGraph graph;
//    Iterator<NodeData> node_iter;
//    Iterator<EdgeData> edge_iter;
    double xScale;
    double yScale;

    public MyPannel(Directed_WeightedGraph g){
        this.graph=g;

//        node_iter = g.nodeIter();
//        edge_iter = g.edgeIter();
        scale();
    }

//    public void printG (){
//        Node_Data n = (Node_Data) node_iter.next();
//        while (node_iter.hasNext()){
//            Node_Data temp = (Node_Data) node_iter.next();
//            Geo_Location loc = (Geo_Location) temp.getLocation();
//
//
//        }
//    }

    public void scale (){
        double Xmax = Double.MIN_VALUE;
        double Xmin = Double.MAX_VALUE;
        double Ymax = Double.MIN_VALUE;
        double Ymin = Double.MAX_VALUE;
        Iterator node_iter=graph.nodeIter();
        while (node_iter.hasNext()){
            Node_Data temp = (Node_Data) node_iter.next();
            if (Xmax < temp.getLocation().x()){
                Xmax = temp.getLocation().x();
            }
            if (Xmin > temp.getLocation().x()){
                Xmin = temp.getLocation().x();
            }
            if (Ymax < temp.getLocation().y()){
                Ymax = temp.getLocation().y();
            }
            if (Ymin > temp.getLocation().y()){
                Ymin = temp.getLocation().y();
            }
        }
        double xAbs = Math.abs(Xmax - Xmin);
        double yAbs = Math.abs(Ymax - Ymin);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xScale = screenSize.width/xAbs;
        yScale = screenSize.height/yAbs;
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        Iterator i = this.graph.nodeIter();
//        while(i.hasNext())
//        {
//            Node_Data v = (Node_Data) i.next();
//            Geo_Location loc = (Geo_Location) v.getLocation();
//            g.fillOval((int)(loc.x()*xScale), (int)(loc.y()*yScale), 5,5);
//            setVisible(true);
//
//        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Iterator node_iter=graph.nodeIter();
        while (node_iter.hasNext()){
            Node_Data temp = (Node_Data) node_iter.next();
            Geo_Location loc = (Geo_Location) temp.getLocation();
            g.fillOval((int)(loc.x()*xScale), (int)(loc.y()*yScale), 5,5);
            repaint();

        }

    }
}
