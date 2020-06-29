import java.util.HashMap;
import java.util.List;

public class VirusDriver {
    public static void main(String[] args) {

        VirusGraph g = new VirusGraph();

        g.addEdge("A", "B",1);
        g.addEdge("A", "C",5);
        g.addEdge("A", "D",11);

        g.addEdge("B", "E",3);
        g.addEdge("B", "F",6);

        g.addEdge("C", "G",4);
        g.addEdge("D", "I",9);
        g.addEdge("E", "J",4);

        g.addEdge("F", "G",7);
        g.addEdge("F", "K",9);
        g.addEdge("F", "L",1);

        g.addEdge("G", "H",2);
        g.addEdge("I", "J",8);

        g.addVertex(new Vertex("M"));

        DepthFirstSearch dfs = new DepthFirstSearch(g,g.getVertex("A"));
        System.out.println("To contact: " + dfs.getDepthFirstTraversalList());

        // set alphaCodes for each person
        g.getVertex("A").setCode("0000000");
        g.getVertex("B").setCode("0000001");
        g.getVertex("C").setCode("0000002");
        g.getVertex("D").setCode("0000003");
        g.getVertex("E").setCode("0000004");
        g.getVertex("F").setCode("0000005");
        g.getVertex("G").setCode("0000006");
        g.getVertex("H").setCode("0000007");
        g.getVertex("I").setCode("0000008");
        g.getVertex("J").setCode("0000009");
        g.getVertex("K").setCode("000000A");
        g.getVertex("L").setCode("000000B");
        g.getVertex("M").setCode("000000C");

        // create ContactInfo for each person in the HashMap
        HashMap<String, ContactInfo> dataStore = new HashMap<>();
        dataStore.put("0000000", new ContactInfo("Aardvark", "0400 000 001", "1 street, suburb, postcode"));
        dataStore.put("0000001", new ContactInfo("Baboon", "0400 000 002", "2 street, suburb, postcode"));
        dataStore.put("0000002", new ContactInfo("Crocodile", "0400 000 003", "3 street, suburb, postcode"));
        dataStore.put("0000003", new ContactInfo("Deer", "0400 000 004", "4 street, suburb, postcode"));
        dataStore.put("0000004", new ContactInfo("Eagle", "0400 000 005", "5 street, suburb, postcode"));
        dataStore.put("0000005", new ContactInfo("Ferret", "0400 000 006", "6 street, suburb, postcode"));
        dataStore.put("0000006", new ContactInfo("Giraffe", "0400 000 007", "7 street, suburb, postcode"));
        dataStore.put("0000007", new ContactInfo("Hummingbird", "0400 000 008", "8 street, suburb, postcode"));
        dataStore.put("0000008", new ContactInfo("Iguana", "0400 000 009", "9 street, suburb, postcode"));
        dataStore.put("0000009", new ContactInfo("Jaguar", "0400 000 010", "10 street, suburb, postcode"));
        dataStore.put("000000A", new ContactInfo("Kangaroo", "0400 000 011", "11 street, suburb, postcode"));
        dataStore.put("000000B", new ContactInfo("Labrador", "0400 000 012", "12 street, suburb, postcode"));
        dataStore.put("000000C", new ContactInfo("Markhor", "0400 000 013", "13 street, suburb, postcode"));

        List<Vertex> contacts = dfs.getDepthFirstTraversalList();
        for(Vertex c : contacts){
            System.out.println("Name: " + dataStore.get(c.getCode()).getName() + ", Phone: " + dataStore.get(c.getCode()).getMobile());
        }
        System.out.println();

        ASM asm = new ASM("attaaaggtttataccttcccaggtaacaaaccaaccaactttcgatctcttgtagatct", "aggtct");
        asm.calculateMatrix();
        asm.printMatrix();


    }
}
