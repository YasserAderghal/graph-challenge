//import Graph;

public class Main {
    public static void main(String []args) {

        int M[][] = {
            {1,1,1,1,1,1},
            {1,0,2,0,1,1},
            {1,0,1,0,0,1},
            {1,0,1,1,0,1},
            {1,0,1,1,1,1},
            {1,0,1,1,0,1}
        };

        Graph g = new Graph(M);
        g.convertToAdjacency();
        g.printMatrix("grid");

        //System.out.println();
        //g.printMatrix("adjacent");
        System.out.println();
        g.printMatrix("count");

        //g.gridEdgeDetecteor();
        
        //g.getGridEdges().forEach(e -> System.out.print(e + " "));
        Algorithms A = new Algorithms();
        A.setGraph(g);
        int value = A.getShortestPath(2);
        System.out.println(value);

    }
}
