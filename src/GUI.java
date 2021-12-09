import api.*;
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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class GUI extends JFrame implements ActionListener  {
    Directed_WeightedGraphAlgorithms myGraphAlgo;
    JMenuBar menu;
    JMenu new_graph;
    JMenu update;
    JMenu algorithms;
    JMenuItem add_node;
    JMenuItem connect_edge;
    JMenuItem remove_node;
    JMenuItem remove_edge;
    JMenuItem save;
    JMenuItem load;
    JMenuItem center;
    JMenuItem tsp;
    JMenuItem isconnected;
    JMenuItem shortestPathDist;
    JMenuItem shortestPathList;
    Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
    DirectedWeightedGraph graph;
    public GUI()
    {
//        myGraphAlgo=a;
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
        new_graph= new JMenu("new graph");
        algorithms= new JMenu("algorithms");
        save= new JMenuItem("save");
        load= new JMenuItem("load");
        center=new JMenuItem("center");
        tsp= new JMenuItem("tsp");
        isconnected= new JMenuItem("is connected");
        shortestPathDist= new JMenuItem("short path");
        shortestPathList= new JMenuItem("short path list");
        new_graph.add(load);
        algorithms.add(isconnected);
        algorithms.add(center);
        algorithms.add(tsp);
        algorithms.add(shortestPathDist);
        algorithms.add(shortestPathList);
        algorithms.add(save);
        menu.add(new_graph);
        update= new JMenu("update");
        add_node= new JMenuItem("add node");
        connect_edge= new JMenuItem("connect edge");
        remove_node= new JMenuItem("remove node");
        remove_edge= new JMenuItem("remove edge");
        add_node.addActionListener(this);
        load.addActionListener(this);
        connect_edge.addActionListener(this);
        remove_node.addActionListener(this);
        remove_edge.addActionListener(this);
        save.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);
        isconnected.addActionListener(this);
        shortestPathDist.addActionListener(this);
        shortestPathList.addActionListener(this);
        update.add(add_node);
        update.add(connect_edge);
        update.add(remove_node);
        update.add(remove_edge);
        menu.add(algorithms);
        menu.add(update);
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



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
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
                JLabel label = new JLabel("Enter a path of json file:");
                label.setBounds(50, 30, 200, 20);
                input.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String file = t.getText();
                        graph = Ex2.getGrapg(file);
                        myGraphAlgo = new Directed_WeightedGraphAlgorithms((Directed_WeightedGraph) graph);
                    }
                };
                enter.addActionListener(button);
                input.add(enter);
                input.setVisible(true);
//                if(enter.isSelected()) input.dispose();

//            Scanner s= new Scanner(System.in);
//            System.out.println("Enter file path:");
//            String file= s.nextLine();


//            this.remove(t);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == add_node) {
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
                JLabel label = new JLabel("Enter id and location (with only space between any number):");
                label.setBounds(50, 30, 400, 20);
                add.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        graph.addNode(new Node_Data((Integer.parseInt(arr[0])), new Geo_Location((Integer.parseInt(arr[1])), (Integer.parseInt(arr[2])), (Integer.parseInt(arr[0])))));
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
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == connect_edge) {
            try {
                JFrame connect = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                connect.add(jPanel, BorderLayout.CENTER);
//                    add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                connect.setLayout(null);
                connect.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                connect.add(t);
                JLabel label = new JLabel("Enter source, destination and wight of the edge:(with only space between any number):");
                label.setBounds(50, 30, 700, 20);
                connect.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        graph.connect(Integer.parseInt(arr[0]), (Integer.parseInt(arr[1])), (Double.parseDouble(arr[2])));
                        System.out.println(graph.edgeSize());
                    }
                };
                enter.addActionListener(button);
                connect.add(enter);
                connect.setVisible(true);

//                if(enter.isSelected())
//                    connect.setVisible(false);
//            Scanner s= new Scanner(System.in);
//            System.out.println("Enter file path:");
//            String file= s.nextLine();


//            this.remove(t);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == remove_node) {
            try {
                JFrame nremove = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                nremove.add(jPanel, BorderLayout.CENTER);
                nremove.setLayout(null);
                nremove.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                nremove.add(t);
                JLabel label = new JLabel("Enter the id of the node:");
                label.setBounds(50, 30, 700, 20);
                nremove.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        graph.removeNode(Integer.parseInt(arr[0]));
                        System.out.println(graph.nodeSize());
                    }
                };
                enter.addActionListener(button);
                nremove.add(enter);
                nremove.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == remove_edge) {
            try {
                JFrame eremove = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                eremove.add(jPanel, BorderLayout.CENTER);
                eremove.setLayout(null);
                eremove.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                eremove.add(t);
                JLabel label = new JLabel("Enter the source and the destination of the edge (with only space between any number):");
                label.setBounds(50, 30, 700, 20);
                eremove.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        graph.removeEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        System.out.println(graph.edgeSize());
                    }
                };
                enter.addActionListener(button);
                eremove.add(enter);
                eremove.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == isconnected) {
            try {
                boolean ans = myGraphAlgo.isConnected();
                System.out.println(ans);
                JFrame connected = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                connected.add(jPanel, BorderLayout.CENTER);
                connected.setLayout(null);
                connected.setSize(screensize.width / 4, screensize.height / 10);
                JLabel label = new JLabel();
                label.setBounds(50, 10, 100, 10);
                label.setText("" + ans);
//                label.setBackground(new Color(200,50,70));
                connected.add(label);
                connected.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == shortestPathDist) {
            try {
                JFrame shortp = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                shortp.add(jPanel, BorderLayout.CENTER);
                shortp.setLayout(null);
                shortp.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                shortp.add(t);
                JLabel label = new JLabel("Enter the source and the destination of the path (with only space between any number):");
                label.setBounds(50, 30, 700, 20);
                shortp.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        double ans = myGraphAlgo.shortestPathDist(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        System.out.println(ans);
                        JFrame dis = new JFrame();
                        JPanel jPanel = new JPanel();
                        jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                        jPanel.setLayout(new GridLayout(0, 1));
                        dis.add(jPanel, BorderLayout.CENTER);
                        dis.setLayout(null);
                        dis.setSize(screensize.width / 2, screensize.height / 10);
                        JLabel label = new JLabel();
                        label.setBounds(50, 10, 500, 10);
                        label.setText("" + ans);
                        dis.add(label);
                        dis.setVisible(true);
                    }
                };
                enter.addActionListener(button);
                shortp.add(enter);
                shortp.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == shortestPathList) {
            try {
                JFrame shortp = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                shortp.add(jPanel, BorderLayout.CENTER);
                shortp.setLayout(null);
                shortp.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                shortp.add(t);
                JLabel label = new JLabel("Enter the source and the destination of the path (with only space between any number):");
                label.setBounds(50, 30, 700, 20);
                shortp.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
//                        List<NodeData> ans= myGraphAlgo.shortestPath(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
//                        double ans=myGraphAlgo.shortestPathDist(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        List<NodeData> ans = myGraphAlgo.shortestPath(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        JFrame dis = new JFrame();
                        JPanel jPanel = new JPanel();
                        jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                        jPanel.setLayout(new GridLayout(0, 1));
                        dis.add(jPanel, BorderLayout.CENTER);
                        dis.setLayout(null);
                        dis.setSize(screensize.width / 2, screensize.height / 10);
                        JLabel label = new JLabel();
                        label.setBounds(50, 10, 500, 10);
                        String st ="";
                        for (int i = 0; i < ans.size(); i++) {
                            System.out.println(ans.get(i).getKey());
                            st= st+ ans.get(i).getKey()+ " ";

                        }
                        label.setText(st);
                        dis.add(label);
                        dis.setVisible(true);
                    }
                };
                enter.addActionListener(button);
                shortp.add(enter);
                shortp.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public Directed_WeightedGraph getGraph(){
        return (Directed_WeightedGraph) this.myGraphAlgo.getGraph();
    }

    public static void main(String[] args) {
        new GUI();
//        new ButtonExample();
//        Ex2.runGUI();
    }
    }

