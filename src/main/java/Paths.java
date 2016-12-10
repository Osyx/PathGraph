/**
 * Created by Oscar on 16-12-09.
 */
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

import java.util.Iterator;

public class Paths {
    Graph g = DataSource.load();
    public static void main(String[] args) {
        Paths paths = new Paths();
        Node[] arr = paths.createDataStructure();
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(arr);
        System.out.println("Number of subtrees: " + depthFirstSearch.countSubtrees(arr));
    }

    private Node[] createDataStructure() {
        Node[] arr = new Node[g.numberOfVertices()];
        Iterator<Edge> iterator = g.edges().iterator();
        Edge temp = iterator.next();
        for (int i = 0; i < g.numberOfEdges() - 1; i++) {
            if(arr[temp.from] == null) {
                arr[temp.from] = new Node(temp.to);
            } else {
                Node tempNode = arr[temp.from];
                while(tempNode.getNext() != null)
                    tempNode = tempNode.getNext();
                if (tempNode.getNeighbor() != temp.to)
                    tempNode.add(temp.to);
            }
            temp = iterator.next();
        }
        return arr;
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