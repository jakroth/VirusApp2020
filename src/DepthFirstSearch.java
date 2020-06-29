import java.util.*;

/**
 *
 * @author Joel Pillar-Rogers
 */
public class DepthFirstSearch {

    /**
     * The list of Vertex objects in traversal order
     */
    private List<Vertex> traversalOrder;

    public DepthFirstSearch(VirusGraph g, Vertex source)
    {
        traversalOrder = new LinkedList<>();

        // reset the state of each vertex
        for (Vertex v : g.getVertices()) {
            v.setState(Vertex.VertexState.UNVISITED);
        }

        // call the recursive part of the algorithm
        dfs(source, g, 0);
    }

    private void dfs(Vertex v, VirusGraph g, int day) {
        v.setState(Vertex.VertexState.DISCOVERED);

        Map<Vertex,Integer> contacts = v.adjacentTo();
        if (contacts != null) {
            for(Map.Entry<Vertex,Integer> c : contacts.entrySet()){
                if(c.getValue() >= day &&
                        c.getKey().getState() == Vertex.VertexState.UNVISITED) {
                    dfs(c.getKey(), g, c.getValue());
                }
            }
            v.setState(Vertex.VertexState.FINISHED);
            traversalOrder.add(v);
        }
    }

    public List<Vertex> getDepthFirstTraversalList(){
        return traversalOrder;
    }

}
