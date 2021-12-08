import api.DirectedWeightedGraph;
import api.Geo_Location;
import api.Node_Data;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;


public class GUI extends JFrame implements ActionListener  {
    JMenuBar menu;
    JMenu action;
    JMenu update;
    JMenuItem add_node;
    JMenuItem connect_edge;
    JMenuItem remove_node;
    JMenuItem remove_edge;
    JMenuItem save;
    JMenuItem load;
    Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
    DirectedWeightedGraph graph;
    public GUI(){
//        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(50,50,10,50));
        jPanel.setLayout(new GridLayout(0,1));
        this.add(jPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(screensize);
        this.setTitle("Graph");
        menu= new JMenuBar();
        action= new JMenu("action");
        save= new JMenuItem("save");
        load= new JMenuItem("load");
        action.add(load);
        action.add(save);
        menu.add(action);
        update= new JMenu("update");
        add_node= new JMenuItem("add node");
        connect_edge= new JMenuItem("connect edge");
        remove_node= new JMenuItem("remove node");
        remove_edge= new JMenuItem("remove edge");
        add_node.addActionListener(this);
        update.add(add_node);
        update.add(connect_edge);
        update.add(remove_node);
        update.add(remove_edge);
        menu.add(update);
        load.addActionListener(this);

        this.setJMenuBar(menu);
//        t= new JTextField();
//        t.setBounds(50,50,100,20);
//        JButton load= new JButton("load");
//        load.setBounds(10,0,25,30);
//        load.setFocusable(false);
//        load.setBorder(BorderFactory.createEtchedBorder());
//        load.setHorizontalTextPosition(JButton.CENTER);
//        load.setVerticalTextPosition(JButton.BOTTOM);
//        load.setFont(new Font("Comic Sans",Font.BOLD,25));
//        load.setIconTextGap(-15);
//        load.setForeground(Color.cyan);
//        load.setBackground(Color.lightGray);
        //this.add(load);
//        menu.setBorder(new BevelBorder(BevelBorder.RAISED));
////        menu.setBorderPainted(true);
//        container.add(menu, BorderLayout.NORTH);
//        menu.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));

        this.setVisible(true);

//            ActionListener listen_update= new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if(ac)
//                }
//            };
//        JButton load= new JButton("load");
//        load.addActionListener(this);
//        jPanel.add(load);
//        load.setBorder(BorderFactory.createEmptyBorder(50,0,1,50));
    }

    public static void main(String[] args) {
        new GUI();
//        new ButtonExample();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == load) {
            try {
                JFrame input = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                input.add(jPanel, BorderLayout.CENTER);
//                input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                input.setLayout(null);
                input.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                input.add(t);
                JLabel label= new JLabel("Enter a path of json file:");
                label.setBounds(50, 30, 200, 20);
                input.add(label);
                JButton enter= new JButton("enter");
                enter.setBounds(50,80,50,20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button= new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String file = t.getText();
                        graph= Ex2.getGrapg(file);
                    }
                };
                enter.addActionListener(button);
                input.add(enter);
                input.setVisible(true);
//            Scanner s= new Scanner(System.in);
//            System.out.println("Enter file path:");
//            String file= s.nextLine();



//            this.remove(t);
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        if (e.getSource() == save){

        }
            if(e.getSource() == add_node){
                try {
                    JFrame add = new JFrame();
                    JPanel jPanel = new JPanel();
                    jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                    jPanel.setLayout(new GridLayout(0, 1));
                    add.add(jPanel, BorderLayout.CENTER);
//                    add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    add.setLayout(null);
                    add.setSize(screensize.width / 2, screensize.height / 2);
                    JTextField t = new JTextField();
                    t.setBounds(50, 50, 200, 20);
                    add.add(t);
                    JLabel label= new JLabel("Enter id and location (with only space between all number):");
                    label.setBounds(50, 30, 400, 20);
                    add.add(label);
                    JButton enter= new JButton("enter");
                    enter.setBounds(50,80,50,20);
                    enter.setFocusable(false);
                    enter.setBorder(BorderFactory.createEtchedBorder());
                    ActionListener button= new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s= t.getText();
                            String[] arr= s.split(" ");
                            graph.addNode(new Node_Data((Integer.parseInt(arr[0])), new Geo_Location((Integer.parseInt(arr[1])),(Integer.parseInt(arr[2])),(Integer.parseInt(arr[0])))));
                            System.out.println(graph.nodeSize());
                        }
                    };
                    enter.addActionListener(button);
                    add.add(enter);
                    add.setVisible(true);
//            Scanner s= new Scanner(System.in);
//            System.out.println("Enter file path:");
//            String file= s.nextLine();



//            this.remove(t);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }
    }

