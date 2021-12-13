import api.*;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Iterator;

//this class present the graph in frame.
public class GUI extends JFrame implements ActionListener {
    DirectedWeightedGraphAlgorithms myGraphAlgo;
    MyPannel pannel;
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
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    Directed_WeightedGraph graph;
    boolean loading = false;

    public GUI(DirectedWeightedGraphAlgorithms g) {
        this.myGraphAlgo = g;
        pannel = new MyPannel((Directed_WeightedGraph) g.getGraph());
        pannel.setBounds(0, 0, screensize.width, screensize.height);
        pannel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        this.add(pannel, BorderLayout.NORTH);
        this.add(pannel);
        pannel.setVisible(true);
        this.repaint();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(screensize);
        this.setTitle("Graph");
        menu = new JMenuBar();
        new_graph = new JMenu("new graph");
        algorithms = new JMenu("algorithms");
        save = new JMenuItem("save");
        load = new JMenuItem("load");
        center = new JMenuItem("center");
        tsp = new JMenuItem("tsp");
        isconnected = new JMenuItem("is connected");
        shortestPathDist = new JMenuItem("short path");
        shortestPathList = new JMenuItem("short path list");
        new_graph.add(load);
        algorithms.add(isconnected);
        algorithms.add(center);
        algorithms.add(tsp);
        algorithms.add(shortestPathDist);
        algorithms.add(shortestPathList);
        algorithms.add(save);
        menu.add(new_graph);
        update = new JMenu("update");
        add_node = new JMenuItem("add node");
        connect_edge = new JMenuItem("connect edge");
        remove_node = new JMenuItem("remove node");
        remove_edge = new JMenuItem("remove edge");
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
        this.setVisible(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            try {
                JFrame input = new JFrame();
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
                        graph = (Directed_WeightedGraph) Ex2.getGrapg(file);
                        myGraphAlgo.init(graph);
                        loading = true;
                        repaint();
                        input.dispose();
                        pannel.init((Directed_WeightedGraph) myGraphAlgo.getGraph());
                        pannel.repaint();
                    }
                };
                enter.addActionListener(button);
                input.add(enter);
                this.repaint();
                input.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == add_node) {
            try {
                if (!loading) {
                    JFrame message = new JFrame();
                    message.setLayout(null);
                    message.setSize(screensize.width / 2, screensize.height / 2);
                    JLabel label = new JLabel("please load graph before update");
                    label.setBounds(50, 30, 400, 20);
                    message.add(label);
                    message.setVisible(true);
                    return;
                }
                JFrame add = new JFrame();
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
                        if (graph.getMapOfNode().containsKey(Integer.parseInt(arr[0]))) {
                            add.dispose();
                            JFrame messege1 = new JFrame();
                            messege1.setLayout(null);
                            messege1.setSize(screensize.width / 2, screensize.height / 2);
                            Label l = new Label();
                            l.setText("this node is already exists");
                            l.setBounds(50, 30, 400, 20);
                            messege1.add(l);
                            messege1.setVisible(true);
                        } else {
                            graph.addNode(new Node_Data((Integer.parseInt(arr[0])), new Geo_Location((Double.parseDouble(arr[1])), (Double.parseDouble(arr[2])), (Double.parseDouble(arr[3])))));
                            myGraphAlgo.init(graph);
                            add.dispose();
                        }

                    }
                };
                pannel.init((Directed_WeightedGraph) myGraphAlgo.getGraph());
                pannel.repaint();
                enter.addActionListener(button);
                add.add(enter);
                add.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == connect_edge) {
            try {
                if (!loading) {
                    JFrame message = new JFrame();
                    message.setLayout(null);
                    message.setSize(screensize.width / 2, screensize.height / 2);
                    JLabel label = new JLabel("please load graph before update");
                    label.setBounds(50, 30, 400, 20);
                    message.add(label);
                    message.setVisible(true);
                    return;
                }
                JFrame connect = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                connect.add(jPanel, BorderLayout.CENTER);
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
                        if (graph.getMapOfEdge().containsKey(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))) {
                            connect.dispose();
                            JFrame messege1 = new JFrame();
                            messege1.setLayout(null);
                            messege1.setSize(screensize.width / 2, screensize.height / 2);
                            Label l = new Label();
                            l.setText("this edge is already exists");
                            l.setBounds(50, 30, 400, 20);
                            messege1.add(l);
                            messege1.setVisible(true);
                        } else {
                            graph.connect(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Double.parseDouble(arr[2]));
                            myGraphAlgo.init(graph);
                            connect.dispose();
                        }
                    }
                };
                pannel.init((Directed_WeightedGraph) myGraphAlgo.getGraph());
                pannel.repaint();
                enter.addActionListener(button);
                connect.add(enter);
                connect.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == remove_node) {
            try {
                if (!loading) {
                    JFrame message = new JFrame();
                    message.setLayout(null);
                    message.setSize(screensize.width / 2, screensize.height / 2);
                    JLabel label = new JLabel("please load graph before update");
                    label.setBounds(50, 30, 400, 20);
                    message.add(label);
                    message.setVisible(true);
                    return;
                }
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
                        if (!graph.getMapOfNode().containsKey(Integer.parseInt(arr[0]))) {
                            nremove.dispose();
                            JFrame messege1 = new JFrame();
                            messege1.setLayout(null);
                            messege1.setSize(screensize.width / 2, screensize.height / 2);
                            Label l = new Label();
                            l.setText("this node is not exists");
                            l.setBounds(50, 30, 400, 20);
                            messege1.add(l);
                            messege1.setVisible(true);
                        } else {
                            graph.removeNode(Integer.parseInt(arr[0]));
                            myGraphAlgo.init(graph);
                            System.out.println(graph.nodeSize());
                            nremove.dispose();
                        }
                    }
                };
                pannel.init((Directed_WeightedGraph) myGraphAlgo.getGraph());
                pannel.repaint();
                enter.addActionListener(button);
                nremove.add(enter);
                nremove.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == remove_edge) {
            try {
                if (!loading) {
                    JFrame message = new JFrame();
                    message.setLayout(null);
                    message.setSize(screensize.width / 2, screensize.height / 2);
                    JLabel label = new JLabel("please load graph before update");
                    label.setBounds(50, 30, 400, 20);
                    message.add(label);
                    message.setVisible(true);
                    return;
                }
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
                        if (!graph.getMapOfEdge().containsKey(new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))) {
                            eremove.dispose();
                            JFrame messege1 = new JFrame();
                            messege1.setLayout(null);
                            messege1.setSize(screensize.width / 2, screensize.height / 2);
                            Label l = new Label();
                            l.setText("this edge is not exists");
                            l.setBounds(50, 30, 400, 20);
                            messege1.add(l);
                            messege1.setVisible(true);
                        } else {
                            graph.removeEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                            myGraphAlgo.init(graph);
                            eremove.dispose();
                        }
                    }
                };
                pannel.init((Directed_WeightedGraph) myGraphAlgo.getGraph());
                pannel.repaint();
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
                        shortp.dispose();
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
                        List<NodeData> ans = myGraphAlgo.shortestPath(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        shortp.dispose();
                        JFrame dis = new JFrame();
                        JPanel jPanel = new JPanel();
                        jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                        jPanel.setLayout(new GridLayout(0, 1));
                        dis.add(jPanel, BorderLayout.CENTER);
                        dis.setLayout(null);
                        dis.setSize(screensize.width / 2, screensize.height / 10);
                        JLabel label = new JLabel();
                        label.setBounds(50, 10, 500, 10);
                        String st = "";
                        for (int i = 0; i < (ans.size() - 1); i++) {
                            st = st + ans.get(i).getKey() + " ";
                        }
                        st = st + myGraphAlgo.getGraph().getNode(Integer.parseInt(arr[1])).getKey() + " ";
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
        if (e.getSource() == save) {
            try {
                JFrame saveg = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                saveg.add(jPanel, BorderLayout.CENTER);
                saveg.setLayout(null);
                saveg.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                saveg.add(t);
                JLabel label = new JLabel("Enter a name for json file: ");
                label.setBounds(50, 30, 700, 20);
                saveg.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        myGraphAlgo.save(s);
                        saveg.dispose();
                    }
                };
                enter.addActionListener(button);
                saveg.add(enter);
                saveg.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == tsp) {
            try {
                JFrame tspg = new JFrame();
                JPanel jPanel = new JPanel();
                jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                jPanel.setLayout(new GridLayout(0, 1));
                tspg.add(jPanel, BorderLayout.CENTER);
                tspg.setLayout(null);
                tspg.setSize(screensize.width / 2, screensize.height / 2);
                JTextField t = new JTextField();
                t.setBounds(50, 50, 200, 20);
                tspg.add(t);
                JLabel label = new JLabel("Enter a list of vertex: ");
                label.setBounds(50, 30, 700, 20);
                tspg.add(label);
                JButton enter = new JButton("enter");
                enter.setBounds(50, 80, 50, 20);
                enter.setFocusable(false);
                enter.setBorder(BorderFactory.createEtchedBorder());
                ActionListener button = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = t.getText();
                        String[] arr = s.split(" ");
                        List<NodeData> to_tsp = new LinkedList<>();
                        for (int i = 0; i < arr.length; i++) {
                            to_tsp.add(myGraphAlgo.getGraph().getNode(Integer.parseInt(arr[i])));
                        }
                        List<NodeData> ans = myGraphAlgo.tsp(to_tsp);
                        tspg.dispose();
                        JFrame dis = new JFrame();
                        JPanel jPanel = new JPanel();
                        jPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
                        jPanel.setLayout(new GridLayout(0, 1));
                        dis.add(jPanel, BorderLayout.CENTER);
                        dis.setLayout(null);
                        dis.setSize(screensize.width / 2, screensize.height / 10);
                        JLabel label = new JLabel();
                        label.setBounds(50, 10, 500, 10);
                        String st = "";
                        for (int i = 0; i < ans.size(); i++) {
                            st = st + ans.get(i).getKey() + " ";

                        }
                        label.setText(st);
                        dis.add(label);
                        dis.setVisible(true);
                    }
                };
                enter.addActionListener(button);
                tspg.add(enter);
                tspg.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == center) {
            try {
                JFrame cent = new JFrame();
                cent.setLayout(null);
                cent.setSize(screensize.width / 2, screensize.height / 2);
                int ans = myGraphAlgo.center().getKey();
                JLabel label = new JLabel();
                label.setBounds(50, 30, 700, 20);
                label.setText("the center of the graph is: " + ans);
                cent.add(label);
                cent.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }


    public Directed_WeightedGraph getGraph() {
        return (Directed_WeightedGraph) this.myGraphAlgo.getGraph();
    }

    public static void main(String[] args) {
        Ex2.runGUI("G2.json");
    }
}


