import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Joel Pillar-Rogers
 */
public class Vertex implements Comparable<Vertex> {

    /**
     * Enumeration to model the states of a Vertex
     */
    public enum VertexState {UNVISITED, DISCOVERED, FINISHED};

    /**
     * The uniquely identifying label for the Vertex
     */
    private String label;
    private VertexState state;
    private Map<Vertex,Integer> adjList = new HashMap<>();
    private String alphaCode;

    /**
     * Constructor
     */
    public Vertex(String label){
        this.label = label;
        this.state = VertexState.UNVISITED;
    }

    public VertexState getState(){
        return state;
    }

    public void setState(VertexState vs){
        this.state = vs;
    }

    public void addContact(Vertex w, int day){
        adjList.put(w,day);
    }

    public String getCode(){
        return alphaCode;
    }

    public void setCode(String code){
        this.alphaCode = code;
    }

    /**
     * Neigbours of this vertex in any order.
     * The method will return null if there are no adjacent vertices.
     *
     * @return the list of adjacent vertices or null if no adjacent vertices.
     */
    public Map<Vertex,Integer> adjacentTo() {
        if(adjList.isEmpty())
            return null;
        else
            return adjList;
    }


    /**
     * Returns the hash code for this object, based on the label (calls <code>this.label.hashCode()</code>.
     * @return
     */
    public int hashCode() {
        return this.label.hashCode();
    }

    /**
     * This equals compares this Vertex to the given Object and if it is Vertex it compares based on the label.
     * @param obj the object to compare to this Vertex
     * @return {@inheritDoc}
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;

        return Objects.equals(this.label, other.label);
    }


    /**
     * Compares this Vertex to another Vertex based on the label of the objects.
     * @param t
     * @return
     */
    public int compareTo(Vertex t) {
        return this.label.compareTo(t.label);
    }

    public String toString() {
        return this.label;
    }

    /**
     * Gets the uniquely identifying label of this Vertex.
     * @return
     */
    public String getLabel() {
        return label;
    }
}

