import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

import java.util.Iterator;

/**
 * Created by Oscar on 16-12-09.
 */
public class Paths {

    public static void main(String[] args) {
        Paths paths = new Paths();
        Node[] newGraphStructure = paths.createDataStructure(DataSource.load());
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(newGraphStructure);
        System.out.println("Number of subtrees: " + depthFirstSearch.countSubtrees(newGraphStructure));
    }

    private Node[] createDataStructure(Graph givenGraph) {
        Node[] newDataStructure = new Node[givenGraph.numberOfVertices()];
        Iterator<Edge> edgeIterator = givenGraph.edges().iterator();
        Edge currentEdge = edgeIterator.next();
        for (int i = 0; i < givenGraph.numberOfEdges() - 1; i++) {
            if(newDataStructure[currentEdge.from] == null) {
                newDataStructure[currentEdge.from] = new Node(currentEdge.to);
            } else {
                Node currentFrom = newDataStructure[currentEdge.from];
                while(currentFrom.getNext() != null)
                    currentFrom = currentFrom.getNext();
                if (currentFrom.getNeighbor() != currentEdge.to)
                    currentFrom.add(currentEdge.to);
            }
            currentEdge = edgeIterator.next();
        }
        return newDataStructure;
    }

    class Node {
        int neighbor;
        Node next;

        Node(int neighbor) {
            this.neighbor = neighbor;
        }

        void add(int neighbor) {
            addNext(new Node(neighbor));
        }

        void addNext(Node next) {
            this.next = next;
        }

        int getNeighbor() {
            return neighbor;
        }

        Node getNext() {
            return next;
        }
    }

}