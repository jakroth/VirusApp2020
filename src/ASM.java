import java.util.*;

/**
 *
 * @author Joel Pillar-Rogers
 */
public class ASM {

    private String x;
    private String y;
    private int[][] L;
    private Set<String> all_LCSs;


    public ASM(String x, String y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the matrix for the dynamic programming solution to the
     * longest common subsequence of x and y.
     * @return the matrix
     */
    public int[][] calculateMatrix() {

        L = new int[y.length()+1][x.length()+1];

        // intialise first column, leave first row as zeroes
        for(int i = 0; i <= y.length(); i++){
            L[i][0] = i;
        }
        for(int i = 1; i <= y.length(); i++){                   // start at 1, allows for the first row/column to remain zeroes
            for(int j = 1; j <= x.length(); j++){               //    O(mn)
                if(y.charAt(i-1) == x.charAt(j-1)){             // If these characters in the string match... (adjusted for -1 row/column)
                    L[i][j] = L[i-1][j-1];                      // ...take the diagonal...
                }
                else{
                    int a = Math.min(L[i-1][j],L[i][j-1]);
                    L[i][j] = Math.min(L[i-1][j-1],a) + 1;              // ...or take the min of top, left and diagonal, plus 1
                }
            }
        }
        return L;
    }

    /**
     * Prints the matrix of the calculation of the longest common subsequence.
     */
    public void printMatrix() {
        if (L != null) {
            System.out.print("   ");
            for (int i = 0; i < x.length(); i++) {
                System.out.print(" " + x.charAt(i));
            }
            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < L.length; i++) {
                for (int j = 0; j < L[i].length; j++) {
                    System.out.print(" " + L[i][j]);
                }
                System.out.println();
                if(i<y.length()) System.out.print(y.charAt(i));
            }
        }
    }

    /**
     * Gets the length of the longest common subsequence.  This should simply
     * return the value of the bottom, right corner of L.
     * @return the length of the longest common subsequence
     */
    public int getLongestLength() {
        return L[L.length-1][L[0].length-1];
    }

    /**
     * Find a longest common subsequence by only going to the "left" when
     * the characters do not match and the up and left are equal.
     * @return a longest common subsequence
     */
    public String getLCS() {
        StringBuilder sb = new StringBuilder();
        int i = L.length-1;
        int j = L[0].length-1;
        while(i > 0 && j > 0){                                  // L[0][0] is the "-1" row/column
            if(x.charAt(i - 1) == y.charAt(j - 1)){             // offset by -1 for the -1 row/column
                sb.insert(0,x.charAt(i - 1));             // make the string
                i--; j--;                                       // move diagonally if the same...
            }
            else if(L[i-1][j] > L[i][j-1]) {
                i--;                                            // ...move up only if up is bigger...
            }
            else{
                j--;                                            // ...otherwise move left
            }
        }
        return sb.toString();
    }

    /**
     * Finds all the longest common subsequences from the calculated
     * matrix.  The list returned should be ordered lexicographically.
     * @return the list of subsequences
     */
    public List<String> getAllLCS() {
        Set<String> set = recursiveLCS(L.length-1, L[0].length-1);             // create the set of all strings with the recursive function
        List<String> list = new ArrayList<>(set);                                   // put the elements of the set in an arraylist
        Collections.sort(list);                                                     // sort the arraylist lexicographically
        return list;
    }

    /**
     *  Following Wikipedia...
     */
    private Set<String> recursiveLCS(int i, int j){
        if(i == 0 || j == 0){
            Set<String> start = new HashSet<>();                                    // The start of the string set
            start.add("");
            return start;
        }
        if (x.charAt(i - 1) == y.charAt(j - 1)) {                                       // If the characters at this x,y intersection are equal...
            Set<String> temp = recursiveLCS(i-1, j-1);                             // ...run the recursive function, save in temp set...
            Set<String> newStrings = new HashSet<>();                                   // ... create another temp set for newStrings (this avoids ConcurrentModificationException)...
            for(Iterator<String> iterator = temp.iterator(); iterator.hasNext();){
                String s = iterator.next();                                             // ... and for all strings returned...
                iterator.remove();                                                      // ... remove old string...
                newStrings.add(s+x.charAt(i - 1));                                      // ... add new string with this character at the end to newStrings...
            }
            temp.addAll(newStrings);                                                    // ... then merge newStrings into temp.
            return temp;
        }
        Set<String> stringSet = new HashSet<>();
        Set<String> stringSet2 = new HashSet<>();
        if(L[i - 1][j] >= L[i][j - 1]){                                // If up is bigger than or equal to left...
            stringSet = recursiveLCS(i-1, j);                       // ...run the recursive function on up.
        }
        if(L[i][j-1] >= L[i-1][j]){                                    // If left is bigger than or equal to up...
            stringSet2 = recursiveLCS(i, j-1);                      // ...run the recursive function on up.
        }
        stringSet.addAll(stringSet2);                                  // Merge the sets.
        return stringSet;
    }


}

