import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI {

    public GUI(){
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(50,50,10,50));
        jPanel.setLayout(new GridLayout(0,1));
        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Graph");
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }
}
