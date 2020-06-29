import java.util.*;

/**
 *
 * @author Joel Pillar-Rogers
 */
public class VirusGraph {

    private final List<Vertex> vertices = new ArrayList<>();

    /**
     * Add the given vertex to the graph.  Allows a vertex to be added that might not be connected.
     * Need to check if the Vertex v already exists to stop duplicates and add only if it does not exist.
     *
     * @param v the vertex to be added
     */
    public void addVertex(Vertex v) {
        if(!vertices.contains(v)){
            vertices.add(v);
        }
    }


    /**
     * Add edge v-w.  Will also add Vertex v and w if they do not already exist.
     * Need to check if the v and w already exists to stop duplicates and add only if it does not exist.
     *
     * @param a
     * @param b
     */
    public void addEdge(String a, String b, int day) {
        Vertex v = new Vertex(a);
        Vertex w = new Vertex(b);
        if(!vertices.contains(v)){
            vertices.add(v);
        }
        if(!vertices.contains(w)){
            vertices.add(w);
        }
        int vLoc = vertices.indexOf(v);
        int wLoc = vertices.indexOf(w);
        vertices.get(vLoc).addContact(vertices.get(wLoc),day);
        vertices.get(wLoc).addContact(vertices.get(vLoc),day);
    }


    /**
     * Get all the vertices associated with the graph in lexicographic order.
     *
     * @return a list of adjacent vertices
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Get the vertex with the label provided
     *
     * @return a list of adjacent vertices
     */
    public Vertex getVertex(String label) {
        int index = vertices.indexOf(new Vertex(label));
        if(index == -1)
            return null;
        else
            return vertices.get(index);
    }

}
