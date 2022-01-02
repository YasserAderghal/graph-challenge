import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class DijkstraAdjacencyMatrix {

    static class Graph{
        int vertices;
        int matrix[][];

        public Graph(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        public void addEdge(int source, int destination, int weight) {
            //add edge
            matrix[source][destination]=weight;

            //add back edge for undirected graph
            matrix[destination][source] = weight;
        }

        //get the vertex with minimum distance which is not included in SPT
        int getMinimumVertex(boolean [] mst, int [] key){
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i <vertices ; i++) {
                if(mst[i]==false && minKey>key[i]){
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public Map dijkstra_GetMinDistances(int sourceVertex){
            boolean[] spt = new boolean[vertices];
            int [] distance = new int[vertices];

            Map<Integer, Integer> predecessors = new HashMap<>();


            int INFINITY = Integer.MAX_VALUE;

            //Initialize all the distance to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = INFINITY;
            }

            //start from the vertex 0
            distance[sourceVertex] = 0;

            //create SPT
            for (int i = 0; i <vertices ; i++) {

                //get the vertex with the minimum distance
                int vertex_U = getMinimumVertex(spt, distance);
                System.out.println(i + " : " + vertex_U);

                if( vertex_U <0 )
                    continue;

                //include this vertex in SPT
                spt[vertex_U] = true;

                //iterate through all the adjacent vertices of above vertex and update the keys
                for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                    //check of the edge between vertex_U and vertex_V
                    if(matrix[vertex_U][vertex_V]>0){
                        //check if this vertex 'vertex_V' already in spt and
                        // if distance[vertex_V]!=Infinity

                        if(spt[vertex_V]==false && matrix[vertex_U][vertex_V]!=INFINITY){
                            //check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance

                            int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
                            if(newKey<distance[vertex_V]){
                                distance[vertex_V] = newKey;
                                predecessors.put(Integer.valueOf(vertex_V), Integer.valueOf(vertex_U));
                            }
                        }
                    }
                }
            }
            //print shortest path tree
            printDijkstra(sourceVertex, distance);
            
            return predecessors;
        }

        public void printDijkstra(int sourceVertex, int [] key){
            System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + (sourceVertex +1)+ " to vertex " +   + (i+1) +
                        " distance: " + key[i]);
            }
        }
    }


    public static int[][] makeGrid(int[][] A, int N) throws Exception {
	int j = N;
	int k = 1;
	int stevec = N - 1;
	for(int i = 0; i < A.length - N; i++) {
		A[i][j] = 1;
		A[j][i] = 1;
		j++;
	}
	for(int i = 0; i < A.length - 1; i++) {
		if(stevec > 0) {
			A[i][k] = 1;
			A[k][i] = 1;
			k++;
			stevec--;
		} else {
			k++;
			stevec = N - 1;
			continue;
		}
	}
	return A;
}


    public static void main(String[] args) throws Exception {
        /* int vertices = 6;
        Graph graph = new Graph(vertices);
        int sourceVertex = 0;
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstra_GetMinDistances(sourceVertex);
        System.out.println("-----------------------");
        */

        int M[][] = {
            {1,1,1,0,1},
            {1,0,2,0,1},
            {0,0,1,0,1},
            {1,0,1,1,0},
            {1,1,1,0,1}
        };

       int A[][] = convert(M , 4 , 5);



        // printing matrix
            System.out.print("   ");
        for (int j = 0 ; j<M[0].length ;j++) 
            System.out.print(j + " ");


            System.out.print("\n");
        for (int j = 0 ; j<M.length ;j++) {
            System.out.print(j + "  ");
            for(int i = 0; i< M[0].length ; i++)
                System.out.print(M[j][i] + " ");

            System.out.println();
        }


            System.out.println("\n------------------");


        // printing matrix
            System.out.print("\t");
        for (int j = 1 ; j<=A.length ;j++) 
            System.out.print(j + "  ");


            System.out.print("\n");
        for (int j = 0 ; j<A.length ;j++) {
            System.out.print(j+1 + "\t");
            for(int i = 0; i< A.length ; i++)
                System.out.print(A[j][i] + "  ");

            System.out.println();
        }



        //graph.dijkstra_GetMinDistances(sourceVertex);

        Graph graph = new Graph(A.length);

        for( int i = 0; i< A.length ; i++ ){
            for( int j = 0; j< A[0].length ; j++ ){
                if( A[i][j] == 1 ){
                    graph.addEdge(i, j, 1);
                }
            }
        }

        

        Vector<Integer> edges = edgeDetecteor(M);

        Map pred = graph.dijkstra_GetMinDistances(2); 

        pred.forEach(( k , v ) -> {
            if( edges.contains(k) ){
                System.out.println(k);
            }
        });

        //edges.forEach( e -> System.out.println(e + " ") );
    }


    public static Vector<Integer> edgeDetecteor(int matrix[][]){
        Vector<Integer> edges = new Vector<>();

        // columns
        for(int i =0; i<matrix.length ; i++){
            if(matrix[i][0]> 0 )
                edges.add(Integer.valueOf(matrix[i][0]));

            if( matrix[i][matrix[0].length -1]> 0  )
                edges.add(Integer.valueOf(matrix[i][ matrix[0].length -1 ]));
        }

        // rows


        for(int i =0; i<matrix[0].length ; i++){
            if(matrix[0][i]> 0 )
                edges.add(Integer.valueOf(matrix[0][i]));

            if( matrix[matrix[0].length -1][i]> 0  )
                edges.add(Integer.valueOf(matrix[ matrix[0].length -1][i]));
        }

        return edges;
    }


    public static int[][] convert(int matrix[][], int N , int M){
        int[][] A = matrix;

        int count = 0;
        for( int i = 0; i < A.length; i++ ){
            for (int j = 0 ; j< A[0].length ; j++) {
                if (A[i][j] == 1 )  
                    A[i][j] = 0 ;
                else
                    A[i][j] = 1;
            }
        }



        // trying to identify every free cell 
        for( int i = 0; i < A.length; i++ ){
            for (int j = 0 ; j< A[0].length ; j++) {
                if( A[i][j] == 1 )
                    A[i][j] = ++count;
            }
        }



        // now we are working on adjacent matrix
        int[][] MA = new int[count][count];

        for(int i = 0; i< count ; i++){
            for(int j =0 ; j<count; j++) {
                MA[i][j] = 0;
            }}



        for(int i = 0; i< A.length ; i++){
            for(int j =0 ; j< A[0].length -1 ; j++) {
                if( A[i][j] > 0 ){
                    // check next row element
                    if( A[i][j+1] > 0 )
                        MA[ A[i][j]-1  ] [ A[i][j+1]-1 ] = 1;
                    



                    // check next column element
                    if( i != A.length -1  && A[i+1][j] > 0 )
                        MA[ A[i][j]  -1] [ A[i+1][j] -1] = 1;

                }


            }
        }


        // make the matrix symetric
        for(int i = 0; i< MA.length ; i++) {
            for(int j = 0; j< MA.length ; j++) {
                if( MA[i][j] == 1 )
                    MA[j][i] = 1;
            }
        }






        return MA; 
    }
}

