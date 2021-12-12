# OOP_Ex2
// In this task we asked to peresent graph and do algorinms on him.
// For do this we implement a few classes:
Node_Data: the node data present a vertex that include: id (int), location(Geolocation), information(String) and tag (int). This class implements get and set methods.
Edge_Data: the edge data present a edge on the graph that include: source node(Node_Data), destination node(Node_Data), weight (double), information (string) and tag (int).  This class implements get and set methods.
Directed_WightedGraph: this object present the graph. This class include a few hashmap: 1.mapOfNode: a hash of all the nodes. 2.mapOfEdge: a hash of all the edges. 3.mapOfSrc: a hash that for all node on the graph, keep a hashmap that the key is node that connect to our node with edge and the value is the edge. 4.mapOfDst: the opsite from the mapOfSrc. This class implements get and set methods. In addition this class implemntes the functions: addnode, removenoade, removeedge, connect- connect 2 nodes with edge, iteredge- a iterator of the edge, iternode- a iterator of the nodes, iteredge(int key)- a iterator of all the edge that connect to the node with id key.
Directed_WightedGraphAlgorithem: this class implements methods that activate algorithems on the graph:
Isconnected: return true if the graph is connected. We implements BFS algorithem to help us with this methods.
Center: conculate the center of the graph.
Shortestpathdis: return the shortest path between to vertexes.
Shortestpath: return all the vertex that need to pass for the short path in list.
Tsp: conculate tsp problem.
Save: save the graph to json file.
Load: load graph
