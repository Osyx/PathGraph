import se.kth.id1020.Graph;

/**
 * Created by Oscar on 16-12-09.
 */
class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    DepthFirstSearch(Paths.Node[] nodeList) {
        marked = new boolean[nodeList.length];
    }

    private void dfs(Paths.Node[] nodeList, int v) {
        marked[v] = true;
        if(nodeList[v] != null) {
            Paths.Node temp = nodeList[v];
            while (temp != null) {
                if (!marked[temp.getNeighbor()])
                    dfs(nodeList, temp.getNeighbor());
                temp = temp.getNext();
            }
        }
    }

    int countSubtrees(Paths.Node[] nodeList) {
        for (int i = 0; i < nodeList.length; i++) {
            if(!marked[i]) {
                dfs(nodeList, i);
                count++;
            }
        }
        return count;
    }
}
