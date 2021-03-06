/**
 * Created by nitin on 8/9/16.
 */
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.InputStream;
import java.util.*;
public class Bridge {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        DFS solver = new DFS();
        int test = in.readInt();
        
        for(int i = 1;i<=test;i++)
            solver.solve(1,in, out);
        out.close();
    }
}
class Graph {

    public Map<Integer, List<Integer>> adj;
    public int numVertices,time;
    int[] colors,pred,disc,finish,low;
    public Graph( int numVertices) {

        this.numVertices = numVertices;
        adj = new HashMap<Integer, List<Integer>>();

        colors = new int[ numVertices + 1 ] ;
        pred = new int[ numVertices + 1 ];
        disc	= new int[ numVertices + 1 ] ;
        finish = new int[ numVertices + 1 ] ;
        low = new int[ numVertices + 1 ];


        for(int i= 1; i<=numVertices; i++ ) {
            adj.put(i,new LinkedList<Integer>());
        }

    }

    public void addEdge( int source , int dest ) {
        if ( source > adj.size() || dest > adj.size() ) {
            System.out.println( " edge cannot be added as entered vertex is not present " )	;
            return ;
        }
        //Bidirectional Graph from source to Destination and then from Destination to source it is there
        List< Integer > sList = adj.get( source );
        sList.add(dest);
        List< Integer > dList = adj.get( dest );
        dList.add( source );
    }

    public List<Integer> adjVertices( int source ) {
        if ( source > adj.size() ) {
            System.out.println(" the vertex entered is not present " );
            return null;
        }
        return adj.get(source);
    }
}
class DFS{

	//0 is for white 1 is for grey and 2 is for black

    Graph G;
    boolean flag = false;
    boolean debug = true;
    public Graph dfs(Graph graph) {
        G = graph;
        Arrays.fill(G.pred, -1);
        for (int u : G.adj.keySet()) {
            if (G.colors[u] == 0) {
                dfs_visit(u);
            }
        }
        return graph;
    }
    public void dfs_visit(int u) {
        G.time++;
        G.disc[u] = G.time;
        G.low[u] = G.disc[u];
        G.colors[u] = 1;
        for( int v : G.adj.keySet() ) {
            if ( G.colors[v] == 0 ) {
            	
                G.pred[v] = u;
                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                G.low[u] = Math.min( G.low[u] , G.low[v]);

                // If the lowest vertex reachable from subtree
                // under v is  below u in DFS tree, then u-v
                // is a bridge
                if (G.low[v] > G.disc[u]) {
                    flag = true;
                    System.out.println("bridge found");
                    break;
                }
                dfs_visit(v);
            }
            if (v != G.pred[u]) {
                G.low[u] = Math.min(G.low[u], G.disc[v]);
            }
        }
        G.colors[u] = 2;
        //G.time++;
        G.finish[u] = G.time;
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        Graph G = new Graph(n);
        for ( int i = 1; i<=m; i++ ) {
            int s = in.readInt();
            int e = in.readInt();
            G.addEdge(s,e);
        }
        G = dfs(G);
        System.out.println(flag);
        System.out.println("colors");
        for(int i=1;i<G.colors.length;i++) {
            System.out.println("i at "+G.colors[i]);
        }
        System.out.println("disc");
        for(int i=1;i<G.disc.length;i++) {
            System.out.println("i at "+G.disc[i]);
        }
        System.out.println("finish");
        for(int i=1;i<G.finish.length;i++) {
            System.out.println("i at "+G.finish[i]);
        }
        System.out.println("low");
        for(int i=1;i<G.finish.length;i++) {
            System.out.println("i at "+G.finish[i]);
        }
    }
}
class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
    }
    public String next() {
        return readString();
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}
